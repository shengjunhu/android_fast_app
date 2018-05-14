package com.hsj.ui.controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/12/8/13:08
 * @Class:NotificationNumberController
 * @Description:桌面图标读消息数量提示
 */
public class NotificationNumberController {

    /**
     * 检测系统，适配桌面图标读消息数量提示兼容适配
     * 1、Android原生系统，采用原生方案或自定义方案
     * 2、Android国产ROM，官方提供API，采用官方API
     * 3、Android国产ROM，官方没提供API，采用原生方案或自定义方案
     */
    public void setNotification(@NonNull Context context, int count){

    }

    /**
     * Google Android /小型国产android
     */
    private void android(@NonNull Context context, int count){

    }

    /**
     * 华为系统
     * http://developer.huawei.com/consumer/cn/wiki/index.php?title=%E5%8D%8E%E4%B8%BA%E6%A1%8C%E9%9D%A2%E8%A7%92%E6%A0%87%E5%BC%80%E5%8F%91%E6%8C%87%E5%AF%BC%E4%B9%A6
     */
    private void emui(@NonNull Context context, int count) {
        try {
            // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
            PackageInfo packageinfo = null;
            try {
                packageinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (packageinfo == null) {
                return;
            }
            // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(packageinfo.packageName);

            // 通过getPackageManager()的queryIntentActivities方法遍历
            List<ResolveInfo> resolveInfoList = context.
                    getPackageManager().queryIntentActivities(resolveIntent, 0);

            ResolveInfo resolveinfo = resolveInfoList.iterator().next();
            if (resolveinfo != null) {
                String launchActivityName = resolveinfo.activityInfo.name;

                Bundle bunlde = new Bundle();
                bunlde.putString("package", context.getPackageName());
                bunlde.putString("class", launchActivityName); //对应启动页activity
                bunlde.putInt("badgenumber", count);
                context.getContentResolver().call(Uri.parse(
                        "content://com.huawei.android.launcher.settings/badge/"),
                        " change_badge", null, bunlde);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 小米系统
     * https://dev.mi.com/console/doc/detail?pId=939
     */
    private void miui(@NonNull Context context, int count) {
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle("title")
                .setContentText("text")
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        Notification notification = builder.build();
        try {
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNotificationManager.notify(0, notification);
    }

}
