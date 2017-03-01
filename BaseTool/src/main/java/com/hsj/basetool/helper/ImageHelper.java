package com.hsj.basetool.helper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/2/21 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  ImageHelper
 * @Description :  图片加载帮助者、建议直接使用Glide的方法
 */
public class ImageHelper {

    /**
     * Glide加载图片
     *
     * @param mContext   -   Context 、Activity 、Fragment
     * @param url        -   网络http、SD卡file、应用assets、raw、drawable
     * @param imageView  -   可以是图片、gif、本地视频（VideoView）、网络视屏（VideoView）
     */

    /**********************************加载网络资源********************************/
    public static void load(Context mContext, String url, ImageView imageView){
        Glide.with(mContext).load(url).into(imageView);
    }

    public static void load(Activity mContext, String url, ImageView imageView){
        Glide.with(mContext).load(url).into(imageView);
    }

    public static void load(Fragment mContext, String url, ImageView imageView){
        Glide.with(mContext).load(url).into(imageView);
    }


}
