package com.hsj.ui.kit.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/13 17:04
 * @Class:NineGridView
 * @Description:图片显示九宫格
 */
public class NineGridView extends View{

    /**
     * 图片路径
     */
    private ArrayList<String> path;

    /**
     * 九宫格点击事件监听
     */
    private ItemOnClickListener itemOnClickListener;

    public NineGridView(Context context) {
        this(context,null);
    }

    public NineGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NineGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    /**
     * 初始化视图
     */
    public void initView() {

    }

    public void setData(ArrayList<String> path) {

    }

    public interface ItemOnClickListener {
        void onItmeClick();
    }


}
