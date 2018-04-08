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

package com.hsj.common;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hsj.common.thread.ThreadManager;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017-06-15 23:50
 * @Class:BaseMainActivity
 * @Description:common测试类
 */
public class BaseMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main);

        initUI();

        initData();
    }

    private void initUI() {

    }

    @Override
    public void onClick(View v) {

    }

    private void initData() {

        //执行网络线程
        ThreadManager.executeOnNetWorkThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        //执行文件读写线程
        ThreadManager.executeOnSubThread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

}
