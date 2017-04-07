package com.hsj.imageprovider.ui;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hsj.imageprovider.R;

/**
 * @Company:***科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/4/5 19:47
 * @Version:FastAndroid V1.0
 * @Class:ImageSelectActivity
 * @Description: 图片选择界面
 */
public class ImageSelectActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);

        initView();

        initData();
    }

    private void initView() {
        Toolbar toolbar = findView(R.id.toolbar);
        TextView tv_left = findView(R.id.tv_left);
        TextView tv_right = findView(R.id.tv_right);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }

    private void initData() {
        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        checkPermission(permission,100);
    }

    @Override
    public void hadPermission(int permissionCode) {
        Log.e("TAG", "hadPermission: ");
        if(permissionCode == 100){
            //TODO 1
            Log.e("TAG", "hadPermission: 1");

        }

        if(permissionCode == 101){
            //TODO 2
            Log.e("TAG", "hadPermission: 2");
        }

        super.hadPermission(permissionCode);
    }


}
