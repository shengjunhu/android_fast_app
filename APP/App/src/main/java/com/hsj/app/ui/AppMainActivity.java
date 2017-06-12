package com.hsj.app.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.hsj.app.R;
import com.hsj.base.AppBaseActivity;

public class AppMainActivity extends AppBaseActivity {

    private boolean isExitApp;
    private boolean isFirstShow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_main;
    }

    @Override
    protected void initView() {
        ViewPager vp_main = findView(R.id.vp_main);
        TableLayout tab_main = findView(R.id.tab_main);
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

    @Override
    protected void onResume() {
        super.onResume();
        checkAppUpdate();
    }

    /**
     * 检查app更新
     */
    private void checkAppUpdate() {
        if(isFirstShow) return;
        isFirstShow = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(isExitApp){
                return false;
            }else {
                isExitApp = true;
                showToast("再按返回键退出应用");
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
