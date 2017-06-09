package com.hsj.widget.recycleview;

import android.view.View;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/9 10:16
 * @Class:IXItemOnClickListener
 * @Description:item点击和长按事件监听
 */
public interface IXItemOnClickListener {

    void onItemClick(int position, View view);

    void onItemLongClick(int position);

}
