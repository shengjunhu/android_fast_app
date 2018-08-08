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

package com.hsj.web;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tencent.smtt.sdk.QbSdk;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2018/3/21/13:32
 * @Version:V1.0
 * @Class:WebContext
 * @Description:初始化Web
 */
public final class WebContext {

    public void initWeb(@NonNull Context context) {

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // x5內核初始化完成的回调，为true表示x5内核加载成功，
                // x5內核初始化完成的回调，为false表示x5内核加载失败，会自动切换到系统内核。
            }

            @Override
            public void onCoreInitFinished() {
                // web内核加载完毕
            }
        };

        //x5内核初始化接口
        QbSdk.initX5Environment(context, cb);

    }

}
