package com.hsj.zone.ui;

import android.os.Bundle;
import android.view.View;

import com.hsj.base.lib.ui.BaseActivity;
import com.hsj.zone.R;
import com.hsj.zone.ui.fragment.ZoneFragment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14 09:46
 * @Class:
 * @Description:
 */
public class ZoneActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zone;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_zone, new ZoneFragment())
                .commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
