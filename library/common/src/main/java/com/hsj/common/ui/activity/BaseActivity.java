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
import com.hsj.common.core.AppManager;
import com.hsj.common.core.BaseConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:AppBaseActivity
 * @Description:Activity基类：初始化UI、初始化数据、强制刷新数据、生命周期控制
 */
public abstract class BaseActivity extends CommonActivity implements View.OnClickListener {

    private TextView tv_left, tv_center, tv_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_main);

        Toolbar toolbar = super.findViewById(R.id.toolbar);
        setAppTheme(toolbar);
        setSupportActionBar(toolbar);
        tv_left = super.findViewById(R.id.tv_left);
        tv_center = super.findViewById(R.id.tv_center);
        tv_right = super.findViewById(R.id.tv_right);
        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);

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

    public TextView getTitleLeft() {
        return tv_left;
    }

    public TextView getTitleCenter() {
        return tv_center;
    }

    public TextView getTitleRight() {
        return tv_right;
    }

    /**
     * 设置App主题
     */
    public void setAppTheme(Toolbar toolbar) {
        switch (BaseConstants.appTheme) {
            case 0:
                setTheme(R.style.AppThemeWhite);
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorThemeWhite));
                break;
            case 1:
                setTheme(R.style.AppThemeBlack);
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorThemeBlack));
                break;
            case 2:
                setTheme(R.style.AppThemePink);
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorThemePink));
                break;
            case 3:
                setTheme(R.style.AppThemeOrange);
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorThemeOrange));
                break;
            case 4:
                setTheme(R.style.AppThemeBlue);
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorThemeBlue));
                break;
            case 5:
                setTheme(R.style.AppThemeGreen);
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorThemeGreen));
                break;
            case 6:
                setTheme(R.style.AppThemePurple);
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorThemePurple));
                break;
            default:
                break;
        }
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
     * 刷新数据
     */
    public void refresh() {
        initData();
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

}
