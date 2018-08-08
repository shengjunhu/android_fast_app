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

package com.hsj.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2018/3/21/13:32
 * @Version:V1.0
 * @Class:X5WebView
 * @Description:封装WebView
 */
public class X5WebView extends WebView {

    /**
     * web界面标题
     */
    private TextView title;
    private ProgressBar progressBar;

    public X5WebView(Context arg0) {
        this(arg0, null);
    }

    public X5WebView(Context arg0, AttributeSet arg1) {
        this(arg0, arg1, 0);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        if (attributeSet != null) {
            TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.X5WebView);
            boolean isPb = ta.getBoolean(R.styleable.X5WebView_progressbar, true);
            int pbColor = ta.getColor(R.styleable.X5WebView_progressbar_color,
                    getResources().getColor(R.color.colorBlueTheme));
            ta.recycle();
            initWebView(context, isPb, pbColor);
        } else {
            initWebView(context, false, 0);
        }
    }

    private void initWebView(Context context, boolean isPb, int pbColor) {
        if (isPb) {
            progressBar = new ProgressBar(context, null,
                    android.R.attr.progressBarStyleHorizontal);
            progressBar.setMax(100);
            progressBar.setMinimumHeight(dp2px(context,2));
            progressBar.setBackgroundColor(pbColor);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            this.addViewInLayout(progressBar,0,lp);
        }

        this.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;//防止加载网页时调起系统浏览器
            }
        });

        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (progressBar != null) {
                    if (i == 100) {
                        progressBar.setVisibility(View.GONE);
                    } else {
                        progressBar.setProgress(i);
                    }
                }
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                if (title != null) {
                    title.setText(s);
                }
            }
        });

        // WebStorage webStorage = WebStorage.getInstance();

        initWebViewSettings();

        this.getView().setClickable(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    /**
     * 设置标题文字
     *
     * @param title
     */
    public void setTitle(TextView title) {
        this.title = title;
    }

    /**
     * 设置WebView亮度
     *
     * @param lightColor
     */
    public void setWebViewLight(@ColorInt int lightColor) {
        this.setBackgroundColor(lightColor);
    }

    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public  int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
