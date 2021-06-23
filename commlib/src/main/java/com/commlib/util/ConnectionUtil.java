package com.commlib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author liangxy
 * @name ConnectionUtil
 * @class describe  检查网络地址是否有效
 * @time 2020/9/25 9:04
 * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
 */
public class ConnectionUtil {

    //超时时间
    public static final int TIMEOUT = 99 * 100000;// 超时时间
    public static final int CONN_TIMEOUT = 99 * 100000;// 超时时间
    public static boolean isUrlValid(String address){
        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(CONN_TIMEOUT);
            conn.setReadTimeout(TIMEOUT);

            //HTTP connect
            try {
                conn.connect();
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }

            int code = conn.getResponseCode();
            if ((code >= 100) && (code < 400)){
                return true;
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {

                //这种方法也可以
                //return mNetworkInfo .getState()== NetworkInfo.State.CONNECTED

                return mNetworkInfo.isAvailable();

            }
        }
        return false;
    }

}
