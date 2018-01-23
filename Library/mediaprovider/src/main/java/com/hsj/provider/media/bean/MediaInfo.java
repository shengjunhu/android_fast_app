package com.hsj.provider.media.bean;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/13 14:28
 * @Class:MediaInfo
 * @Description:媒体文件实体
 */
public class MediaInfo {
    /**
     * id
     */
    private String id;
    /**
     * 文件类型：mediaType
     * 1--图片
     * 2--视屏
     */
    private int mediaType;
    /**
     * 是否可编辑
     */
    private boolean isEdit;
    /**
     * 是否已经编辑
     */
    private boolean isEdited;
    /**
     * 网络图片url
     */
    private String url;
    /**
     * 本地原图片路径
     */
    private String path;
    /**
     * 处于状态：currentAction
     * 1--原文件状态（选中）
     * 2--压缩文件后（确定选中后）
     * 3--添加剪切文件后（完成剪切后）
     * 4--添加打码文件后（完成打码后）
     * 5--添加文字文件后（完成文字后）
     * 6--添加贴图文件后（完成贴图后）
     * 7--添加磨皮文件后（完成磨皮后）
     * 8--添加美白文件后（完成美白后）
     */
    private int currentAction;
    /**
     * 当前路径（对应上操作后的文件当前路径）
     */
    private String currentPath;

}
