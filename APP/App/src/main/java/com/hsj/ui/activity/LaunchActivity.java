package com.hsj.ui.activity;

import android.os.Bundle;
import android.view.View;
import com.hsj.R;
import com.hsj.base.ui.activity.BaseActivity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27/15:26
 * @Class:AppLaunchActivity
 * @Description:启动页
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        //进入欢迎页或者广告页
        //startActivity(new Intent(this, WelActivity.class));
        this.finish();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void initData() {

    }
}
