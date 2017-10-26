package com.hsj.me.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.hsj.base.app.ui.AppBaseActivity;
import com.hsj.me.R;
import com.hsj.me.ui.fragment.MeFragment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14 09:46
 * @Class:
 * @Description:
 */
public class MeActivity extends AppBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_me, new MeFragment())
                .commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
