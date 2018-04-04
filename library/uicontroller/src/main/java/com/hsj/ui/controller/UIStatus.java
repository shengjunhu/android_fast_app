/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.hsj.ui.controller;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2018/2/3/18:09
 * @Version:V1.0
 * @Class:UIStatus
 * @Description:界面状态类型注解
 */
@Documented
@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
public @interface UIStatus {

    /**
     * 加载成功（销毁本控件）
     */
    int STATUS_DATA_SUCCESS = 1;

    /**
     * 加载中
     */
    int STATUS_DATA_LOADING = 0;

    /**
     * 加载成功却无数据（显示无数据占位图）
     */
    int STATUS_DATA_EMPTY = -1;

    /**
     * 数据异常
     */
    int STATUS_DATA_EXCEPTION = -2;

    /**
     * 网络异常（去打开网络）
     */
    int STATUS_NETWORK_EXCEPTION = -3;

    /**
     * 服务器异常(点击视图，可重新请求网络)
     */
    int STATUS_SERVER_EXCEPTION = -4;

    /**
     * 没有登陆(点击去登陆)
     */
    int STATUS_NO_LOGIN = -5;

    /**
     * 没有查看or执行权限
     */
    int STATUS_NO_SERVER_PERMISSION = -6;

    /**
     * 没有运行权限（点击去设置权限）
     */
    int STATUS_NO_RUN_PERMISSION = -7;

}
