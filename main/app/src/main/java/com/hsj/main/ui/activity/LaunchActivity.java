/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.hsj.main.ui.activity;

import android.os.Bundle;
import android.view.View;
import com.app.R;
import com.hsj.common.ui.activity.BaseActivity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27/15:26
 * @Class:AppLaunchActivity
 * @Description:启动页
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        //进入欢迎页或者广告页
        //startActivity(new Intent(this, WelActivity.class));
        LaunchActivity.this.finish();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void initData() {

    }
}
