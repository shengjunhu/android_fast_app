package com.hsj.basetool.base;

import android.content.Context;

import com.hsj.basetool.helper.Logger;

/**
 * 模块在主APP下初始化、获取Context、deBug
 * 设置常信息：
 */
public class BaseToolContext {

    //debugFlag - 默认为false ，true为调试
    public static boolean debugFlag;

    //初始化获取的Context
    public static Context mContext;

    //首选项文件名
    public static String  SpConfigName = "Config";

    /**
     * 在app主Module中初始化BaseTool Module
     *
     * @param context   - 引入上下文
     * @param deBug     - 调试标志：true为调试
     */
    public static void init(Context context, boolean deBug) {
        mContext  = context;
        debugFlag = deBug;

        //App管理者初始化
        AppManager.getInstance().init(mContext);

        //初始化日志类
        Logger.init(mContext);

    }

}
