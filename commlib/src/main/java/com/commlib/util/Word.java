package com.commlib.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by wz on 2020/3/24.
 */
public class Word {

    public static String cacheDir = Environment.getExternalStorageDirectory().toString() + File.separator + "Test" + File.separator;

    public static int REQUESTCODE_1 = 123 ; //6.0申请权限

    public static int CACHE_TIME_5 = 300; //五分钟的缓存时间
}
