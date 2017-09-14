package com.hsj.ui.kit.view;

import java.util.ArrayList;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/13 17:04
 * @Class:NineGridView
 * @Description:图片显示九宫格
 */
public class NineGridView {

    /**
     * 图片路径
     */
    private ArrayList<String> path;

    /**
     * 九宫格点击事件监听
     */
    private ItemOnClickListener itemOnClickListener;

    public interface ItemOnClickListener {
        void onItmeClick();
    }

    /**
     * 初始化视图
     */
    public void initView() {

    }

    public void setData(ArrayList<String> path) {

    }

}
