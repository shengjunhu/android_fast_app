package com.hsj.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hsj.base.core.BaseApp;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:AppBaseActivity
 * @Description:Activity基类：初始化UI、初始化数据、强制刷新数据、生命周期控制
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public String TAG = this.getClass().getSimpleName();
    private int defaultLayout = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() == 0) {
            setContentView(getLayoutId());
        } else {
            setContentView(getLayoutId());
        }

        initUI(savedInstanceState);

        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void initData();

    public void setDefaultLayout(int defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    protected int getDefaultLayout() {
        return defaultLayout;
    }

    /**
     * 检测内存泄露
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseApp.getRefWatcher().watch(this);
    }

    /**
     * 查找View
     *
     * @param id  控件的id
     * @param <V> View类型
     * @return
     */
    protected <V extends View> V findView(@IdRes int id) {
        return (V) findViewById(id);
    }

    /**
     * 刷新数据
     *
     * @param isRefresh
     */
    protected void refreshData(boolean isRefresh) {
        if (isRefresh) initData();
    }

    /**
     * 弹出Toast
     *
     * @param message
     */
    public void showToast(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * activity切换动画
     *
     * @param clazz
     * @param enterAnim
     * @param exitAnim
     */
    public void activityAnim(Class clazz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        startActivity(new Intent(this, clazz));
        overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * fragment切换动画
     *
     * @param layoutId
     * @param fragment
     * @param enterAnim
     * @param exitAnim
     */
    public void fragmentAnim(@IdRes int layoutId, Fragment fragment, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(enterAnim, exitAnim)
                .replace(layoutId, fragment)
                .commit();
    }

    /**
     * 开始提示等待dialog
     */
    protected void startDialog(@Nullable String hint) {

    }

    /**
     * 结束提示dialog
     */
    protected void stopDialog() {

    }

    /**
     * 判断字符串不为 null和 ""
     *
     * @param str
     * @return
     */
    protected boolean notNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * TextView、EditText设置文本
     *
     * @param str
     * @return
     */
    protected String checkStr(String str) {
        if (notNull(str)) {
            return str;
        } else {
            return "";
        }
    }

    /**
     * TextView、EditText设置文本
     *
     * @param num
     * @return
     */
    protected String checkStr(Number num) {
        if (num == null) {
            return "";
        } else {
            return String.valueOf(num);
        }
    }

    /**
     * 按返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 按键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
