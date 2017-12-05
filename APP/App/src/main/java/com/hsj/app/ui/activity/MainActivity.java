package com.hsj.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.hsj.app.R;
import com.hsj.app.ui.adapter.TabFragmentAdapter;
import com.hsj.base.ui.BaseActivity;
import com.qihoo360.replugin.RePlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14/09:46
 * @Class:AppMainActivity
 * @Description:应用主页
 */
public class MainActivity extends BaseActivity {

    private boolean isExitApp;
    private boolean isFirstShow;
    private ViewPager vp_app;
    private TabLayout tab_app;

    @Override
    protected int getLayoutId() {
        RePlugin.registerHookingClass("com.hsj.home.ui.fragment.HomeFragment",
                RePlugin.createComponentName("home", "com.hsj.home.ui.fragment.HomeFragment"), null);
        RePlugin.registerHookingClass("com.hsj.shop.ui.fragment.ShopFragment",
                RePlugin.createComponentName("shop", "com.hsj.shop.ui.fragment.ShopFragment"), null);
        RePlugin.registerHookingClass("com.hsj.chat.ui.fragment.ChatFragment",
                RePlugin.createComponentName("chat", "com.hsj.chat.ui.fragment.ChatFragment"), null);
        RePlugin.registerHookingClass("com.hsj.discover.ui.fragment.DiscoverFragment",
                RePlugin.createComponentName("discover", "com.hsj.discover.ui.fragment.DiscoverFragment"), null);
        RePlugin.registerHookingClass("com.hsj.me.ui.fragment.MeFragment",
                RePlugin.createComponentName("me","com.hsj.me.ui.fragment.MeFragment"),null);
        return R.layout.activity_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        vp_app = findView(R.id.vp_app);
        tab_app = findView(R.id.tab_app);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void initData() {
        List<String> tabNameList = new ArrayList<>();
        tabNameList.add("首页");
        tabNameList.add("商城");
        tabNameList.add("好友");
        tabNameList.add("发现");
        tabNameList.add("我的");

        List<Fragment> tabFragmentList = new ArrayList<>();
        try {
            ClassLoader homeClassLoader = RePlugin.fetchClassLoader("home");
            ClassLoader shopClassLoader = RePlugin.fetchClassLoader("shop");
            ClassLoader chatClassLoader = RePlugin.fetchClassLoader("chat");
            ClassLoader discoverClassLoader = RePlugin.fetchClassLoader("discover");
            ClassLoader meClassLoader = RePlugin.fetchClassLoader("me");

            //使用插件的Classloader获取指定Fragment实例
            Fragment homeFragment = homeClassLoader.loadClass("com.hsj.home.ui.fragment.HomeFragment")
                    .asSubclass(Fragment.class).newInstance();
            Fragment shopFragment = shopClassLoader.loadClass("com.hsj.shop.ui.fragment.ShopFragment")
                    .asSubclass(Fragment.class).newInstance();
            Fragment chatFragment = chatClassLoader.loadClass("com.hsj.chat.ui.fragment.ChatFragment")
                    .asSubclass(Fragment.class).newInstance();
            Fragment discoverFragment = discoverClassLoader.loadClass("com.hsj.discover.ui.fragment.DiscoverFragment")
                    .asSubclass(Fragment.class).newInstance();
            Fragment meFragment = meClassLoader.loadClass("com.hsj.me.ui.fragment.MeFragment")
                    .asSubclass(Fragment.class).newInstance();

            tabFragmentList.add(homeFragment);
            tabFragmentList.add(shopFragment);
            tabFragmentList.add(chatFragment);
            tabFragmentList.add(discoverFragment);
            tabFragmentList.add(meFragment);

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        vp_app.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(),tabFragmentList, tabNameList));
        tab_app.setupWithViewPager(vp_app);
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
