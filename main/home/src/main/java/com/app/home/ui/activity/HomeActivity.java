package com.app.home.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.app.common.ui.activity.BaseActivity;
import com.app.home.R;
import com.app.home.ui.fragment.HomeFragment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14/09:46
 * @Class:HomeActivity
 * @Description:
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_home, new HomeFragment())
                .commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
