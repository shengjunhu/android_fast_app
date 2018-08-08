package com.hsj.ui.kit.banner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/2 17:11
 * @Class:Banner
 * @Description:轮播图
 */
public class Banner extends View {

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {

    }

    /**
     * 开始滚动
     */
    public void startPlay(){

    }

    /**
     * 暂停滚动
     */
    public void pausePlay(){

    }

    /**
     * 停止滚动
     */
    public void stopPlay(){

    }

}
