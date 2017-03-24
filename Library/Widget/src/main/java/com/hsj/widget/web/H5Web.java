package com.hsj.widget.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.util.AttributeSet;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  FastAndroid V1.0
 * @Date        :  2017/3/14 18:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  H5Web
 * @Description :  Android WebView 封装
 */
public class H5Web extends WebView {

    private Context mContext;

    public H5Web(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public H5Web(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public H5Web(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        WebSettings mWebSettings = getSettings();
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setLoadsImagesAutomatically(true);

        //调用JS方法
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(true);

        //缓存数据
        saveData(mWebSettings);
        newWin(mWebSettings);
        setWebChromeClient(webChromeClient);
        setWebViewClient(webViewClient);
    }

    /**
     * 多窗口的问题
     */
    private void newWin(WebSettings mWebSettings) {
        mWebSettings.setSupportMultipleWindows(false);

        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    /**
     * WebView 设置缓存、取缓存数据
     * 1、webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); //设置 缓存模式
     * 2、webView.getSettings().setDomStorageEnabled(true); // 开启 DOM storage API 功能
     * 3、Android WebView 设置缓存数据
     * <p>
     * HTML5数据存储
     * 首先来了解一下WebView加载网页的几个模式，即WebSetting中设置的加载模式。WebSetting.setCacheMode( int  mode)。
     * <p/>
     * LOAD_CACHE_ELSE_NETWORK：只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
     * <p/>
     * LOAD_CACHE_ONLY：只加载缓存数据，如果没有缓存数据，就出现加载失败；
     * <p/>
     * LOAD_DEFAULT：默认加载方式， 根据cache-control决定是否从网络上取数据；
     * <p/>
     * LOAD_NO_CACHE：不使用缓存，只从网络获取数据；
     * <p/>
     * LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式
     */
    private void saveData(WebSettings mWebSettings) {

        if (isNetworkConnected(mContext)) {
            /**
             * 根据cache-control决定是否从网络上取数据
             */
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            /**
             * 网络断开，则从本地缓存获取，即离线加载
             */
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//
        }

        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);

        /**
         * 获取WebView的缓存位置
         */
        String appCachePath = mContext.getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
    }

    WebViewClient webViewClient = new WebViewClient() {
        /**
         * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //view.loadUrl(url);
            return false;
        }
    };

    /**
     * H5定位；添加网络、定位权限
     */
    WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
            /**
             * param1:
             * param2: true为同意定位权限
             * param3: true为内核记住
             */
            callback.invoke(origin, true, false);//注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        /**
         * 多窗口的问题
         *
         * @param view
         * @param isDialog
         * @param isUserGesture
         * @param resultMsg
         * @return
         */
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebViewTransport transport = (WebViewTransport) resultMsg.obj;
            transport.setWebView(view);
            resultMsg.sendToTarget();
            return true;
        }
    };

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


}
