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

package com.app.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2018/2/3/17:46
 * @Version:V1.0
 * @Class:SPFile
 * @Description:SharedPreferences文件存储命名
 */
@Documented
@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
public @interface SPFile {

    /**
     * APP_INFO:App相关信息（版本号、该版本号是否第一次启动、主题、字体样式和颜色）
     */
    String APP_INFO = "app_info";

    /**
     * PHONE_INFO:设备相关参数
     */
    String PHONE_INFO = "phone_info";

    /**
     * WORK_INFO:业务逻辑文件命名
     */
    String WORK_INFO = "work_info";

    /**
     * USER_INFO:用户信息(账号、密码、token、设备ID)
     */
    String USER_INFO = "user_info";

    /**
     * ACTION_INFO: 上一次程序未执行完的操作标志，继续操作
     */
    String ACTION_INFO = "action_info";

}
