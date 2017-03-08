package com.hsj.basetool.tool;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import com.hsj.basetool.base.BaseToolContext;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/2/21 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  PhoneManager
 * @Description :  设备信息帮助者
 */
public class PhoneManager {

    public static Context mContext = BaseToolContext.mContext;

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String phoneBrand() {
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
    public static String phoneModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 网络可用
     *
     * @return
     */
    public static boolean netWorkAvailable() {
        ConnectivityManager cM = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = cM.getActiveNetworkInfo();
        if (mNetworkInfo == null) {
            return false;
        } else {
            return mNetworkInfo.isAvailable();
        }
    }

    /**
     * 流量数据
     *
     * @return
     */
    public static boolean isMobileNet() {
        ConnectivityManager cM = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = cM.getActiveNetworkInfo();
        if (mNetworkInfo == null) {
            return false;
        } else {
            return mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE ? true : false;
        }
    }

    /**
     * Wifi数据
     *
     * @return
     */
    public static boolean isWifiNet() {
        ConnectivityManager cM = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = cM.getActiveNetworkInfo();
        if (mNetworkInfo == null) {
            return false;
        } else {
            return mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI ? true : false;
        }
    }


    /**
     * 获取手机的IMEI序列号
     *
     * @return-- 设备ID
     */
    public static String DeviceID() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取手机号码
     *
     * @return --返回手机号码
     */
    public static String getPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String number = telephonyManager.getLine1Number();
        return number;
    }

    /**
     * 获取手机网络运营商
     *
     * @return
     */
    public static String getPhoneType() {
        TelephonyManager telManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = telManager.getSubscriberId();
        if (TextUtils.isEmpty(imsi))
            return "";
        if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
            return "中国移动";
        } else if (imsi.startsWith("46001")) {
            return "中国联通";
        } else if (imsi.startsWith("46003")) {
            return "中国电信";
        } else {
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
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context -- 传入XBSApplication.appContext
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context -- 传入XBSApplication.appContext
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
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
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获取当前手机的屏幕尺寸
     *
     * @param context
     * @return
     */
    public static int[] getResolution(Context context) {
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
    public static int getStatusHeight(Context context) {
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
     * 判断网络连接状态
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断wifi状态
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断移动网络
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 设置网络
     *
     * @param paramContext
     */
    public static void startToSettings(Context paramContext) {
        if (paramContext == null)
            return;
        try {
            if (Build.VERSION.SDK_INT > 10) {
                paramContext.startActivity(new Intent("android.settings.SETTINGS"));
                return;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
            return;
        }
        paramContext.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
    }

}
