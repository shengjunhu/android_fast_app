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

package com.hsj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.hsj.app.R;
import com.hsj.base.ui.BaseActivity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/7/24/10:09
 * @Class:AdvertActivity
 * @Description:广告页
 */
public class AdvertActivity extends BaseActivity implements Runnable{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advert;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void initData() {
        getWindow().getDecorView().postDelayed(this, 5000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, WelcomeActivity.class));
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public void onDestroy() {
        getWindow().getDecorView().removeCallbacks(this);
        super.onDestroy();
    }

}
