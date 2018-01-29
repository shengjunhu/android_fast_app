package com.app.discover.ui.activity;

import android.os.Bundle;
import android.view.View;
import com.app.base.ui.activity.BaseActivity;
import com.app.discover.R;
import com.app.discover.ui.fragment.DiscoverFragment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14/09:46
 * @Class:DiscoverActivity
 * @Description:
 */
public class DiscoverActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discover;
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
