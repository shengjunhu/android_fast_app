package com.hsj.image.loader.interfaces;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/14/11:16
 * @Class:IGlideLoadListener
 * @Description:Glide加载图片监听
 */
public interface IGlideLoadListener {

    void onGlideStart();

    void onGlideProgress(long currentPosition,long allPosition);

    void onGlideException(Exception e);

    void onGlideStop();
}
