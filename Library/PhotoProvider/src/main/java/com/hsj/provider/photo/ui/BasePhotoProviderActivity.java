package com.hsj.provider.photo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/9 09:18
 * @Class:BaseImageProviderActivity
 * @Description:
 */
public abstract class BasePhotoProviderActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();

        toolBar();

        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void toolBar(){

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
     * @param clazz
     * @param enterAnim
     * @param exitAnim
     */
    public void activityAnim(Class clazz, @AnimRes int enterAnim, @AnimRes int exitAnim){
        startActivity(new Intent(this,clazz));
        overridePendingTransition(enterAnim,exitAnim);
    }

    /**
     * fragment切换动画
     * @param layoutId
     * @param fragment
     * @param enterAnim
     * @param exitAnim
     */
    public void fragmentAnim(@IdRes int layoutId, Fragment fragment, @AnimRes int enterAnim, @AnimRes int exitAnim){
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(enterAnim, exitAnim)
                .replace(layoutId, fragment)
                .commit();
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
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}

