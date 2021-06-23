package com.commlib.util;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.commlib.R;

public class ToastUtil {
    private Toast mToast = null;
    private TextView mTextView = null;
    public void showToast(Activity activity, String message) {
        if (StringUtil.checkEmptyString(message)||null==activity) return;
        StringBuilder strBuilder = new StringBuilder("<font face='" + activity.getString(R.string.font_type) + "'>");
        strBuilder.append(message).append("</font>");

        View toastRoot = activity.getLayoutInflater().inflate(R.layout.layout_toast, null);
        if (null == mToast || null == mTextView) {
            mToast = new Toast(activity);
            mToast.setView(toastRoot);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mTextView = toastRoot.findViewById(R.id.tv_toast_info);
            mTextView.setText(Html.fromHtml(strBuilder.toString()));
        } else {
            mTextView.setText(Html.fromHtml(strBuilder.toString()));
        }
        mToast.setGravity(Gravity.BOTTOM, 0, activity.getResources().getDisplayMetrics().heightPixels / 5);
        mToast.show();
    }

    public void showToast(Context context, String message) {
        if (StringUtil.checkEmptyString(message)||null==context) return;
        StringBuilder strBuilder = new StringBuilder("<font face='" + context.getString(R.string.font_type) + "'>");
        strBuilder.append(message).append("</font>");

        View toastRoot = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        if (null == context || null == mTextView) {
            mToast = new Toast(context);
            mToast.setView(toastRoot);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mTextView = toastRoot.findViewById(R.id.tv_toast_info);
            mTextView.setText(Html.fromHtml(strBuilder.toString()));
        } else {
            mToast.setView(toastRoot);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mTextView = toastRoot.findViewById(R.id.tv_toast_info);
            mTextView.setText(Html.fromHtml(strBuilder.toString()));
        }
        mToast.setGravity(Gravity.BOTTOM, 0, context.getResources().getDisplayMetrics().heightPixels / 5);
        mToast.show();
    }
}
