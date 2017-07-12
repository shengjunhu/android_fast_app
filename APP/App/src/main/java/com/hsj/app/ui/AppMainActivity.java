package com.hsj.app.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.hsj.app.R;
import com.hsj.base.app.ui.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14 09:46
 * @Class:
 * @Description:应用主页
 */
public class AppMainActivity extends BaseActivity {

    private boolean isExitApp;
    private boolean isFirstShow;
    private ViewPager vp_app;
    private TabLayout tab_app;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        vp_app = findView(R.id.vp_app);
        tab_app = findView(R.id.tab_app);
    }

    @Override
    protected void initToolbar(Toolbar toolbar, TextView tv_left, TextView tv_center, TextView tv_right) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        List<String> tabNameList = new ArrayList<>();
        List<Fragment> tabFragmentList = new ArrayList<>();
        tabNameList.add("首页");
        tabNameList.add("商城");
        tabNameList.add("购物车");
        tabNameList.add("好友");
        tabNameList.add("聊天");
        tabNameList.add("好友圈");
        tabNameList.add("我的");

        try {
            Class<Fragment> home = (Class<Fragment>) Class.forName("com.hsj.home.ui.fragment.HomeFragment");
            Class<Fragment> shop = (Class<Fragment>) Class.forName("com.hsj.home.ui.fragment.ShopFragment");
            Class<Fragment> car = (Class<Fragment>) Class.forName("com.hsj.home.ui.fragment.CarFragment");
            Class<Fragment> friend = (Class<Fragment>) Class.forName("com.hsj.home.ui.fragment.FriendFragment");
            Class<Fragment> chat = (Class<Fragment>) Class.forName("com.hsj.home.ui.fragment.ChatFragment");
            Class<Fragment> zone = (Class<Fragment>) Class.forName("com.hsj.home.ui.fragment.ZoneFragment");
            Class<Fragment> me = (Class<Fragment>) Class.forName("com.hsj.home.ui.fragment.MeFragment");

            tabFragmentList.add(home.newInstance());
            tabFragmentList.add(shop.newInstance());
            tabFragmentList.add(car.newInstance());
            tabFragmentList.add(friend.newInstance());
            tabFragmentList.add(chat.newInstance());
            tabFragmentList.add(zone.newInstance());
            tabFragmentList.add(me.newInstance());

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        vp_app.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(),tabFragmentList, tabNameList));
        tab_app.setupWithViewPager(vp_app);
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
