package com.hsj.core.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/2/21 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  AppManager
 * @Description :  Activity管理和崩溃信息上传
 */
public class AppManager {

    private Context mContext = BaseToolContext.mContext;

    public static AppManager instance = new AppManager();

    public static AppManager getInstance() {
        return instance;
    }

    /**
     * 初始化、所有线程都捕获错误信息
     */
    public void init() {
        // 是所有的线程
        Thread.currentThread().setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, final Throwable ex) {
                Activity topActivity = getTopActivity();
                if (topActivity != null) { //顶栈Activity不为null(app 发生异常)
                    showErrorDialogInNonUiThread();
                    new Thread() {
                        @Override
                        public void run() {
                            // 生成错误报告，并上传到服务器
                            reportError(ex);
                            ex.printStackTrace();
                        }
                    }.start();
                } else {    //顶栈Activity为null(app crash)
                    new Thread() {
                        @Override
                        public void run() {
                            // 生成错误报告，并上传到服务器
                            reportError(ex);
                            ex.printStackTrace();
                            quitApp();
                        }
                    }.start();
                }
            }
        });
    }

    private void reportError(final Throwable ex) {
        // 生成错误报告：异常的类型、行号；应用的版本；用户的软硬件信息
        File file = saveReport(ex);
        // 上传错误报告的服务器
        uploadReport(file);
    }

    /**
     * 收集错误日志
     *
     * @param ex
     * @return
     */
    private File saveReport(Throwable ex) {
        File result = new File(mContext.getFilesDir(), "" + System.currentTimeMillis());
        FileWriter writer = null;
        PrintWriter printWriter = null;
        try {
            writer = new FileWriter(result);
            printWriter = new PrintWriter(writer);
            printWriter.append("========Device==========\n");
            printWriter.append(String.format("BOARD\t%s\n", Build.BOARD));
            printWriter.append(String.format("BOOTLOADER\t%s\n", Build.BOOTLOADER));
            printWriter.append(String.format("BRAND\t%s\n", Build.BRAND));
            printWriter.append(String.format("CPU_ABI\t%s\n", Build.CPU_ABI));
            printWriter.append(String.format("CPU_ABI2\t%s\n", Build.CPU_ABI2));
            printWriter.append(String.format("DEVICE\t%s\n", Build.DEVICE));
            printWriter.append(String.format("DISPLAY\t%s\n", Build.DISPLAY));
            printWriter.append(String.format("FINGERPRINT\t%s\n", Build.FINGERPRINT));
            printWriter.append(String.format("HARDWARE\t%s\n", Build.HARDWARE));
            printWriter.append(String.format("HOST\t%s\n", Build.HOST));
            printWriter.append(String.format("ID\t%s\n", Build.ID));
            printWriter.append(String.format("MANUFACTURER\t%s\n", Build.MANUFACTURER));
            printWriter.append(String.format("MODEL\t%s\n", Build.MODEL));
            printWriter.append(String.format("SERIAL\t%s\n", Build.SERIAL));
            printWriter.append(String.format("PRODUCT\t%s\n", Build.PRODUCT));
            printWriter.append("========APP==========\n");
            PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            printWriter.append(String.format("versionCode\t%s\n", versionCode));
            printWriter.append(String.format("versionName\t%s\n", versionName));
            printWriter.append("========Exception==========\n");
            ex.printStackTrace(printWriter);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
        return result;
    }

    /**
     * 上传错误报告
     *
     * @param report
     */
    private void uploadReport(File report) {
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            URL url = new URL("url/ErrorReportServlet");//服务器路径
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            os = urlConnection.getOutputStream();
            fis = new FileInputStream(report);//错误日志文件
            byte[] buf = new byte[1024 * 8];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            int responseCode = urlConnection.getResponseCode();
            Log.d("uploadReport", "" + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(os);
            close(fis);
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在界面展示对话框，拦截系统的对话框
     */
    private void showErrorDialogInNonUiThread() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                showErrorDialog();
                Looper.loop();
            }
        }.start();
    }

    /**
     * app crash 弹出自定义对话框(app crash 系统会弹出对话框，拦截系统对话框)
     */
    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AppManager.getInstance().getTopActivity());
        builder.setTitle("程序出错了");
        builder.setMessage("程序出错了，我们已经把错误报告给开发人员修复；非常抱歉对你带来的困扰，程序即将退出。");
        builder.setPositiveButton("知道了", mQuitOcl);
        builder.show();
    }

    /**
     * 退出 App
     */
    private DialogInterface.OnClickListener mQuitOcl = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //关闭所有Activity
            finishAllActivity();
            //退出App
            quitApp();
        }
    };

    /**
     * Stack 是先入后出/ 后入先出的集合，
     */
    private Stack<WeakReference<Activity>> mActivityStack = new Stack<>();

    /**
     * 给Activity在onCreate用的
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityStack.add(new WeakReference<Activity>(activity));
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
     * 给 App用来获取最顶端的Activity用的
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
     * 因为finish会导致onDestroy方法调用，会导致移除，所以要倒叙遍历
     */
    public void finishAllActivity() {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i).get();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 强制退出应用APP:
     *      1、使用killProcess完全退出
     *      2、使用System.exit(0)正常退出，非0异常退出。后台缓存进程依然在
     */
    public void quitApp() {
        Process.killProcess(Process.myPid());
        //System.exit(1);
    }

    /**
     * 应用是否退出
     *
     * @return -- true为退出
     */
    public boolean isAppExit() {
        return mActivityStack == null || mActivityStack.isEmpty();
    }

}
