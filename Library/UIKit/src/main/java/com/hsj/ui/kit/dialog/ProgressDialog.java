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

package com.hsj.ui.kit.dialog;

import android.app.Activity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/8/16 11:15
 * @Class:ProgressDialog
 * @Description:等待提示
 */
public class ProgressDialog {

    private Activity activity;
    private String msg = " 请等待...";

    /**
     * 开始Dialog
     * @param activity
     */
    private void start(Activity activity){
        this.activity = activity;
    }

    /**
     * 开始Dialog
     * @param activity
     * @param hitMsg
     */
    private void start(Activity activity,String hitMsg){
        this.activity = activity;
    }

    /**
     * 操作成功
     * @param successMsg
     */
    private void onSucces(String successMsg){

    }

    /**
     * 操作失败
     * @param failureMsg
     */
    private void onFailure(String failureMsg){

    }

    /**
     *  停止Dialog
     */
    private void stop(){

    }


}
