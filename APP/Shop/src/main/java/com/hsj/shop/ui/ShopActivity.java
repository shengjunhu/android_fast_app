package com.hsj.shop.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.hsj.base.app.ui.BaseActivity;
import com.hsj.shop.R;
import com.hsj.shop.ui.fragment.ShopFragment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14 09:46
 * @Class:
 * @Description:
 */
public class ShopActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_shop, new ShopFragment())
                .commit();
    }

    @Override
    protected void initToolbar(Toolbar toolbar, TextView tv_left, TextView tv_center, TextView tv_right) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
