package com.commlib.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.commlib.R;
import com.commlib.util.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 加载时等待的动画框 ylp
 */
public class MyProgressDialog {

    private LayoutInflater mInflater;
    private View inflaterlayout;
    private TextView mTextView;
    private PopupWindow mPopupWindow;
    AVLoadingIndicatorView avi;
    private int mResId;
    private String string;

    public MyProgressDialog(Context pContext) {
        initView(pContext);
    }

    public MyProgressDialog(Context pContext, int ResId) {
        this.mResId = ResId;
        initView(pContext);
    }

    public MyProgressDialog(Context pContext, String ResId) {
        this.string = ResId;
        initView(pContext);
    }

    public void setMessage(int ResId) {
        mTextView.setText(ResId);
    }

    public void setMessage(String ResId) {
        mTextView.setText(ResId);
    }


    private void initView(Context pContext) {
        if (null == pContext) return;
        mInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (null != mInflater) {
            inflaterlayout = mInflater.inflate(R.layout.progressdialog, null);
            LinearLayout layout = (LinearLayout) inflaterlayout.findViewById(R.id.progressdialog_layout);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
            WindowManager _wm = (WindowManager) pContext.getSystemService(Context.WINDOW_SERVICE);
            layoutParams.width = _wm.getDefaultDisplay().getWidth() * 2 / 5;
            layout.setLayoutParams(layoutParams);
            avi = (AVLoadingIndicatorView) layout.findViewById(R.id.avi);
            //显示
            avi.show();


            //GradientDrawable myGrad = (GradientDrawable) myGif.getBackground();
            //myGrad.setColor(mContext.getResources().getColor(R.color.setting_listBg_color));
            //myGrad.setColor(android.graphics.Color.parseColor("#00BD9C"));
            // 对话框上的字体
            mTextView = (TextView) inflaterlayout.findViewById(R.id.progressdialog_title);
//        if (!StringUtil.checkEmptyString(string))
//            mTextView.setText(string);
//        else if (Integer.MIN_VALUE != mResId&&Integer.MAX_VALUE != mResId)
//            mTextView.setText(mResId);
            //int _Width = _wm.getDefaultDisplay().getWidth() * 13 / 20;
            //mTextView.setWidth(_Width);

            // 动态添加自定义组件,gif动画
        /*gifView = new GifView(mContext);
        gifView.setGifImage(R.drawable.waitgif_4);
		LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams(-2, -2);
		contentLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		contentLayoutParams.topMargin = 40;

		myGif.addView(gifView, 0, contentLayoutParams);*/
//            SpinKitView spinKitView = (SpinKitView) inflaterlayout.findViewById(R.id.spin_kit);
//            Style style = Style.values()[7];
//            Sprite drawable = SpriteFactory.create(style);
////            spinKitView.setColor(SkinManager.getInstance().replaceColorById(pContext, R.color.not_select_text_green));
//            spinKitView.setIndeterminateDrawable(drawable);

            // -1为fill——parent，-2为适配控件大小
            mPopupWindow = new PopupWindow(inflaterlayout, -1, -1);
        }
    }

    public void show(View parent, int p_ResId) {
        mTextView.setText(p_ResId);
        try {
            mPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
        }
    }

    public void showByText(View parent, String p_ResId) {
        mTextView.setText(p_ResId);
        try {
            mPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
        }
    }

    public void show(View parent) {
        try {
            mPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            LogUtil.e("showDialog", e.toString());
        }
    }

    public void dismiss() {
        try {
            avi.hide();
            mPopupWindow.dismiss();
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Description: 获取PopUpWindow的状态，true为显示，false为隐藏<br>
     */
    public boolean isShowing() {
        try {
            return mPopupWindow.isShowing();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setText(String pString) {
        mTextView.setText(pString);
    }


}
