package com.hsj.push;

import android.content.Context;
import android.support.annotation.NonNull;

import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @Date:2018/5/14/18:34
 * @Version:XBS
 * @Class:PushConfig
 * @Description:推送配置
 */
public class PushConfig {

    public static void init(@NonNull Context context) {

        if ("HuaWei".equals(android.os.Build.MANUFACTURER)) {
            HMSAgent.init(context);
            HMSAgent.connect(this, new ConnectHandler() {
                @Override
                public void onConnect(int rst) {

                }
            });
            HMSAgent.Push.getToken(new GetTokenHandler() {
                @Override
                public void onResult(int rtnCode) {

                }
            });
        } else if ("Xiaomi".equals(android.os.Build.MANUFACTURER)) {
            MiPushClient.registerPush(context, "APP_ID", "APP_KEY");
        } else if (MzSystemUtils.isBrandMeizu(context)) {
            PushManager.register(context, "APP_ID", "APP_KEY");
        }else {

        }
    }

//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//        HMSAgent.destroy();
//    }

}
