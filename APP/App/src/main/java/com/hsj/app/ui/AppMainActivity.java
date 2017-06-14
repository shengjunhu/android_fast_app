package com.hsj.app.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import com.hsj.app.R;
import com.hsj.base.AppBaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14 09:46
 * @Class:
 * @Description:
 */
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
        List<String> tabNameList = new ArrayList<>();
        tabNameList.add("首页");
        tabNameList.add("商城");
        tabNameList.add("购物车");
        tabNameList.add("好友");
        tabNameList.add("聊天");
        tabNameList.add("好友圈");
        tabNameList.add("我的");

        Object[] tabNameArray = tabNameList.toArray();

        Class home = null;
        Class shop = null;
        Class car = null;
        Class friend = null;
        Class chat = null;
        Class zone = null;
        Class me = null;
        try {
            home = Class.forName("com.hsj.home.ui.fragment.HomeFragment");
            shop = Class.forName("com.hsj.home.ui.fragment.ShopFragment");
            car = Class.forName("com.hsj.home.ui.fragment.CarFragment");
            friend = Class.forName("com.hsj.home.ui.fragment.FriendFragment");
            chat = Class.forName("com.hsj.home.ui.fragment.ChatFragment");
            zone = Class.forName("com.hsj.home.ui.fragment.ZoneFragment");
            me = Class.forName("com.hsj.home.ui.fragment.MeFragment");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
