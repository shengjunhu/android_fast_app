package com.hsj.app.base.uitls;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:41
 * @Class:BaseTool
 * @Description:基本工具类
 */
public class BaseTool {

    /**
     * 功能如下：
     *
     * 1、不为null 或者 ""
     * 2、当前所在线程
     * 3、EditText、TextView设置文本
     * 4、是否是手机号
     * 5、格式化当前时间
     *
     */

    /**
     * 判断字符串是否为null 或 ""
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return TextUtils.isEmpty(str);
    }

    /**
     * EditText、TextView设置文本
     * @param str
     * @return
     */
    public String setStr(String str){
        if(TextUtils.isEmpty(str)){
            return "";
        }else {
            return str;
        }
    }

    /**
     * 当前时间格式
     * @return
     */
    public static String timeFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(new Date());
    }

    /**
     * 手机号码验证
     *
     * @param phoneNum
     * @return -- false表示号码为null或者不是手机号码
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (isEmpty(phoneNum)) return false;

        String regExp0 = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Matcher mMatcher0 = Pattern.compile(regExp0).matcher(phoneNum);

        return mMatcher0.matches();
    }

}
