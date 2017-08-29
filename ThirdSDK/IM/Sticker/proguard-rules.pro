# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/lixiao/bin/adt-bundle/sdk/tools/proguard/proguard-android.txt
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
-keepparameternames
-dontoptimize
-optimizations !code/allocation/variable
#-optimizationpasses 10
-keepattributes *Annotation*,Signature,SourceFile,LineNumberTable,Deprecated
-renamesourcefileattribute SourceFile
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-keep public class android.support.**{*;}
-dontwarn android.support.**
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keepclassmembers class * implements android.os.Parcelable {
static ** CREATOR;
}
-keep class com.melink.bqmmplugin.rc.EmojiMessage{*;}
-keep class com.melink.bqmmplugin.rc.EmojiMessageItemProvider{*;}
-keep class com.melink.bqmmplugin.rc.BQMMExtensionModule{*;}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.sdk.BQMM{
 public <methods>;
 }
-keep class com.melink.bqmmplugin.rc.bqmmsdk.sdk.BQMM$LANGUAGE_CONSTANTS{
*;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.sdk.BQMM$REGION_CONSTANTS{
*;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.bean.**{
*;
}
-keep class com.melink.bqmmplugin.rc.sop.api.models.open.forms.**{
*;
}
-keep class com.melink.bqmmplugin.rc.sop.api.models.open.modelinfos.**{
*;
}
-keep class com.melink.bqmmplugin.rc.baseframe.utils.DensityUtils{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.baseframe.utils.StringUtils{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.resourceutil.BQMMConstant{
*;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.resourceutil.DefaultResValues{
*;
}

-keep class com.melink.bqmmplugin.rc.baseframe.utils.KJLoger{
public <methods>;
}
-keep interface com.melink.bqmmplugin.rc.bqmmsdk.ui.keyboard.IBQMMUnicodeEmojiProvider{
<methods>;
}
-keep interface com.melink.bqmmplugin.rc.bqmmsdk.sdk.IBqmmSendMessageListener{
<methods>;
}
-keep interface com.melink.bqmmplugin.rc.bqmmsdk.sdk.IFetchEmojiByCodeCallback{
<methods>;
}
-keep interface com.melink.bqmmplugin.rc.bqmmsdk.sdk.IFetchEmojisByCodeListCallback{
<methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.sdk.BQMMMessageHelper{
public <methods>;
}
-keep interface com.melink.bqmmplugin.rc.bqmmsdk.sdk.IMessageParser{
<methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.ui.keyboard.BQMMKeyboard{
public <methods>;
}
-keep interface com.melink.bqmmplugin.rc.bqmmsdk.ui.keyboard.IBQMMUnicodeEmojiProvider{
*;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.BQMMEditView{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.BQMMSendButton{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.gif.BQMMAnimatedGifDrawable{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.gif.BQMMAnimatedImageSpan{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.UpdateListener{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.GifMovieView{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.task.BQMMPopupViewTask{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.baseframe.bitmap.BitmapCreate{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.utils.BQMMBitmapCache{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.BQMMMessageView{
public <methods>;
}
-keep class com.melink.bqmmplugin.rc.bqmmsdk.widget.BQMMMessageText{
public *;
}