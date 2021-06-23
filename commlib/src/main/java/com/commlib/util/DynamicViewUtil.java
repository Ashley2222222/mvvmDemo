package com.commlib.util;

/**
 * description ：动态绘制控件，主要用于画表格(目前：无疫区虫媒)
 * Created by liangxy on  2019/8/2 10:58
 */

public class DynamicViewUtil {
   /* // 生成LinearLayout
    @SuppressLint("NewApi")
    public static void setFL(FrameLayout.LayoutParams lp, int orientation, FrameLayout ll, int bgColor,
                             int paddingLeft, int paddingTop, int paddingRight, int paddingBottom,
                             int marginLeft, int marginTop, int marginRight, int marginBottom) {
        // 新建一个LinearLayout
        ll.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        ll.setBackgroundColor(bgColor);
        lp.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        ll.setForegroundGravity(Gravity.CENTER); //必须要加上这句，setMargins才会起作用，而且此句还必须在setMargins下面
        ll.setLayoutParams(lp);
    }    @SuppressLint("NewApi")
    public static void setLL(LinearLayout.LayoutParams lp, int orientation, LinearLayout ll, int bgColor,
                             int paddingLeft, int paddingTop, int paddingRight, int paddingBottom,
                             int marginLeft, int marginTop, int marginRight, int marginBottom) {
        // 新建一个LinearLayout
        ll.setOrientation(orientation);
        ll.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        ll.setBackgroundColor(bgColor);
        lp.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        ll.setGravity(Gravity.CENTER); //必须要加上这句，setMargins才会起作用，而且此句还必须在setMargins下面
        ll.setLayoutParams(lp);
    }
    // 生成RelativeLayout
    @SuppressLint("NewApi")
    public static void setRL(RelativeLayout.LayoutParams lp, RelativeLayout rl, int bgColor,
                             int paddingLeft, int paddingTop, int paddingRight, int paddingBottom,
                             int marginLeft, int marginTop, int marginRight, int marginBottom) {
        rl.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        rl.setPadding(5, 5, 5, 5);
        rl.setBackgroundColor(bgColor);
        lp.setMargins(5, 5, 5, 5);
        rl.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        rl.setLayoutParams(lp);
    }
    //生成Textview
    @SuppressLint("NewApi")
    public static void setTV(int width, Context ctx, TextView tv, int height, int stringId,
                             String str, int inputType, int textColor, int bgColor,
                             int marginLeft, int marginTop, int marginRight, int marginBottom,int font) {
        LinearLayout.LayoutParams LP_tv = new LinearLayout.LayoutParams(
                width, height);
        LP_tv.setMargins(marginLeft, marginTop,marginRight,marginBottom);

        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(LP_tv);
        tv.setInputType(inputType);
        tv.setTextColor(textColor);
        tv.setBackgroundColor(bgColor);
        if (stringId != 0)
            tv.setText(ctx.getResources().getString(stringId));
        else tv.setText(str);
        float font_normal = ctx.getResources().getDimension(font);//R.dimen.font_normal);
        int sp =UIUtil.px2sp(font_normal);
        tv.setTextSize(sp);


    }

    @SuppressLint("NewApi")
    public static void setET(int width, Context ctx, EditText et, int height, int stringId,
                             String str, int inputType, int textColor, int bgColor  ,
                             int marginLeft, int marginTop, int marginRight, int marginBottom,int drawable,int font) {
        LinearLayout.LayoutParams LP_tv = new LinearLayout.LayoutParams(
                width, height);
        LP_tv.setMargins(marginLeft, marginTop,marginRight,marginBottom);

//        et.setBackground(ctx.getResources().getDrawable(R.drawable.selector_for_edittext_table));
        et.setBackground(ctx.getResources().getDrawable(drawable));

        et.setGravity(Gravity.CENTER);
        et.setLayoutParams(LP_tv);
        et.setInputType(inputType);
        et.setTextColor(textColor);
//        et.setBackgroundColor(bgColor);
        et.setFocusableInTouchMode(true);
//        et.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (float) 200);
        if (stringId != 0)
            et.setText(ctx.getResources().getString(stringId));
        else et.setText(str);
        float font_normal = ctx.getResources().getDimension(font);//R.dimen.font_normal);
        int sp =UIUtil.px2sp(font_normal);
        et.setTextSize(sp);
    }


    @SuppressLint("NewApi")
    public static void setIv(int width, Context ctx, ImageView iv, int height,
                             int marginLeft, int marginTop, int marginRight, int marginBottom) {
        LinearLayout.LayoutParams LP_tv = new LinearLayout.LayoutParams(
                width, height);
        LP_tv.setMargins(marginLeft, marginTop,marginRight,marginBottom);
        iv.setLayoutParams(LP_tv);
//        iv.setBackground(ctx.getResources().getDrawable(R.drawable.selector_add_video_btn));
    }*/
}
