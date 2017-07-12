package com.hsj.app.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.hsj.base.app.ui.BaseActivity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 15:26
 * @Class:AppSplashActivity
 * @Description:启动页
 */
public class AppSplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void initToolbar(Toolbar toolbar, TextView tv_left, TextView tv_center, TextView tv_right) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
