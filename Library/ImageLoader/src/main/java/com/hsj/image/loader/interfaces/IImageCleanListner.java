package com.hsj.image.loader.interfaces;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/13 15:43
 * @Class:IImageCleanListner
 * @Description:图片清理接口
 */
public interface IImageCleanListner {

    void onImageCleanStart();

    void onImageCleanProgress(long currentPosition,long allPosition);

    void onImageCleanException(Exception e);

    void onImageCleanStop();

}
