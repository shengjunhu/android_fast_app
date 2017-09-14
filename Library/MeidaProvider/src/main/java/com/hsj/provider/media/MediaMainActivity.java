/*******************************************************************************
 * Copyright (c) 2017.   ShengJunHu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 ******************************************************************************/

package com.hsj.provider.media;

import android.os.Bundle;
import android.view.View;
import com.hsj.base.ui.BaseActivity;

public class MediaMainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        findView(R.id.btn_photo).setOnClickListener(this);
        findView(R.id.btn_take_photo).setOnClickListener(this);
        findView(R.id.btn_video).setOnClickListener(this);
        findView(R.id.btn_record_video).setOnClickListener(this);
        findView(R.id.btn_photo_video).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_photo:
                selectPhoto();
                break;
            case R.id.btn_take_photo:
                takePhoto();
                break;
            case R.id.btn_video:
                selectVideo();
                break;
            case R.id.btn_record_video:
                recordVideo();
                break;
            case R.id.btn_photo_video:
                selectPhotoVideo();
                break;
            default:
                break;
        }
    }

    private void selectPhoto() {

    }

    private void takePhoto() {

    }

    private void selectVideo() {

    }

    private void recordVideo() {

    }

    private void selectPhotoVideo() {

    }
}
