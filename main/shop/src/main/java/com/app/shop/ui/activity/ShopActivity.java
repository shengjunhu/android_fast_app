package com.app.shop.ui.activity;

import android.os.Bundle;
import android.view.View;
import com.app.base.ui.activity.BaseActivity;
import com.app.shop.ui.fragment.ShopFragment;
import com.app.shop.R;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14/09:46
 * @Class:ShopActivity
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
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
