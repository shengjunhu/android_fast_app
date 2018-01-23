package com.hsj.image.loader.interfaces;

import java.util.List;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/13 15:46
 * @Version:XBS V2.0
 * @Class:IImageEditListener
 * @Description:图片编辑接口
 */
public interface IImageEditListener {

    void onImageEditStart();

    void onImageEditProgresss(long currentPosition, long allPosition);

    void OnImageEditException(Exception e);

    void onImageEditStop(List<String> imagePaths);

}
