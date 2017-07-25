package com.hsj.provider.photo.interfaces;

import com.hsj.provider.photo.bean.PhotoBean;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/14 12:40
 * @Class:ImageProviderListener
 * @Description:
 */
public interface PhotoProviderListener {

    /**
     * 图片提供者回调
     * @param action     - 0添加图片、1删除图片、2浏览图片
     * @param position
     * @param imageList
     */
    void onImageProvider(int action, int position, List<PhotoBean> imageList);

}
