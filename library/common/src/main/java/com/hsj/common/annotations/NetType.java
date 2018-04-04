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

package com.hsj.common.annotations;

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
 * @Class:NetType
 * @Description:网络类型注解
 */
@Documented
@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
public @interface NetType {

    /**
     * 设备网络关闭
     */
    int NETWORK_CLOSE       = -1;

    /**
     * 设备网络不可用
     */
    int NETWORK_UNAVAILABLE = 0;

    /**
     *  设备网络移动网可用
     */
    int NETWORK_MOBILE      = 1;

    /**
     * 设备2G网络可用
     */
    int NETWORK_2G          = 2;

    /**
     * 设备3G网络可用
     */
    int NETWORK_3G          = 3;

    /**
     * 设备4G网络可用
     */
    int NETWORK_4G          = 4;

    /**
     * 设备5G网络可用
     */
    int NETWORK_5G          = 5;

    /**
     * 设备WIFI可用
     */
    int NETWORK_WIFI        = 6;

}
