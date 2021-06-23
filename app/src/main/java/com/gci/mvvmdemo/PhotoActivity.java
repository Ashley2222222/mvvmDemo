package com.gci.mvvmdemo;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.commlib.util.StringUtil;
import com.gci.mvvmdemo.databinding.ActivityPhotoBinding;
import com.luck.picture.lib.tools.StringUtils;

public class PhotoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        //获取布局绑定实例
        ActivityPhotoBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_photo);
        //获取VM实例
        PhotoActivityVM vm = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(PhotoActivityVM.class);
        TitleVM title = new TitleVM(getApplication(),this);
        title.setTitle(new MutableLiveData<>(StringUtil.getStringById(this,R.string.picture_title)));
        binding.setTitleVm(title);
        //把他们邦在一起
        binding.setViewModel(vm);


        //设置VM所使用的生命周期
        binding.setLifecycleOwner(this);
        /**
         * 以下是我的习惯：
         *
         * 因为在VM中我们可能要用到MainActivity弹Toast什么的，因此需要传一个this
         * 因为若涉及到适配器列表等控件、以及数据绑定则需要在VM中用到binding
         * 因此VM中的setBinding，我把他当作初始化函数使用更方便！
         */
        vm.setBinding(binding,this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
}
