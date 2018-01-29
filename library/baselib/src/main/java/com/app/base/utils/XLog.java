package com.app.base.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:39
 * @Class:Xlog
 * @Description:日志工具类
 */
public class XLog {

    /**
     * 日志总开关
     */
    private static boolean isLogEnable = true;
    /**
     * 调试状态
     */
    private static boolean isDebug = true;
    /**
     * 发布状态 是否输错误日志
     */
    private static boolean isErrorLogEnable = true;
    /**
     * 日志打印行数
     */
    private static final int MAX_LENGTH = 3000;
    /**
     * 日志输出类型
     */
    private static char LOG_TYPE = 'v';

    /**
     * 日志名称
     */
    private static String TAG = "[Log]";

    /**
     * 日志存放目录
     */
    private static File logDir;
    /**
     * 日志目录名称
     */
    private static final SimpleDateFormat LOG_DIR = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    /**
     * 日志文件名
     */
    private static final SimpleDateFormat LOG_NAME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    /**
     * 日志保存时间（day）
     */
    private static final int LOG_SAVE_DAYS = 3;

    private XLog() {

    }

    /**
     * 在Application中初始化Log
     */
    public static void startLog(Context context) {
        getLogDir(context);
        deleteLogFile();
    }

    /****************************************** Warn：警告 *********************************************/

    public static void w(Object msg) {
        w(TAG, msg);
    }

    public static void w(String tag, Object msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'w');
    }

    /******************************************** Error： 错误******************************************/

    public static void e(Object msg) {
        e(TAG, msg);
    }

    public static void e(String tag, Object msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'e');
    }

    /******************************************* Debug： 调试*******************************************/

    public static void d(Object msg) {
        d(TAG, msg);
    }

    public static void d(String tag, Object msg) {
        d(tag, msg, null);
    }

    public static void d(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'd');
    }

    /********************************************** Info *********************************************/

    public static void i(Object msg) {
        i(TAG, msg);
    }

    public static void i(String tag, Object msg) {
        i(tag, msg, null);
    }

    public static void i(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'i');
    }

    /********************************************* Verbose ********************************************/

    public static void v(Object msg) {
        v(TAG, msg);
    }

    public static void v(String tag, Object msg) {
        v(tag, msg, null);
    }

    public static void v(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'v');
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     */
    private static void log(String tag, String msg, Throwable tr, char level) {
        if (!isLogEnable) return;

        if (isDebug) {//输出日志开关
            if ('e' == level && ('e' == LOG_TYPE || 'v' == LOG_TYPE)) {         //e输出优先级最高
                print("e", tag, msg, tr);
            } else if ('w' == level && ('w' == LOG_TYPE || 'v' == LOG_TYPE)) {  //w输出优先级第二
                print("e", tag, msg, tr);
            } else if ('d' == level && ('d' == LOG_TYPE || 'v' == LOG_TYPE)) {  //d输出优先级第三
                print("d", tag, msg);
            } else if ('i' == level && ('d' == LOG_TYPE || 'v' == LOG_TYPE)) {  //i输出有限级第四
                print("i", tag, msg);
            } else {
                print("v", tag, msg);
            }
        }

        if (isErrorLogEnable) {//错误日志记录文件
            log2File(tag, String.valueOf(level), msg + tr == null ? "" : "\n" + Log.getStackTraceString(tr));
        }
    }

    /**
     * 自定义打印日志方法，当信息比较长时日志也能完全的打印出来
     *
     * @param method  such as "{@code v, i, d, w, e, wtf}".
     * @param tag     tag.
     * @param message message.
     */
    private static void print(String method, String tag, String message) {
        int strLength = message.length();
        if (strLength == 0)
            invokePrint(method, tag, message);
        else {
            for (int i = 0; i < strLength / MAX_LENGTH + (strLength % MAX_LENGTH > 0 ? 1 : 0); i++) {
                int end = (i + 1) * MAX_LENGTH;
                if (strLength >= end) {
                    invokePrint(method, tag, message.substring(end - MAX_LENGTH, end));
                } else {
                    invokePrint(method, tag, message.substring(end - MAX_LENGTH));
                }
            }
        }
    }

    /**
     * 自定义打印日志方法，当信息比较长时日志也能完全的打印出来
     *
     * @param method such as "{@code v, i, d, w, e, wtf}".
     * @param o      message.
     * @param e      error.
     */
    private static void print(String method, Object o, Throwable e) {
        print(method, TAG, o.toString(), e);
    }

    /**
     * 自定义打印日志方法，当信息比较长时日志也能完全的打印出来
     *
     * @param method  such as "{@code v, i, d, w, e, wtf}".
     * @param tag     tag.
     * @param message message.
     * @param e       error.
     */
    private static void print(String method, String tag, String message, Throwable e) {
        invokePrint(method, tag, message, e);
    }

    /**
     * 通过映射来调用打印日志
     *
     * @param method  such as "{@code v, i, d, w, e, wtf}".
     * @param tag     tag.
     * @param message message.
     */
    private static void invokePrint(String method, String tag, String message) {
        try {
            Class<Log> logClass = Log.class;
            Method logMethod = logClass.getMethod(method, String.class, String.class);
            logMethod.setAccessible(true);
            logMethod.invoke(null, tag, message);
        } catch (Exception e) {
            System.out.println(tag + ": " + message);
        }
    }

    /**
     * 通过映射来调用打印日志
     *
     * @param method  such as "{@code v, i, d, w, e, wtf}".
     * @param tag     tag.
     * @param message message.
     * @param e       error.
     */
    private static void invokePrint(String method, String tag, String message, Throwable e) {
        try {
            Class<Log> logClass = Log.class;
            Method logMethod = logClass.getMethod(method, String.class, String.class, Throwable.class);
            logMethod.setAccessible(true);
            logMethod.invoke(null, tag, message, e);
        } catch (Exception e1) {
            System.out.println(tag + ": " + message);
        }
    }

    /**
     * 将日志写入文件: IO线程
     *
     * @param logType log类型（e\w\d\i\v）
     * @param tag
     * @param text
     */
    private static void log2File(String tag, String logType, String text) {
        Date currentTime = new Date();

        // 目录名称20170920
        String dir = LOG_DIR.format(currentTime);
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }

        //文件名称：2017-09-20 08:00:00
        file = new File(file, LOG_NAME.format(currentTime));

        // 日志输出内容格式
        String logContent = tag + ":" + logType + ":" + text;
        try {
            FileWriter filerWriter = new FileWriter(file, true);
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(logContent);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除三天前的目录：IO线程
     */
    private static void deleteLogFile() {
        //三天前日志目录：20170920
        String deleteLogDir = LOG_DIR.format(getDateBefore());
        long dir = Long.valueOf(deleteLogDir);
        if (logDir != null && logDir.exists()) {
            File[] files = logDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    String dirName = file.getName();
                    long dir1 = Long.valueOf(dirName);
                    if (dir > dir1) {
                        FileManager.deleteDir(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * 得到LOG_SAVE_DAYS天前的日期
     *
     * @return
     */
    private static Date getDateBefore() {
        Date time = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(time);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - LOG_SAVE_DAYS);
        return now.getTime();
    }

    /**
     * 获取日志存放目录: cache/log/
     *
     * @param context
     */
    private static void getLogDir(Context context) {
        logDir = FileManager.getCacheDir(context, "log");
    }

}
