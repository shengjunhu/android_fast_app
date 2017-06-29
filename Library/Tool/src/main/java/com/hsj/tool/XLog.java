package com.hsj.tool;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:39
 * @Class:Xlog
 * @Description: 日志工具类
 */
public class XLog {

    /**
     * 日志名称
     */
    private static String tag = "[Log]";
    /**
     * 日志文件输出格式
     */
    private static SimpleDateFormat LOG_FILE_NAME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    /**
     * 日志文件路径
     */
    private static File logDir;
    /**
     * 日志打印行数
     */
    private static final int MAX_LENGTH = 3000;
    /**
     * 日志是否写到文件
     */
    private static boolean isLog2File;
    /**
     * 错误日志是否写到文件
     */
    private static boolean isErrorLog2File;
    /**
     * 日志保存时间（day）
     */
    private static final int LOG_SAVE_TIME = 3;
    /**
     * 是否是调试
     */
    private static boolean isDebug;

    private XLog() {

    }

    /**
     * 在Application中初始化Log
     *
     * @param isDebug         - 是否是调试
     * @param isErrorLog2File - 是否将错误日志写入文件
     * @param logDir          - 写入文件路径
     * @return
     */
    public static void initLog(boolean isDebug,boolean isErrorLog2File, File logDir) {
        XLog.isDebug         = isDebug;
        XLog.isErrorLog2File = isErrorLog2File;
        XLog.logDir          = logDir;

        checkLogFile();
    }

    /************************************** Debug： 调试 *******************************************/
    public static void d(Object msg) {
        d(tag, msg);
    }

    public static void d(String tag, Object msg) {
        d(tag, msg, null);
    }

    public static void d(String tag, Object msg, Throwable tr) {
        print(tag, msg.toString(), tr, 'd');
    }

    /**************************************** Warn：警告 *******************************************/
    public static void w(Object msg) {
        w(tag, msg);
    }

    public static void w(String tag, Object msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, Object msg, Throwable tr) {
        print(tag, msg.toString(), tr, 'w');
    }

    /************************************* Error： 错误 ********************************************/
    public static void e(Object msg) {
        e(tag, msg);
    }

    public static void e(String tag, Object msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, Object msg, Throwable tr) {
        print(tag, msg.toString(), tr, 'e');
    }

    /**
     * 打印日志
     *
     * @param tag
     * @param msg
     * @param tr
     */
    private static void print(String tag, Object msg, Throwable tr, char LOG_TYPE) {
        if (isDebug) {//调试：在控制台输出日志即可

            //TODO 输出日志

        } else if (isErrorLog2File) {//非调试：错误日志写文件

            //TODO 输出日志

        }
    }

    /**
     * 检查过期日志、并删除
     */
    private static void checkLogFile() {

    }

    /**
     * Print log for define method. When information is too long, the Logger can also complete printing. The
     * equivalent of "{@code android.util.Log.i("Tag", "Message")}" "{@code Logger.print("i",
     * "Tag", "Message")}".
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
     * Through the reflection to call the print method.
     *
     * @param method  such as "{@code v, i, d, w, e, wtf}".
     * @param tag     tag.
     * @param message message.
     */
    private static void invokePrint(String method, String tag, String message) {
        try {
            Class<android.util.Log> logClass = android.util.Log.class;
            Method logMethod = logClass.getMethod(method, String.class, String.class);
            logMethod.setAccessible(true);
            logMethod.invoke(null, tag, message);
        } catch (Exception e) {
            System.out.println(tag + ": " + message);
        }
    }

    /**
     * Print log for define method. When information is too long, the Logger can also complete printing. The
     * equivalent of "{@code android.util.Log.i("Tag", "Message")}" "{@code Logger.print("i",
     * "Tag", "Message")}".
     *
     * @param method such as "{@code v, i, d, w, e, wtf}".
     * @param o      message.
     * @param e      error.
     */
    private static void print(String method, Object o, Throwable e) {
        print(method, tag, o.toString(), e);
    }

    /**
     * Print log for define method. When information is too long, the Logger can also complete printing. The
     * equivalent of "{@code android.util.Log.i("Tag", "Message")}" "{@code Logger.print("i",
     * "Tag", "Message")}".
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
     * Through the reflection to call the print method.
     *
     * @param method  such as "{@code v, i, d, w, e, wtf}".
     * @param tag     tag.
     * @param message message.
     * @param e       error.
     */
    private static void invokePrint(String method, String tag, String message, Throwable e) {
        try {
            Class<android.util.Log> logClass = android.util.Log.class;
            Method logMethod = logClass.getMethod(method, String.class, String.class, Throwable.class);
            logMethod.setAccessible(true);
            logMethod.invoke(null, tag, message, e);
        } catch (Exception e1) {
            System.out.println(tag + ": " + message);
        }
    }

}
