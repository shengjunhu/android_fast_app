package com.hsj.image.loader.interfaces;

import java.io.File;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/18/20:17
 * @Class:IImageCompressListener
 * @Description:图片压缩回调
 */
public interface IImageCompressListener {
    /**
     * 开始压缩
     */
    void onStart();

    /**
     * 压缩成功
     */
    void onSuccess(File file);

    /**
     * 压缩失败
     */
    void onError(Throwable e);
}
