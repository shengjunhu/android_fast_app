package com.hsj.image.loader.interfaces;

import java.io.File;
import java.util.List;

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
    void onCompressStart();

    /**
     * 压缩成功
     */
    void onCompressProgress(int currentPosition, int allPosition);

    /**
     * 压缩失败
     */
    void onCompressError(Throwable e);

    /**
     * 完成压缩
     * @param fileList
     */
    void onCompressStop(List<File> fileList);

}
