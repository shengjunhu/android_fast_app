package com.hsj.basetool.helper;

import android.os.Looper;
import android.widget.Toast;
import com.hsj.basetool.base.BaseToolContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseHelper {

    /**
     * Toast方法
     *
     * @param str
     */
    public static void showToast(String str) {
        Toast.makeText(BaseToolContext.mContext, str + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * 当前线程
     *
     * @return -- true为主线程
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * 当前时间格式
     *
     * @return
     */
    public static String DateForm() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date currentTime = new Date(System.currentTimeMillis());

        return formatter.format(currentTime);
    }

    /**
     * 手机号码验证
     *
     * @param phoneNum
     * @return -- false表示号码为null或者不是手机号码
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (isEmptyStr(phoneNum)) return false;

        String regExp0 = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Matcher mMatcher0 = Pattern.compile(regExp0).matcher(phoneNum);

        return mMatcher0.matches();
    }

    /**
     * 判断给定字符串是否空白串。
     *
     * @param input
     * @return boolean--null或者""返回true
     */
    public static boolean isEmptyStr(String input) {
        if (input == null || "".equals(input) || input.equals("null"))
            return true;

        return false;
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue--可为0
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 判断数组是否包含某个字符串
     *
     * @param arry
     * @param str
     * @return
     */
    public static boolean arrayContainStr(String[] arry, String str) {
        for (String string : arry) {
            if (string.contains(str.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断list是否为空
     *
     * @param list
     * @return boolean--为空返回true
     */
    public static boolean isEmptyList(List list) {
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断集合是否包含某个字符串
     *
     * @param list
     * @param str
     * @return -- 包含返回true
     */
    public static boolean listContainStr(List<String> list, String str) {
        for (String string : list) {
            if (string.equals(str.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串截切
     *
     * @param original--要剪切的字符串
     * @param str--切剪参考字符串
     * @param i--取剪切后的0前一部分，非0后一部分
     * @return
     */
    public static String spiltStr(String original, String str, int i) {
        if (isEmptyStr(original) | isEmptyStr(str)) {
            return null;
        } else if (i != 0) {
            i = 1;
        }
        return original.split(str)[i];
    }


}
