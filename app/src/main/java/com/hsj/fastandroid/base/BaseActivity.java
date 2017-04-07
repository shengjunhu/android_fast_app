package com.hsj.fastandroid.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * @Company:****科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/4/7 12:14
 * @Version:XBS V2.0
 * @Class:BaseActivity
 * @Description:
 */
public class BaseActivity extends AppCompatActivity {

    private int CHECK_PERMISSION_CODE = 100;

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
     * Toast
     *
     * @param msg
     */
    public void showToast(@NonNull String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 复写读取图片、调用camera方法，（子类复写该方法即可）
     */
    public void hadPermission() {

    }

    /**
     * 需要检测的权限数组
     *
     * @param permission
     */
    public void checkPermission(String[] permission) {
        if (ActivityCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED) {//检查有没有授权

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {//被拒绝后的再次提醒
                showToast("当前操作需要以下权限!");
                ActivityCompat.requestPermissions(this, permission, CHECK_PERMISSION_CODE);
            } else {//初次申请权限
                ActivityCompat.requestPermissions(this, permission, CHECK_PERMISSION_CODE);
            }

        } else {//已授权
            hadPermission();
        }

    }

    /**
     * 权限申请结果回掉
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CHECK_PERMISSION_CODE) {
            boolean flag = true;
            for (int i = 0; i < grantResults.length; i++) {
                flag = flag && (grantResults[i] == 0);
            }

            if (flag) {
                hadPermission();
            } else {
                showToast("权限被拒绝，无法进行以上操作!");
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
