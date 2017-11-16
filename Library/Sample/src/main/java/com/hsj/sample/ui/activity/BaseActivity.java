package com.hsj.sample.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/11/16/10:35
 * @Version:XBS V2.0
 * @Class:BaseActivity
 * @Description:
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
