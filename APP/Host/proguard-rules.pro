# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\ToolBcakup\AndroidSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#========================================项目混淆规则=================================================
#混淆说明：
#1、各Moudle单独使用自己的混淆文件
#2、发布module 开启混淆，library也会开启混淆
#3、程序中使用到原生组件，SDK提供混淆规则；如：四大组件、接口、序列化、注解、自定义控件等等
#4、对实体模块添加混淆规则（不混淆）
#5、jar混淆规则

#----------------------------------------基本混淆命令-------------------------------------------------


#----------------------------------------本module混淆命令---------------------------------------------


#----------------------------------------开源库混淆命令-----------------------------------------------


#----------------------------------------第三方SDK混淆命令--------------------------------------------
#百度地图SDK
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

#支付宝SDK
-libraryjars libs/alipaySdk-20170623-proguard.jar
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

#微信SDK（登录、分享）
-keep class com.tencent.mm.opensdk.** { *; }
-keep class com.tencent.wxop.** { *; }
-keep class com.tencent.mm.sdk.** { *; }

#QQSDK（登录、分享）
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

#微博SDK（登录、分享）
-keep class com.sina.weibo.sdk.** { *; }