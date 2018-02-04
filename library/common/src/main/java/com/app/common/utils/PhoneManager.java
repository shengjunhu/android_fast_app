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

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.DimenRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.app.common.annotations.NetType;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:41
 * @Class:PhoneManager
 * @Description:手机相关工具类
 */
public class PhoneManager {

    //////////////////////////////////////////////////////////////
    // PhoneManager 缓存功能如下：
    //      1、获取设备品牌：android.os.Build.BRAND;
    //      2、设备的唯一标识：android.os.Build.FINGERPRINT;
    //      3、获取设备型号：android.os.Build.MODEL
    //      4、获取设备制造厂商：android.os.Build.MANUFACTURER
    //      5、获取系统版本号：android.os.Build.VERSION.SDK_INT
    //////////////////////////////////////////////////////////////

    private PhoneManager() {
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static float dp2px(@NonNull Context context, float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spValue
     * @return
     */
    public static float sp2px(@NonNull Context context, float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2dp(@NonNull Context context, float pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().density);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2sp(Context context, float pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获取设备分辨率( 内容显示分辨率)
     *
     * @param context
     */
    public static int[] getResolution(@NonNull Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int resolution[] = new int[2];
        resolution[0] = dm.widthPixels;
        resolution[1] = dm.heightPixels;
        return resolution;
    }

    /**
     * 获取设备分辨率（屏幕分辨率）
     *
     * @param context
     */
    public static int[] getScreenResolution(@NonNull Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int resolution[] = new int[2];
        resolution[0] = dm.widthPixels;
        resolution[1] = dm.heightPixels + getNavigationBarHeight(context);
        return resolution;
    }

    /**
     * 获取设备顶部状态栏高度
     *
     * @param context
     */
    public static int getStatusBarHeight(@NonNull Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 获取设备底部导航栏高度
     *
     * @param context
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 获取网络状态
     *
     * @return 0为网络不可用、1为移动网络、2为Wifi网络
     */
    public int getNetworkType(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return NetType.NETWORK_CLOSE;
        } else {
            NetworkInfo mNetworkInfo = cm.getActiveNetworkInfo();
            if (mNetworkInfo == null) {
                return NetType.NETWORK_UNAVAILABLE;
            } else if (mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return NetType.NETWORK_WIFI;
            } else if (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NetType.NETWORK_MOBILE;
            } else {
                return NetType.NETWORK_UNAVAILABLE;
            }
        }
    }

}
