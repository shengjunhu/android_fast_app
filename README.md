# FastAndroid
工作中Android开发用的项目框架：模块化、组件化开发，可快速集成

## 开发框架：
* 1、MVC框架 ： 使用简单（结合DataBinding）更好用
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
  * [Sample:使用说明Module](/Library/Sample/Sample_README.md)
  * [Base:项目基类库](/Library/Base/Base_README.md)
  * [UIController:UI控制库](/Library/UIController/UIController_README.md)
  * [Widget:控件库](/Library/Widget/Widget_README.md)
  * [Http:网络库](/Library/Http/Http_README.md)
  * [ImageLoader:图片加载库](/Library/ImageLoader/ImageLoader_README.md)
  * [DB:数据库模块](/Library/DB/DB_README.md)
  * [DBObject:对象存储](/Library/DBObject/DBObject_README.md)
  * [AutoLayout:自动布局库](/Library/AutoLayout/AutoLayout_README.md)
  * [AudioProvider:音频提供者](/Library/AudioProvider/AudioProvider_README.md)
  * [QRProvider:二维码提供者](/Library/QRProvider/QRProvider_README.md)
  * [ImageProvider:图片提供者](/Library/ImageProvider/ImageProvider_README.md)
  * [Permission:权限申请库](/Library/Permission/Permission_README.md)
  * [Keyboard:自定义键盘](/Library/Keyboard/Keyboard_README.md)
  * [FileProvider:文件提供者](/Library/FileProvider/FileProvider_README.md)
  * [VideoPlayer:视屏播放器](/Library/VideoPlayer/VideoPlayer_README.md)
  * [VideoProvider:视屏提供者](/Library/VideoProvider/VideoProvider_README.md)
  * [Web:web封装库](/Library/Web/Web_README.md)
  * [GPSLocation:GPS定位](/Library/GPSLocation/GPSLocation_README.md)
  * [Router:数据路由者](/Library/Router/Router_README.md)
  * [NdkLib:NDK模块](/Library/NdkLib/NdkLib_README.md)
  
### 第三方SDK库:
  * [Login  :第三方登陆模块](/ThirdSDK/Login/Login_README.md)
  * [Pay:支付宝、微信](/ThirdSDK/Pay/Pay_README.md)
  * [BDMap:百度地图](/ThirdSDK/BDMap/BDMap_README.md)
  * [BDCloud:百度云存储](/ThirdSDK/BDCloud/BDCloud_README.md)
  * [GDMap:高德地图](/ThirdSDK/GDMap/GDMap_README.md)
  * [QNCloud:七牛云存储](/ThirdSDK/QNCloud/QNCloud_README.md)
  * [IM:及时通讯](/ThirdSDK/IM/IM_README.md)
  * [Bugly:热修复](/ThirdSDK/Bugly/Bugly_README.md)

### 关于缓存：
* 1、sp
```
①路径：/data/data/{packageName}/shared_prefs/config.xml
②存储内容：账号、密码、token、设备ID、版本号、该版本号第一次登陆
```

* 2、db：
```
①路径：
②内容：网络数据字段存储
```
* 3、cache：
```
①image路径：
②内容：网络加载图片缓存
```
* 4、log：
①log路径：
②内容：存放错误日志、上传服务器后删除
```
* 4、download：
①路径：
②内容：下载存放区
```

* 5、temp：
```
①路径：
②内容：临时文件存放区
```

* 6、缓存清理
1、应用内存清理：执行在主线程
2、应用缓存清理：执行在子线程

