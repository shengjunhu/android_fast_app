/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.app.common.utils;

import android.support.annotation.NonNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2018/2/1/14:33
 * @Version:V1.0
 * @Class:TimeUtils
 * @Description:时间格式化工具类
 */
public class TimeUtils {

    //////////////////////////////////////////////////////////////
    // TimeUtils 功能如下：
    //      1、格式化时间戳
    //      2、格式化系统当前时间
    //      3、格式化的时间转时间戳
    //      4、定时器：CountDownTimer;
    //////////////////////////////////////////////////////////////

    /**
     * 一分钟的毫秒值
     */
    private static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值
     */
    private static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值
     */
    private static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 两天天的毫秒值
     */
    private static final long TWO_DAY = 48 * ONE_HOUR;

    /**
     * 三天的毫秒值
     */
    private static final long THREE_DAY = 72 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    private static final long ONE_MONTH = 30 * TWO_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    private static final long ONE_YEAR = 12 * ONE_MONTH;

    /**
     * 社区时间的文字描述：（HH为24小时制，hh为12小时制）
     * "刚刚"、"x分钟前"、"今天/昨天/前天 HH:mm"、"MM月dd日 HH:mm"（今年）、"yyyy年MM月dd日 HH:mm"（非今年）
     */
    public static String timeFormat(long timeTamp) {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - timeTamp;
        String updateAtValue = "";

        if (timePassed < ONE_MINUTE) {
            updateAtValue = "刚刚";
        } else if (timePassed < ONE_HOUR) {
            long timeIntoFormat = timePassed / ONE_MINUTE;
            updateAtValue = timeIntoFormat + "分钟前";
        } else if (timePassed < TWO_DAY) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date zero = calendar.getTime();
            long timeIntoFormat = zero.getTime();
            if (timeTamp < timeIntoFormat) {
                updateAtValue = "昨天 " + new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date(timeTamp));
            } else {
                updateAtValue = "今天 " + new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date(timeTamp));
            }
        } else if (timePassed < THREE_DAY) {
            updateAtValue = "前天 " + new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date(timeTamp));
        } else if (timePassed < ONE_YEAR) {//一年以内
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.YEAR,0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date zero = calendar.getTime();
            long timeIntoFormat = zero.getTime();
            if (timeTamp < timeIntoFormat){//去年
                updateAtValue = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA).format(new Date(timeTamp));
            }else {//今年
                updateAtValue = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA).format(new Date(timeTamp));
            }
        } else {//大于一年
            updateAtValue = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA).format(new Date(timeTamp));
        }
        return updateAtValue;
    }

    /**
     * 当前时间格式(24小时制)
     *
     * @return
     */
    public static String currentTimeFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(new Date());
    }

    /**
     * 格式时间转毫秒值(24小时制)
     *
     * @return
     */
    public static long formatToTamp(@NonNull String formatTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            Date date = simpleDateFormat.parse(formatTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
