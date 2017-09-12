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

#===================================关于Push混淆规则==================================================

#Push
-keep class com.hsj.push.AppPushReceiver{*;}
-dontwarn com.hsj.push.**

#JPush
-keep class com.hsj.push.JPushReceiver{*;}
-dontoptimize
-dontpreverify
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keep class com.google.protobuf.** {*;}

#HwPush
-keep class com.hsj.push.HwPushReceiver{*;}
-ignorewarning
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.huawei.hms.**{*;}

#MiPush
-keep class com.hsj.push.MiPushRecevier{*;}
-dontwarn com.xiaomi.push.**
