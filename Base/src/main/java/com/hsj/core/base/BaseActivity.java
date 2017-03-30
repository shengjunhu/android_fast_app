package com.hsj.core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @Company:北京****科技有限公司
 * @Author:HSJ
 * @Version:FastAndroid V1.0
 * @Date:2017/2/21 12:54
 * @E-mail:mr.ajun@foxmail.com
 * @Class:BaseActivity
 * @Description:项目Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    /**
     * Toast
     * @param msg
     */
    public void showToast(@NonNull String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断字符串为空
     *
     * @param str
     * @return false 为（null、""）
     */
    public boolean isEmpty(@Nullable String str){
        return TextUtils.isEmpty(str);
    }

    /**
     * 判断对象是否为null
     * @param obj
     * @return
     */
    public boolean isNull(@Nullable Object obj){
        return null == obj ? true:false;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        System.gc();
    }

}
