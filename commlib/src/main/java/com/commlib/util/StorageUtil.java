package com.commlib.util;

import java.io.File;

public class StorageUtil {
    public static boolean isFileExist(String fileNamePath) {
        if (StringUtil.checkEmptyString(fileNamePath)) return false;
        File file = new File(fileNamePath);
        file.isFile();
        return file.exists();
    }

    public static boolean isFileExist(String fileName, String path) {
        File file = new File(path + fileName);
        file.isFile();
        return file.exists();
    }
}
