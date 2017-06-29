package com.hsj.tool.core;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:41
 * @Class:PhoneManager
 * @Description:手机相关工具类
 */
public class PhoneManager {

    /**
     * SD卡是否可用
     * @return
     */
    public static boolean isSd() {
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

    /**
     * 获取手机品牌
     *
     * @return
     */
    private String phoneBrand() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    private String phoneModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取手机的ID
     *
     * @return 设备ID
     */
    public String getPhoneId(Context context) {
        try {
            return Build.SERIAL;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取手机号码
     *
     * @return --返回手机号码
     */
    private String getPhoneNumber(@NonNull Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getLine1Number();
        }catch (Exception e){
            return "";
        }
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context -- 传入XBSApplication.appContext
     * @param spVal
     * @return
     */
    private int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context -- 传入XBSApplication.appContext
     * @param pxVal
     * @return
     */
    private float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context -- 传入XBSApplication.appContext
     * @param pxVal
     * @return
     */
    private float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获取当前手机的屏幕尺寸
     *
     * @param context
     * @return
     */
    private int[] getResolution(Context context) {
        if (context == null) return null;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int resolution[] = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        resolution[0] = dm.widthPixels;
        resolution[1] = dm.heightPixels;
        return resolution;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    private int getStatusHeight(@NonNull Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取网络状态
     *
     * @return 0为网络不可用、1为移动网络、2为Wifi网络
     */
    public int getNetworkType(@NonNull Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if(mNetworkInfo == null){
            return 0;
        }else if(mNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            return 2;
        }else if(mNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 判断GPS是否打开 (ACCESS_FINE_LOCATION权限)
     *
     * @param context
     * @return
     */
    private boolean isGPSAvailable(@NonNull Context context) {
        try {
            LocationManager locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
