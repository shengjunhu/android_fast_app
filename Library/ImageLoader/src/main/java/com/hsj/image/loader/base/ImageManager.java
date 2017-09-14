package com.hsj.image.loader.base;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/13 15:41
 * @Class:ImageManager
 * @Description:图片管理类
 */
public class ImageManager {

    /**
     * 获取Image缓存目录
     * @return
     */
    public static String getImageCacheDir(){
        return "";
    }

    /**
     * 获取Image存储目录
     * @return
     */
    public static String getImageFileDie(){
        return "";
    }

    /**
     * 删除指定路径图片
     * @param imagePath
     */
    public static void deleteImage(String imagePath){

    }

    /**
     * 清理内存：主线程
     */
    public static void cleanMemory(){

    }

    /**
     * 清理缓存：异步线程
     */
    public static void cleanCacheMemory(){

    }

}
