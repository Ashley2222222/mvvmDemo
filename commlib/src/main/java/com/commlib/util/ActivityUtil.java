package com.commlib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.commlib.R;

public class ActivityUtil {
    public static void startActivity(Activity fromActivity, Class<?> toClass) {
        Intent intent = new Intent(fromActivity, toClass);
        fromActivity.startActivity(intent);
        fromActivity.overridePendingTransition(R.anim.in_from_left_to_right, R.anim.out_to_right);
    }

    /**
     * @name ActivityUtil
     * @class describe 启动新Activity后finish
     * @author liangxy
     * @time 2021/6/23 9:10
     * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
     */
    public static void startActivityFinished(Activity fromActivity, Class<?> toClass) {
        Intent intent = new Intent(fromActivity, toClass);
        fromActivity.startActivity(intent);
        fromActivity.overridePendingTransition(R.anim.in_from_left_to_right, R.anim.out_to_right);
        fromActivity.finish();
    }

    public static void startActivityForResult(Activity fromActivity, Class<?> toClass, int requestCode) {
        Intent intent = new Intent(fromActivity, toClass);
        fromActivity.startActivityForResult(intent, requestCode);
        fromActivity.overridePendingTransition(R.anim.in_from_left_to_right, R.anim.out_to_right);
    }

    public static void finish(Activity fromActivity) {
        fromActivity.overridePendingTransition(R.anim.in_from_left_to_right,
                R.anim.out_to_right);
        fromActivity.finish();
    }


}
