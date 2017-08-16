# FastAndroid
工作中Android开发用的项目框架：模块化、组件化开发，可快速集成

## 开发框架：
* 1、MVC框架 ： 使用简单（结合DataBinding更好用）
* 2、MVP框架 ： 高解耦、类文件多（View、Data、IPresenter、IContract）
* 3、MVVM框架： 一般般

## 结构组成：
### 主工程APP： 
  * [App :主模块](/APP/App/App_README.md)
  * [Home:首页模块](/APP/App/Home_README.md)
  * [Shop:商城模块](/APP/App/Shop_README.md)
  * [Chat:聊天模块](/APP/App/Chat_README.md)
  * [Zone:朋友圈模块](/APP/App/Zone_README.md)
  * [Me  :我的模块](/APP/App/Me_README.md)
 
### 库工程Library：
  * [Sample         :使用说明Module](/Library/Sample/Sample_README.md)
  * [Base           :项目基类库](/Library/Base/Base_README.md)
  * [UIController   :UI控制者](/Library/UIController/UIController_README.md)
  * [UIKit          :控件库](/Library/UIKit/UIKit_README.md)
  * [Http           :网络库](/Library/Http/Http_README.md)
  * [ImageLoader    :图片加载库](/Library/ImageLoader/ImageLoader_README.md)
  * [PercentLayout  :自动布局库](/Library/PercentLayout/PercentLayout_README.md)
  * [AudioProvider  :音频提供者](/Library/AudioProvider/AudioProvider_README.md)
  * [QRProvider     :二维码提供者](/Library/QRProvider/QRProvider_README.md)
  * [PhotoProvider  :图片提供者](/Library/PhotoProvider/PhotoProvider_README.md)
  * [FileProvider   :文件提供者](/Library/FileProvider/FileProvider_README.md)
  * [VideoPlayer    :视屏播放器](/Library/VideoPlayer/VideoPlayer_README.md)
  * [VideoProvider  :视屏提供者](/Library/VideoProvider/VideoProvider_README.md)
  * [Permission     :权限申请库](/Library/Permission/Permission_README.md)
  * [Keyboard       :自定义键盘](/Library/Keyboard/Keyboard_README.md)
  * [Web            :web封装库](/Library/Web/Web_README.md)
  * [GPSLocation    :GPS定位](/Library/GPSLocation/GPSLocation_README.md)
  * [DB             :数据库模块](/Library/DB/DB_README.md)
  * [DBObject       :对象存储](/Library/DBObject/DBObject_README.md)
  * [Router         :数据路由者](/Library/Router/Router_README.md)
  * [NdkLib         :NDK模块](/Library/NdkLib/NdkLib_README.md)
  * [XMPP           :XMPP模块](/Library/XMPP/XMPP_README.md)
  * [Socket         :NDK模块](/Library/Socket/Socket_README.md)
  
### 第三方SDK库:
  * [Login  :第三方登陆模块](/ThirdSDK/Login/Login_README.md)
  * [Pay    :支付宝、微信](/ThirdSDK/Pay/Pay_README.md)
  * [Push   :极光、小米、华为推送](/ThirdSDK/Push/Push_README.md)
  * [BDMap  :百度地图](/ThirdSDK/BDMap/BDMap_README.md)
  * [BDCloud:百度云存储](/ThirdSDK/BDCloud/BDCloud_README.md)
  * [GDMap  :高德地图](/ThirdSDK/GDMap/GDMap_README.md)
  * [QNCloud:七牛云存储](/ThirdSDK/QNCloud/QNCloud_README.md)
  * [IM     :及时通讯](/ThirdSDK/IM/IM_README.md)
  * [Bugly  :热修复](/ThirdSDK/Bugly/Bugly_README.md)

### 关于缓存：
* 0-先读取内存、磁盘可用大小值；优先采用SD卡缓存

* 1-SharedPreferences:
```
①根路径: /data/data/{packageName}/shared_prefs/xx.xml

②设备信息：设备ID、app版本号、该版本号第一次启动 /data/data/{packageName}/shared_prefs/appInfo.xml

③用户信息：账号、loginToken （密码）          /data/data/{packageName}/shared_prefs/userInfo.xml

④操作信息：启动app后后台检测上次启动未完成任务   /data/data/{packageName}/shared_prefs/actionInfo.xml
```

* 2-cache:
```
①根路径: /storage/emulated/0/Android/data/{packageName}/cache/
         /data/data/{packageName}/cache/
        
②网络数据缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/db/data.db
        /data/data/{packageName}/cache/db/data.db
        
③网络图片缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/image/
        /data/data/{packageName}/cache/image/

④网络视屏缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/video/
        /data/data/{packageName}/cache/video/
    
⑤网络音频缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/audio/
        /data/data/{packageName}/cache/audio/
        
⑤Web缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/web/
        /data/data/{packageName}/cache/web/
```

* 3-files:
```
①根路径: /storage/emulated/0/Android/data/{packageName}/files/
         /data/data/{packageName}/files/
        
②log异常日志: 上传后删除或超过一周删除
        /storage/emulated/0/Android/data/{packageName}/files/log/
        /data/data/{packageName}/files/log/
        
③apk下载: 安装成功后删除
        /storage/emulated/0/Android/data/{packageName}/files/apk/
        /data/data/{packageName}/files/apk/
        
④doc文档: 
        /storage/emulated/0/Android/data/{packageName}/files/doc/
        /data/data/{packageName}/files/doc/
        
⑤image编辑:完成操作后删除
        /storage/emulated/0/Android/data/{packageName}/files/image/
        /data/data/{packageName}/files/image/
        
⑤audio录制:完成操作后删除
        /storage/emulated/0/Android/data/{packageName}/files/audio/
        /data/data/{packageName}/files/audio/
        
⑥video录制:完成操作后删除
        /storage/emulated/0/Android/data/{packageName}/files/video/
        /data/data/{packageName}/files/video/
        
⑦adver广告图片:
        /storage/emulated/0/Android/data/{packageName}/files/adver/
        /data/data/{packageName}/files/adver/
        
⑧upload: 上传成功删除
        /storage/emulated/0/Android/data/{packageName}/files/upload/
        /data/data/{packageName}/files/upload/
        
⑨download:
        /storage/emulated/0/Android/data/{packageName}/files/download/
        /data/data/{packageName}/files/download/
```

* 4-缓存管理:
```
①应用内存清理：执行在主线程
②应用缓存清理：执行在子线程
③定期管理缓存：后台任务管理中检查缓存，缓存时间过长子线程中删除
```
