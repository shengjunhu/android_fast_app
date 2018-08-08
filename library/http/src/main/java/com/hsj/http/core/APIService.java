package com.hsj.http.base;

import com.hsj.http.data.request.BaseReq;
import com.hsj.http.data.response.LoginResp;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/1 09:39
 * @Class:APIService
 * @Description:网络请求接口，接口文档支持动态配置
 */
public interface APIService {

    /**
     * 所有请求的第一步获取accessToken
     *
     * @return
     */
    @GET("/ws/xbs/token/getToken/{deviceId}")
    Observable<String> getAppToken(
            @Path("deviceId") String deviceId
    );

    /**
     * 登陆请求
     *
     * @param Authorization
     * @param deviceId
     * @param secToken
     * @param token
     * @return
     */
    @POST("/ws/xbs/a/login/loginOnPass")
    Observable<LoginResp> login(
            @Header("Authorization") String Authorization,
            @Header("deviceId") String deviceId,
            @Header("secToken") String secToken,
            @Body BaseReq token
    );

    /**
     * 退出登陆
     *
     * @param Authorization
     * @param deviceId
     * @param secToken
     * @return
     */
    @GET("/ws/xbs/a/login/loginOut")
    Observable<LoginResp> loginOut(
            @Header("Authorization") String Authorization,
            @Header("deviceId") String deviceId,
            @Header("secToken") String secToken
    );


}
