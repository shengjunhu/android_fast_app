package com.hsj.app.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hsj.app.R;
import com.hsj.base.AppBaseActivity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 15:28
 * @Class:AppWelcomeActivity
 * @Description:引导页
 */
public class AppWelcomeActivity extends AppBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_welcome;
    }

    @Override
    protected void initView() {

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
