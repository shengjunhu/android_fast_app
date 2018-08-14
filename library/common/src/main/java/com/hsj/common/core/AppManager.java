package com.hsj.common.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 16:41
 * @Class:AppManager
 * @Description:收集AppActivity
 */
public class AppManager {

    private AppManager() {
    }

    private static final AppManager appManager = new AppManager();

    /**
     * 实例化 AppManager
     *
     * @return
     */
    public static AppManager getInstance() {
        return appManager;
    }

    /**
     * 初始化、所有线程都捕获错误信息
     */
    public void init(final Context context) {
        Thread.setDefaultUncaughtExceptionHandler(
                (thread, ex) -> new Thread(() -> {
                    ex.printStackTrace();
                    Looper.prepare();
                    showCrashDialog();
                    Looper.loop();
                }).start());
    }

    /**
     * Stack 是先入后出/ 后入先出的集合，
     */
    private Stack<SoftReference<Activity>> mActivityStack = new Stack<>();

    /**
     * 给Activity在onCreate用的
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityStack.add(new SoftReference<>(activity));
    }

    /**
     * 给Activity在onDestroy用的
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            if (mActivityStack.get(i).get() == activity) {
                mActivityStack.remove(i);
                return;
            }
        }
    }

    /**
     * 给MyApp用来获取最顶端的Activity用的
     *
     * @return
     */
    public Activity getTopActivity() {
        if (mActivityStack.isEmpty()) {
            return null;
        }
        return mActivityStack.peek().get();
    }

    /**
     * app crash 弹出自定义对话框(app crash 系统会弹出对话框，拦截系统对话框)
     */
    private void showCrashDialog() {
        final Activity topActivity = AppManager.getInstance().getTopActivity();
        if (topActivity == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(topActivity);
        builder.setTitle("未知错误");
        builder.setMessage("很抱歉，程序发生未知错误，我们会迅速修复！");
        builder.setPositiveButton("知  道", (dialog, which) -> System.exit(1));
        builder.show();
    }

}
