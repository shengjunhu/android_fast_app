package com.hsj.tool;

import android.os.Environment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:41
 * @Class:PhoneTool
 * @Description: 手机相关工具类
 */
public class PhoneTool {

    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean isSdUsable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

}
