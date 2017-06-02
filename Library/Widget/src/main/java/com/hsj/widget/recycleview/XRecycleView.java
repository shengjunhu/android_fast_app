package com.hsj.widget.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/2 17:28
 * @Class:XRecycleView
 * @Description: 自定义RecycleView：
 * 主要实现下拉刷新、添加头、添加脚、上拉加载更多
 */
public class XRecycleView extends RecyclerView {

    public XRecycleView(Context context) {
        super(context);
    }

    public XRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
