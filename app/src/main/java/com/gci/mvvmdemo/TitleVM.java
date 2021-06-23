package com.gci.mvvmdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.commlib.util.ActivityUtil;

public class TitleVM extends AndroidViewModel {


    @SuppressLint("StaticFieldLeak")

    public static MutableLiveData<String> titleStr = new MutableLiveData<>();
    private Activity activity;

    public TitleVM(@NonNull Application application, Activity activity) {
        super(application);
        this.activity = activity;
    }


    public MutableLiveData<String> getTitle() {
        return titleStr;
    }

    public void setTitle(MutableLiveData<String> titleStr) {
        this.titleStr = titleStr;
    }


    public  void back(View v) {
        ActivityUtil.finish(activity);
    }
}
