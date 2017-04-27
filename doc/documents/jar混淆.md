# 引入依赖的混淆文档

* OkHttp 3.6：
    ```
    -dontwarn okhttp3.**
    ```

* Glide 3.7.0：

    ```
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
        **[] $VALUES;
        public *;
    }
  ```

* Glide+OkHttp3：
    ```
    -keep public class * implements com.bumptech.glide.module.GlideModule
    ```

* greenDao3.2:
    ```
    -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
    }
    -keep class **$Properties
    # If you do not use SQLCipher:
    -dontwarn org.greenrobot.greendao.database.**
    # If you do not use Rx:
    -dontwarn rx.**
    ```

* EventBus:
    ```
    -keepattributes *Annotation*
    -keepclassmembers class ** {
        @org.greenrobot.eventbus.Subscribe <methods>;
    }
    -keep enum org.greenrobot.eventbus.ThreadMode { *; }
    # Only required if you use AsyncExecutor
    -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
        <init>(java.lang.Throwable);
    }
    ```



