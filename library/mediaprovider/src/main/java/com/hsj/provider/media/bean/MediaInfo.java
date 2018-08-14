package com.hsj.provider.media.bean;

import android.text.TextUtils;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
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
    private String oldPath;
    /**
     * 处于状态：action
     * 1--原文件状态（选中）
     * 2--压缩文件后（确定选中后）
     * 3--添加剪切文件后（完成剪切后）
     * 4--添加打码文件后（完成打码后）
     * 5--添加文字文件后（完成文字后）
     * 6--添加贴图文件后（完成贴图后）
     * 7--添加磨皮文件后（完成磨皮后）
     * 8--添加美白文件后（完成美白后）
     */
    private int action;
    /**
     * 当前路径（对应上操作后的文件当前路径）
     */
    private String newPath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getNewPath() {
        if (TextUtils.isEmpty(newPath)){
            return oldPath;
        }
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }
}
