package com.hsj.http.tool;

import com.google.gson.Gson;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:40
 * @Class:JsonManager
 * @Description: Json解析者
 */
public class JsonManager {

    private static Gson gson = new Gson();

    /**
     * 将json字符串转换成java对象
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        try {
            return gson.fromJson(json,cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) cls;
    }

    /**
     * 将bean对象转化成json字符串
     *
     * @param obj
     * @return
     */
    public static String beanToJson(Object obj) {
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
