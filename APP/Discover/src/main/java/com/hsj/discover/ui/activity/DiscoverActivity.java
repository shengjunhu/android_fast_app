package com.hsj.discover.ui.activity;

import android.os.Bundle;
import android.view.View;
import com.hsj.base.app.ui.AppBaseActivity;
import com.hsj.discover.ui.fragment.DiscoverFragment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14 09:46
 * @Class:
 * @Description:
 */
public class DiscoverActivity extends AppBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zone;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_zone, new DiscoverFragment())
                .commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
