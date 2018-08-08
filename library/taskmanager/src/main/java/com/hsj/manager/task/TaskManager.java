/*******************************************************************************
 * Copyright (c) 2017.   ShengJunHu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 ******************************************************************************/

package com.hsj.manager.task;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/08/06 22:49
 * @Class:TaskManager
 * @Description:任务管理器
 */
public class TaskManager {

    private volatile static TaskManager taskManager;

    public static TaskManager getInstance() {
        if (taskManager == null) {
            synchronized (TaskManager.class) {
                if (taskManager == null) {
                    taskManager = new TaskManager();
                }
            }
        }
        return taskManager;
    }

    /**
     * 开启定位
     */
    public void doTask1() {

    }

    /**
     * loginToken未过期时，刷新用户信息
     */
    public void doTask2() {

    }

    /**
     * loginToken未过期时，刷新好友列表
     */
    public void doTask3() {

    }

    /**
     * loginToken未过期时，刷新新消息
     */
    public void doTask4() {

    }

    /**
     * 检测上次退出未完成的任务
     */
    public void doTask5() {

    }

    /**
     * 上传错误日志
     */
    public void doTask6() {

    }

    /**
     * 删除过期的缓存文件
     */
    public void doTask7() {

    }

}
