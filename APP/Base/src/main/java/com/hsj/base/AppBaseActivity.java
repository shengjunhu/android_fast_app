package com.hsj.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:AppBaseActivity
 * @Description:
 */
public abstract class AppBaseActivity extends AppCompatActivity implements View.OnClickListener {

    public String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initUI();

        initToolBar();

        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initUI();

    protected abstract void initToolbar(Toolbar toolbar,TextView tv_left,TextView tv_center,TextView tv_right);

    protected abstract void initData();

    /**
     * 初始化Toolbar
     */
    protected void initToolBar() {
        Toolbar toolbar = findView(R.id.tb_base);
        TextView tv_left = findView(R.id.tv_left);
        TextView tv_center = findView(R.id.tv_center);
        EditText et_center = findView(R.id.et_center);
        TextView tv_right = findView(R.id.tv_right);

        initToolbar(toolbar,tv_left, tv_center,tv_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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

    protected void startDialog() {

    }

    protected void finishDialog() {

    }

    protected void startProgressBar() {

    }

    protected void finishProgressBar() {

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
