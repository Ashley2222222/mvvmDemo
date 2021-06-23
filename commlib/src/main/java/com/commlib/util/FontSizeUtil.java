package com.commlib.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FontSizeUtil {

    //遍历设置字体
    public static void changeViewSize(ViewGroup viewGroup,int screenWidth,int screenHeight,Float font,int titleBarId) {//传入Activity顶层Layout,屏幕宽,屏幕高
        float adjustFontSize;
        if(Float.MIN_VALUE==font){//CommVariable.FONTSIZE
            adjustFontSize = adjustFontSize(screenWidth, screenHeight);
        }else
        adjustFontSize=font;

        ////Log.i("adjustFontSize",adjustFontSize+"");
        for(int i = 0; i<viewGroup.getChildCount(); i++ ){
            View v = viewGroup.getChildAt(i);
            if(v instanceof ViewGroup){
                changeViewSize((ViewGroup)v,screenWidth, screenHeight,font,titleBarId);
            }else if(v instanceof Button){//按钮加大这个一定要放在TextView上面，因为Button也继承了TextView
                ( (Button)v ).setTextSize(adjustFontSize);
            }else if(v instanceof TextView){
                if(v.getId()== titleBarId){//R.id.tv_title_bar){//顶部标题栏标题文字
                    ( (TextView)v ).setTextSize(adjustFontSize+2);
                }else{
                    ( (TextView)v ).setTextSize(adjustFontSize);
                }
            }
        }
    }


    //获取字体大小
    public static int adjustFontSize(int screenWidth,int screenHeight) {
        /**
         * 1. 在视图的 onsizechanged里获取视图宽度，一般情况下默认宽度是320，所以计算一个缩放比率
         rate = (float) w/320   w是实际宽度
         2.然后在设置字体尺寸时 paint.setTextSize((int)(8*rate));   8是在分辨率宽为320 下需要设置的字体大小
         实际字体大小 = 默认字体大小 x  rate
         */
        int rate = (int)(4*(float) screenWidth/320); //我自己测试这个倍数比较适合，当然你可以测试后再修改
        ////Log.i("adjustFontSizeW",screenWidth+"");
//        CommVariable.FONTSIZE =rate;
        return rate<15?15:rate; //字体太小也不好看的

    }

}
