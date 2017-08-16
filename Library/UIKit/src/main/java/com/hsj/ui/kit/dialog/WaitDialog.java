package com.hsj.ui.kit.dialog;

import android.app.Activity;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/2 17:06
 * @Class:LoadDialog
 * @Description:网络加载、耗时处理提示对话框
 */
public class WaitDialog {

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
