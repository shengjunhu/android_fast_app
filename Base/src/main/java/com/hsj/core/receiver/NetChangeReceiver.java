package com.hsj.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * @Company:北京****科技有限公司
 * @Author:HSJ
 * @Version:FastAndroid V1.0
 * @Date:2017/3/24 11:08
 * @E-mail:mr.ajun@foxmail.com
 * @Class:NetChangeReceiver
 * @Description:网络变化广播
 */
public class NetChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {


        }
    }

}
