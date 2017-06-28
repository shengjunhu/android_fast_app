package com.hsj.app.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.hsj.app.R;
import com.hsj.base.app.AppBaseActivity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 15:29
 * @Class:AppLoginActivity
 * @Description:应用登陆界面：密码登陆第三方登陆和短信登陆
 */
public class AppLoginActivity extends AppBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_login;
    }

    @Override
    protected void initUI() {

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
