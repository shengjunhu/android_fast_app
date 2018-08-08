package com.hsj.common.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsj.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:AppBaseActivity
 * @Description:Activity基类：初始化UI、初始化数据、强制刷新数据、生命周期控制
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public String TAG = this.getClass().getSimpleName();
    private boolean isShowTitle = true;
    private TextView tv_left, tv_center, tv_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_main);

        if (isShowTitle) {
            Toolbar toolbar = super.findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            tv_left = super.findViewById(R.id.tv_left);
            tv_center = super.findViewById(R.id.tv_center);
            tv_right = super.findViewById(R.id.tv_right);
        } else {
            super.findViewById(R.id.toolbar).setVisibility(View.GONE);
        }

        FrameLayout mContentView = super.findViewById(R.id.fl_base);
        View view = LayoutInflater.from(this).inflate(getLayoutId(), null);
        mContentView.addView(view);

        initUI(savedInstanceState);

        initData();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void initData();

    public void setShowTitle(boolean showTitle) {
        isShowTitle = showTitle;
    }

    public TextView getTitleLeft() {
        tv_left.setOnClickListener(this);
        return tv_left;
    }

    public TextView getTitleCenter() {
        return tv_center;
    }

    public TextView getTitleRight() {
        tv_right.setOnClickListener(this);
        return tv_right;
    }

    /**
     * 权限
     *
     * @param requestCode
     * @param permissions
     */
    protected void requestPermissions(@IntRange(from = 0) int requestCode, @NonNull String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionResponse(requestCode, true);
            return;
        }

        List<String> requestPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions.add(permission);
            }
        }

        if (requestPermissions.size() > 0) {
            ActivityCompat.requestPermissions(this, requestPermissions.toArray(new String[]{}), requestCode);
        } else {
            permissionResponse(requestCode, true);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                permissionResponse(requestCode, false);
                showPermissionWarn();
                return;
            }
        }
        permissionResponse(requestCode, true);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 复写此方法读取回调
     *
     * @param requestCode
     * @param granted
     */
    protected void permissionResponse(int requestCode, boolean granted) {

    }

    /**
     * 权限被拒绝一次的提示对话框
     */
    private void showPermissionWarn() {

    }

    /**
     * 检测内存泄露
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
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
    protected void startProgressDialog(@Nullable String hint) {

    }

    /**
     * 结束提示dialog
     */
    protected void stopProgressDialog() {

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

    /**
     * 文本不随系统字体增大儿增大
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 失去焦点，软键盘自动收起
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus() && event.getAction() == MotionEvent.ACTION_UP) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (null != mInputMethodManager) {
                return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        }
        return super.onTouchEvent(event);
    }

}
