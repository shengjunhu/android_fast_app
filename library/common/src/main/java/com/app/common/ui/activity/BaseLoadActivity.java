package com.app.common.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:AppBaseActivity
 * @Description:界面数据全部来源网络，继承此activity
 * 1、加载中、2、加载成功、3、加载失败、4、网络故障
 */
public abstract class BaseLoadActivity extends AppCompatActivity implements View.OnClickListener {

    public String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initUI(savedInstanceState);

        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 刷新数据
     * @param isRefresh
     */
    protected void refreshData(boolean isRefresh){
        if(isRefresh)initData();
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
     * 弹出Toast
     *
     * @param message
     */
    public void showToast(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 判断字符串不为 null和 ""
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

    protected void startDialog(String hint) {

    }

    protected void stopDialog() {

    }

    /**
     * 按返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 按返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
