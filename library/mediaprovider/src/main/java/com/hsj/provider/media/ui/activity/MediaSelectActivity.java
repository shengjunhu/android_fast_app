package com.hsj.provider.media.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.hsj.common.ui.activity.BaseActivity;
import com.hsj.provider.media.R;

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
        getTitleCenter().setText("");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {

    }

}
