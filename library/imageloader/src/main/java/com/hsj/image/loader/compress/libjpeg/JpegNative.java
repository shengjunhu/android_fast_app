package com.hsj.image.loader.compress.libjpeg;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/15/20:49
 * @Class:JpegNative
 * @Description:
 */
public class JpegNative {

    static void load() {
        System.loadLibrary("libjpeg");
    }

}
