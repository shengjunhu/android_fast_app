
#==========================================FastAndroid混淆配置=======================================
#---------->基本混淆区
#基本区
-optimizationpasses 5
-keepattributes EnclosingMethod
-ignorewarnings
-keepattributes Exceptions,InnerClasses
-keepattributes Signature
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

#native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#Parcelable不被混淆
-keep class * implements Android.os.Parcelable {
    public static final Android.os.Parcelable$Creator *;
}

#Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#列举不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#Android
#-dontwarn android.support.**
#-keep class android.support.** {*; }
#-keep public class * extends android.os.IInterface

#---------->架包混淆区
#OkHttp3.6.0
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#Gilde3.7.0
-keep class com.bumptech.glide.Glide { *; }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.**

#EventBus3.0.0
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

###：Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}

#greenDao3.2.0
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

#fastJson-android1.1.56
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }

#Gson2.8.0
-keep class com.google.gson.** { *; }

#---------->Module混淆区


#---------->百度云存储混淆区
#官方暂无混淆要求

#---------->百度文库阅读器
-keep class com.baidu.bdocreader.** {
  public *;
}

#---------->融云IM混淆区
# RongCloud SDK
-keep class io.rong.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**

#BroadcastReceiver （改成自己的的类名）
-keep class io.rong.app.DemoNotificationReceiver {*;}

# VoIP
-keep class io.agora.rtc.** {*;}

# 红包
-keep class com.uuhelper.Application.** {*;}
-keep class net.sourceforge.zbar.** { *; }
-keep class com.google.android.gms.** { *; }
-keep class com.jrmf360.rylib.** {*;}

#---------->第三方登陆、分享、支付混淆区
#QQ登陆、分享
-keep class * extends android.app.Dialog

#微信登陆、分享、支付
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** {*;}
-keep class com.tencent.mm.sdk.** {*;}

#微博登陆、分享
#官方暂无混淆要求

#支付宝支付
-keep class com.alipay.** {*;}

#---------->百度地图混淆区
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**


