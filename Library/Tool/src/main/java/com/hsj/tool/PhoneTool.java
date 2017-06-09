package com.hsj.tool;

import android.content.res.Resources;
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
    public boolean isSdUsable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取屏幕尺寸
     */
    public void getDisplay() {
        int widthPixels  = Resources.getSystem().getDisplayMetrics().widthPixels;
        int heightPixels = Resources.getSystem().getDisplayMetrics().heightPixels;
        float xdpi = Resources.getSystem().getDisplayMetrics().xdpi;
        float ydpi = Resources.getSystem().getDisplayMetrics().ydpi;
    }

}
