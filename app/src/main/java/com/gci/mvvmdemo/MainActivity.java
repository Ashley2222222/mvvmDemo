package com.gci.mvvmdemo;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.commlib.util.LogUtil;
import com.gci.mvvmdemo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        //获取布局绑定实例
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //获取VM实例
        MainVM vm = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainVM.class);
        //把他们邦在一起
        binding.setViewModel(vm);
        User user = new User("dog", vm.getImageUrl());
        binding.setUser(user);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccount("aaaa");
        binding.setLoginInfo(loginInfo);

        //设置VM所使用的生命周期
        binding.setLifecycleOwner(this);
        /**
         * 以下是我的习惯：
         *
         * 因为在VM中我们可能要用到MainActivity弹Toast什么的，因此需要传一个this
         * 因为若涉及到适配器列表等控件、以及数据绑定则需要在VM中用到binding
         * 因此VM中的setBinding，我把他当作初始化函数使用更方便！
         */
        vm.setBinding(binding, this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                LogUtil.i("backPic","back");
                break;
            default:
                break;
        }
    }
}
