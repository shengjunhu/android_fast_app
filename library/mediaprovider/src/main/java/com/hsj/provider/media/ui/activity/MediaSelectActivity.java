package com.hsj.provider.media.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.hsj.common.ui.activity.BaseActivity;
import com.hsj.provider.media.R;
import com.hsj.provider.media.ui.fragment.MediaSelectFragment;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/9 09:20
 * @Class:MediaSelectActivity
 * @Description:图片、视频选择
 */
public class MediaSelectActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_media_select;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        getTitleCenter().setText("最近照片");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            requestRunPermission();
        } else {
            //replaceFragment();
        }
    }

    private void requestRunPermission(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, 101);
    }

    /**
     * 使用fragment提高内存申请
     */
    private void replaceFragment() {
        MediaSelectFragment fragment = new MediaSelectFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_base, fragment)
                .commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //replaceFragment();
                } else {
                    showToast("应用需要运行权限！");
                    requestRunPermission();
                }
            }
        }
    }

}
