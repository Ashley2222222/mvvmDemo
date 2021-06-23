package com.commlib.util;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;

import com.commlib.bean.wps.WpsInfo;
import com.commlib.util.file.DownloadWpsUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright jiesai All right rserved.
 * <p>Title: FileUtil
 *
 * @author zhuojinhong
 * @sinace 2015-7-27 下午2:24:48
 * Description:该类用于处理一些文件相关的操作
 * Modify History:
 */
public class FileUtil {
    // 下载成功
    public static final int DOWNLOAD_ERROR = 7;
    // 下载失败
    public static final int DOWNLOAD_SUCCESS = 6;

    private static ProgressDialog mProgressDialog;
    public static File writeFile(String filePath, InputStream input) {
        if (input == null)
            return null;
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file);
             InputStream ins = input) {
            byte[] b = new byte[1024];
            int len;
            while ((len = ins.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public static boolean openOfficeFile(String path, Context ctx,boolean isOnline) {

        mProgressDialog = new ProgressDialog(ctx);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(WpsInfo.OPEN_MODE, WpsInfo.OpenMode.READ_MODE); // 打开模式
        bundle.putBoolean(WpsInfo.SEND_CLOSE_BROAD, true); // 关闭时是否发送广播
        bundle.putString(WpsInfo.THIRD_PACKAGE, ctx.getPackageName()); // 第三方应用的包名，用于对该应用合法性的验证
        bundle.putBoolean(WpsInfo.CLEAR_BUFFER, true);// 清除打开记录
        bundle.putBoolean(WpsInfo.CLEAR_TRACE, true);// 清除打开记录
        // bundle.putBoolean(CLEAR_FILE, true); //关闭后删除打开文件
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName(WpsInfo.PackageName.NORMAL, WpsInfo.ClassName.NORMAL);
        File file = new File(path);
        if (!path.contains("http")) {
            if (!file.isFile() || null == file || !file.exists() || file.length() == 0) {
                System.out.println("文件为空或者不存在");
                ToastUtil viewUtil = new ToastUtil();
                viewUtil.showToast(ctx, "文件为空或者不存在");
                ////Log.i("file", "文件为空或者不存在");
                return false;
            }
        }
        //android7以上解决FileUriExposedException。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        if (isOnline) {
            //在支持http模式打开文档的版本下
            ////Log.i("online", "在线读取");

//			String uriString ="http://119.23.20.192/eastnet_wechat/test.doc" ;
            Uri uri = Uri.fromFile(new File(path));
            intent.setData(uri);
            try {
                ctx.startActivity(intent);
            } catch (ActivityNotFoundException e1) {
                e1.printStackTrace();
            }
        } else {
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            intent.putExtras(bundle);
            try {
                ctx.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                String uriString = "http://119.23.20.192/eastnet_wechat/test.doc";
                uri = Uri.parse(uriString);
                intent.setData(uri);
                try {
                    ctx.startActivity(intent);
                } catch (ActivityNotFoundException e1) {
                    e1.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    //安装wps
    public static void installWps(Context ctx) {
        mProgressDialog = new ProgressDialog(ctx);
        DownloadWpsUtil downloadUtils = new DownloadWpsUtil(ctx);
        downloadUtils.downloadAPK(ctx, "http://dl.op.wpscdn.cn/dl/wps/mobile/apk/moffice_10.3.2_1033_cn00563_multidex_9119546.apk", "office.apk");
    }


    //判断是否已安装wps
    public static boolean isInstall(Context ctx) {
        boolean isInstall = false;
        List<PackageInfo> list = ctx.getPackageManager().getInstalledPackages(
                PackageManager.GET_PERMISSIONS);

        StringBuilder stringBuilder = new StringBuilder();

        for (PackageInfo packageInfo : list) {
            stringBuilder.append("package name:" + packageInfo.packageName
                    + "\n");
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            stringBuilder.append("应用名称:"
                    + applicationInfo.loadLabel(ctx.getPackageManager()) + "\n");
            if (packageInfo.packageName.equals("cn.wps.moffice_eng"))
                isInstall = true;
            if (packageInfo.permissions != null) {

                for (PermissionInfo p : packageInfo.permissions) {
                    stringBuilder.append("权限包括:" + p.name + "\n");
                }
            }
        }

        return isInstall;
    }

    /**
     * 获取指定路径下的所有视频文件和其绝对路径
     * Created by liangxy on 2017/5/25 15:21
     */
    public static void getFileDir(String filePath, ArrayList<String> paths) {
        try {
//            names = new ArrayList<String>();
            if (null == paths)
                paths = new ArrayList<String>();
            File f = new File(filePath);
            File[] files = f.listFiles();// 列出所有文件

            // 将所有文件存入list中
            if (files != null) {
                int count = files.length;// 文件个数
                for (int i = 0; i < count; i++) {
                    File file = files[i];
//                    names.add(file.getName());
                    paths.add(file.getPath());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public static ArrayList getAllFilesName(String filePath) {
        ArrayList<String> names = new ArrayList<String>();
        try {
            File f = new File(filePath);
            File[] files = f.listFiles();// 列出所有文件

            // 将所有文件存入list中
            if (files != null) {
                int count = files.length;// 文件个数
                for (int i = 0; i < count; i++) {
                    File file = files[i];
                    names.add(file.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return names;
    }

    public static ArrayList getSpecifyFilesName(String filePath, int begin, int end) {
        ArrayList<String> names = new ArrayList<String>();
        try {
            File f = new File(filePath);
            File[] files = f.listFiles();// 列出所有文件

            // 将所有文件存入list中
            if (files != null) {
                int count = files.length;// 文件个数
                if (end > count) end = count;

                for (int i = begin; i < count; i++) {
                    File file = files[i];
                    names.add(file.getName());
//                    paths.add(file.getPath());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return names;
    }

    /**
     * 将json格式数据写入到文件中
     *
     * @param json
     * @param filePath
     */
    public static void writeJsonToFile(String json, String filePath) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            bw.write(json);
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bw, null);
        }
    }

    /**
     * 从文件中读取json格式数据
     *
     * @param filePath
     * @return
     */
    public static String readJsonFromFile(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String str = null;
            if ((str = br.readLine()) != null)
                return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(null, br);
        }
        return null;
    }

    /**
     * 关闭流
     *
     * @param bw
     * @param br
     */
    public static void close(BufferedWriter bw, BufferedReader br) {
        try {
            if (bw != null) {
                bw.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 判断文件路径是否以分割符结尾，如果不是，添加分隔符
     *
     * @param filePath
     * @return
     */
    public static String validateFilePath(String filePath) {
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        return filePath;
    }


    //文件类型后缀
    private static final String[][] MIME_MapTable = {{".3gp", "video/3gpp"}, {".apk", "application/vnd.android.package-archive"}, {".asf", "video/x-ms-asf"}, {".avi", "video/x-msvideo"}, {".bin", "application/octet-stream"}, {".bmp", "image/bmp"}, {".c", "text/plain"}, {".class", "application/octet-stream"}, {".conf", "text/plain"}, {".cpp", "text/plain"}, {".doc", "application/msword"}, {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"}, {".xls", "application/vnd.ms-excel"}, {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}, {".exe", "application/octet-stream"}, {".gif", "image/gif"}, {".gtar", "application/x-gtar"}, {".gz", "application/x-gzip"}, {".h", "text/plain"}, {".htm", "text/html"}, {".html", "text/html"}, {".jar", "application/java-archive"}, {".java", "text/plain"}, {".jpeg", "image/jpeg"}, {".jpg", "image/jpeg"}, {".js", "application/x-javascript"}, {".log", "text/plain"}, {".m3u", "audio/x-mpegurl"}, {".m4a", "audio/mp4a-latm"}, {".m4b", "audio/mp4a-latm"}, {".m4p", "audio/mp4a-latm"}, {".m4u", "video/vnd.mpegurl"}, {".m4v", "video/x-m4v"}, {".mov", "video/quicktime"}, {".mp2", "audio/x-mpeg"}, {".mp3", "audio/x-mpeg"}, {".mp4", "video/mp4"}, {".mpc", "application/vnd.mpohun.certificate"}, {".mpe", "video/mpeg"}, {".mpeg", "video/mpeg"}, {".mpg", "video/mpeg"}, {".mpg4", "video/mp4"}, {".mpga", "audio/mpeg"}, {".msg", "application/vnd.ms-outlook"}, {".ogg", "audio/ogg"}, {".pdf", "application/pdf"}, {".png", "image/png"}, {".pps", "application/vnd.ms-powerpoint"}, {".ppt", "application/vnd.ms-powerpoint"}, {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"}, {".prop", "text/plain"}, {".rc", "text/plain"}, {".rmvb", "audio/x-pn-realaudio"}, {".rtf", "application/rtf"}, {".sh", "text/plain"}, {".tar", "application/x-tar"}, {".tgz", "application/x-compressed"}, {".txt", "text/plain"}, {".wav", "audio/x-wav"}, {".wma", "audio/x-ms-wma"}, {".wmv", "audio/x-ms-wmv"}, {".wps", "application/vnd.ms-works"}, {".xml", "text/plain"}, {".z", "application/x-compress"}, {".zip", "application/x-zip-compressed"}, {"", "*/*"}};


    /**
     * 打开文件
     *
     * @param file
     */
    public static void openFile(File file, Context ctx) {
        if (StorageUtil.isFileExist(file.getAbsolutePath())){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String type = getMIMEType(file);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, type);
            ctx.startActivity(intent);
        } else {
            ToastUtil viewUtil = new ToastUtil();
            viewUtil.showToast(ctx, "文件为空或者不存在");
        }
//		activity.overridePendingTransition(0, 0);
    }

    /**
     * 获取文件类型后缀
     *
     * @param paramFile
     * @return
     */
    private static String getMIMEType(File paramFile) {
        String str1 = "*/*";
        String str2 = paramFile.getName();
        int i = str2.lastIndexOf(".");
        if (i < 0)
            return str1;
        String str3 = str2.substring(i, str2.length()).toLowerCase();
        if (str3 == "")
            return str1;
        for (int j = 0; ; j++) {
            if (j >= MIME_MapTable.length)
                return str1;
            if (str3.equals(MIME_MapTable[j][0]))
                str1 = MIME_MapTable[j][1];
        }
    }

    public static String getFileSize(String path) {
        File f = new File(path);
        if (!f.exists() || !f.isFile()) {
            return "0 MB";
        } else {
            long size = f.length();
            return (size / 1024f) / 1024f + "MB";
        }
    }

    public static Double getVideoLength(String path) {
        Double videoLength = 0.0;
        //获取视频时长  计算压缩进度用
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(path);
        String time = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);//获取视频时长
        //7680
        try {
            videoLength = Double.parseDouble(time) / 1000.00;
        } catch (Exception e) {
            e.printStackTrace();
            videoLength = 0.00;
        }
        return videoLength;

    }

    /**
     * 分隔符.
     */
    public final static String FILE_EXTENSION_SEPARATOR = ".";

    /**
     * 删除指定文件或指定目录内的所有文件
     *
     * @param path 文件或目录的绝对路径
     * @return 路径为空或空白字符串，返回true；文件不存在，返回true；文件删除返回true；
     * 文件删除异常返回false
     */
    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return true;
        }
        return deleteFile(new File(path));
    }

    /**
     * 删除指定文件或指定目录内的所有文件
     *
     * @param file
     * @return 路径为空或空白字符串，返回true；文件不存在，返回true；文件删除返回true；
     * 文件删除异常返回false
     */
    public static boolean deleteFile(File file) {
        if (null == file)
            throw new NullPointerException("file is null");
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }

        File[] files = file.listFiles();
        if (null == files)
            return true;
        for (File f : files) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    /**
     * 删除指定目录中特定的文件
     *
     * @param dir
     * @param filter
     */
    public static void delete(String dir, FilenameFilter filter) {
        if (TextUtils.isEmpty(dir))
            return;
        File file = new File(dir);
        if (!file.exists())
            return;
        if (file.isFile())
            file.delete();
        if (!file.isDirectory())
            return;

        File[] lists = null;
        if (filter != null)
            lists = file.listFiles(filter);
        else
            lists = file.listFiles();

        if (null == lists)
            return;
        for (File f : lists) {
            if (f.isFile()) {
                f.delete();
            }
        }
    }

    /**
     * 获得不带扩展名的文件名称
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0,
                    extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
                extenPosi) : filePath.substring(filePosi + 1));
    }

    /**
     * 获得文件名
     *
     * @param filePath 文件路径
     * @return 如果路径为空或空串，返回路径名；不为空时，返回文件名
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * 获得所在目录名称
     *
     * @param filePath 文件的绝对路径
     * @return 如果路径为空或空串，返回路径名；不为空时，如果为根目录，返回"";
     * 如果不是根目录，返回所在目录名称，格式如：C:/Windows/Boot
     */
    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

}
