package com.hsj.provider.media;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hsj.common.ui.activity.BaseActivity;
import com.hsj.provider.media.ui.activity.MediaSelectActivity;

public class MediaProviderMainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_media_provider_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        findViewById(R.id.btn).setOnClickListener(
                v->startActivity(new Intent(this, MediaSelectActivity.class)));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
