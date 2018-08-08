package com.hsj.common.ui.fragment;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:AppBaseFragment
 * @Description:普通Fragment、数据本地初始化
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    public String TAG = this.getClass().getSimpleName();

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {

            rootView = inflater.inflate(getLayoutId(), container, false);

            initUI(savedInstanceState);

            initData();
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void initData();

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
            if (ContextCompat.checkSelfPermission(this.getContext(), permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions.add(permission);
            }
        }

        if (requestPermissions.size() > 0) {
            ActivityCompat.requestPermissions(getActivity(), requestPermissions.toArray(new String[]{}), requestCode);
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * 检测内存泄露
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
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
     * 查找View
     *
     * @param id  控件的id
     * @param <V> View类型
     * @return
     */
    protected <V extends View> V findView(@IdRes int id) {
        return (V) rootView.findViewById(id);
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
     * 弹出Toast
     *
     * @param message
     */
    public void showToast(@NonNull String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 启动加载提示
     */
    protected void startDialog(String hint) {

    }

    /**
     * 停止加载提示
     */
    protected void stopDialog() {

    }

}
