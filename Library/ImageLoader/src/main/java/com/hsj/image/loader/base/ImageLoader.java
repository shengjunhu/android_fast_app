package com.hsj.image.loader.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/15 10:21
 * @Class:ImageLoader
 * @Description:图片加载者
 */
public class ImageLoader {

    public static RequestManager with(Activity activity){
        return Glide.with(activity);
    }

    public static RequestManager with(Fragment fragment){
        return Glide.with(fragment);
    }

    public static RequestManager with(Context context){
        return Glide.with(context);
    }



}
