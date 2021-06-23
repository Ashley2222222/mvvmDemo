package com.gci.mvvmdemo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.commlib.util.ActivityUtil;
import com.gci.mvvmdemo.databinding.ActivityPhotoBinding;


public class PhotoActivityVM  extends AndroidViewModel {
    private static ActivityPhotoBinding binding;



    @SuppressLint("StaticFieldLeak")
    private static PhotoActivity mainActivity;
    public static MutableLiveData<String> text = new MutableLiveData<>();


    public PhotoActivityVM(@NonNull Application application) {
        super(application);
    }

    public void setBinding(ActivityPhotoBinding binding, PhotoActivity mainActivity) {
        //把binding和mainActivity都赋值给MainVM作为静态变量备用，因为很多绑定的控件都只能用静态方法
        PhotoActivityVM.binding = binding;
        PhotoActivityVM.mainActivity = mainActivity;
    }





    public static void click2(View view) {
        text.setValue("new value");
        loadImage(view, changeImageUrl(), mainActivity.getResources().getDrawable(R.drawable.add), mainActivity.getResources().getDrawable(R.drawable.add));
        Toast.makeText(mainActivity, "你点击了图片", Toast.LENGTH_SHORT).show();
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "https://img-blog.csdnimg.cn/20200110093032777.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dpdGh1Yl8zNjc4NzU4NQ==,size_16,color_FFFFFF,t_70";
    }

    public static String changeImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbbs-fd.zol-img.com.cn%2Ft_s800x5000%2Fg1%2FM0B%2F00%2F0B%2FCg-4jVM-SuWILNJoAAFHc08fRWMAAMClQNeI7IAAUeL136.jpg&refer=http%3A%2F%2Fbbs-fd.zol-img.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1625648027&t=a74ffcfa2d33575abefd4d82f5503866";
    }

    @BindingAdapter({"path", "error", "placeholder"})
    public static void loadImage(View imageView, String imageUrl, Drawable error, Drawable placeholder) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .error(error);


        Glide.with(imageView.getContext()).load(imageUrl).apply(options).into((ImageView) imageView);
//        Glide.with(imageView.getContext()).load(R.drawable.add).apply(options).into((ImageView) imageView);
    }
}
