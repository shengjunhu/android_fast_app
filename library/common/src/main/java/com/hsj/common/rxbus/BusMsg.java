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

package com.hsj.common.rxbus;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2018/2/25/14:19
 * @Version:V1.0
 * @Class:BusBean
 * @Description:
 */
public class BusMsg {

    private int busId;
    private Object busData;

    public BusMsg() {
    }

    public BusMsg(int busId) {
        this.busId = busId;
    }

    public BusMsg(int busId, Object busData) {
        this.busId = busId;
        this.busData = busData;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public Object getBusData() {
        return busData;
    }

    public void setBusData(Object busData) {
        this.busData = busData;
    }
}
