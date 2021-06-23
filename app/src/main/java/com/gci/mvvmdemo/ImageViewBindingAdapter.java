package com.gci.mvvmdemo;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageViewBindingAdapter {

    @BindingAdapter({"imageUrl", "error", "placeholder"})
    public static void loadImg(ImageView imageView, String imageUrl, Drawable error, Drawable placeholder){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .error(error);
        Glide.with(imageView.getContext()).load(imageUrl).apply(options).into(imageView);
    }
}
