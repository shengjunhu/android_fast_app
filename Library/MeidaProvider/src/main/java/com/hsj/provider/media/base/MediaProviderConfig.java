/*******************************************************************************
 * Copyright (C) 2017.  HSJ.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed To in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 ******************************************************************************/
package com.hsj.provider.media.base;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/14/15:06
 * @Class:MediaProviderConfig
 * @Description:媒体文件提供者配置
 */
public class MediaProviderConfig {

/*****************************************照片配置参数***********************************************/
    /**
     * 是否选图片
     */
    private boolean isPhotoSelect;

    /**
     * 是否可编辑图片：为true可编辑能剪裁、美图等修图操作，为false只可压缩、剪切
     */
    private boolean isPhotoEditEnable;

    /**
     * 是否可压缩图片
     */
    private boolean isPhotoCompressEnable = true;

    /**
     * 推动剪裁：true为推动剪裁，false为指定参数剪裁
     */
    private boolean isPhotoDragCut;

    /**
     * 是否圆形剪裁：true为圆形剪裁，false为方形剪裁
     */
    private boolean isPhotoCutCircle;

    /**
     * 使用指定长宽参数剪裁：为true拖动剪裁无效
     */
    private boolean isPhotoCropByParam;

    /**
     * 指定剪裁宽，小于此值不剪裁
     */
    private int photoCropWidth;

    /**
     * 指定剪裁高，小于此值不剪裁
     */
    private int photoCropHeight;

    /**
     * 默认质量压缩
     */
    private int PhotoCompressQuality = 100;

    /**
     * 采用长宽压缩：低于此值不压缩
     */
    private int photoCompressWidth = 1080;

    /**
     * 采用长宽压缩：低于此值不压缩
     */
    private int photoCompressHeight = 1920;

    /**
     * 指定压缩后的最大体积/kb
     */
    private long photoCompressMaxSize = 200;

/*****************************************视屏配置参数***********************************************/

    /**
     * 是否选视屏
     */
    private boolean isVideoSelect;

    /**
     * 是否可编辑视屏: true开启打码、贴图，美图等修图功能，为false只可压缩、剪裁
     */
    private boolean isVideoEditEnable;

    /**
     * 是否可压缩视屏
     */
    private boolean isVideoCompressEnable;

    /**
     * 是否可剪裁视屏
     */
    private boolean isVideoCropEnable;

    /**
     * 长按录视屏最短时间 /s ：
     * 1、videoRecordMinTime<3s--></3s-->提示不可小于三秒，
     * 2、3s<videoRecordMinTime<10s--></10s-->松开录制按键，删除，重录、确定
     */
    private long videoRecordMinTime = 3;

    /**
     * 长按录视屏最长时间 /s：
     * 1、videoRecordMaxTime < 10 && 按住录制-->录制状态
     * 2、videoRecordMaxTime = 10-->删除，重录、确定
     */
    private long videoRecordMaxTime = 10;

    /**
     * 帧率：建议20-30
     */
    private int videoRate = 20;

    /**
     * 间隔帧率：建议1-100
     */
    private int videoInterval = 5;

    /**
     * 分辨率：360、480、540、720、960、1080
     */
    private int videoResolutionRatio = 540;

    /**
     * 视屏质量：1-100
     */
    private int videoQuality = 70;

    /**
     * 视屏比例：
     * 11-->1:1
     * 34-->3:4
     * 916-->9:16
     */
    private int videoWHRatio = 34;

/*****************************************共同配置参数***********************************************/

    /**
     * 启动拍照or录制：
     * 1、isPhotoSelect = true->拍照
     * 2、isVideoSelect = true->录制
     * 3、isPhotoSelect && isVideoSelect = true->点击拍照、长按录制视屏
     */
    private boolean isStartcamera;

    /**
     * 启动相册图片or相册视屏：
     * 1、isPhotoSelect = true->筛选图片
     * 2、isVideoSelect = true->筛选视屏
     * 3、isPhotoSelect && isVideoSelect = true->筛选图片和视屏
     */
    private boolean isStartGallery;

    /**
     * 是否多选：
     * 为tue
     * 1、isPhotoSelect = true->选selectMaxNum张图
     * 2、isVideoSelect = true->选selectMaxNum视屏
     * 3、isPhotoSelect && isVideoSelect = true->选图片和视屏最多9张
     *
     * 为false
     * 1、isPhotoSelect = true->选图片1
     * 2、isVideoSelect = true->选视屏1
     * 3、isPhotoSelect && isVideoSelect = true->图片or视屏二选一
     */
    private boolean isMultiSelect;

    /**
     * 多选默认数量9
     */
    private int selectMaxNum = 9;

/*****************************************配置参数End***********************************************/


}
