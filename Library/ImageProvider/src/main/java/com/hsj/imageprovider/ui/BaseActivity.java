package com.hsj.imageprovider.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * @Company:****息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/4/5 20:11
 * @Version:FastAndroid V21.0
 * @Class:BaseActivity
 * @Description: 权限检测
 */
public class BaseActivity extends AppCompatActivity {

    private final int CHECK_PERMISSION_CODE = 100;

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
     * 复写读取图片、调用camera方法，
     */
    public void getImage() {

    }

    /**
     * 检测权限
     */
    public void checkPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {//检查有没有授权

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {//被拒绝后的再次提醒
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CHECK_PERMISSION_CODE);

            } else {//初次申请权限
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CHECK_PERMISSION_CODE);
            }

        } else {//已授权
            getImage();
        }

    }

    public void showToast(@NonNull String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CHECK_PERMISSION_CODE) {
            if (grantResults.length == 2
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getImage();
            } else {
                showToast("您拒绝了读取图片和启动相机的权限");
            }
        }
    }


}
