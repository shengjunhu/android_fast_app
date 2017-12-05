# FastAndroid
工作中Android开发用的项目框架：模块化、组件化开发，可快速集成

## 开发框架：
* 1、MVC框架 ： 使用简单（结合DataBinding更好用）
* 2、MVP框架 ： 高解耦、提高维护效率、类文件多（View、Data、IPresenter、IContract）
* 3、MVVM框架： 一般般

## 结构组成：
### 主工程APP： 
  * [App            :应用宿主程序](/APP/App/App_README.md)
  * [Home           :应用首页插件](/APP/App/Home_README.md)
  * [Shop           :应用商城插件](/APP/App/Shop_README.md)
  * [Chat           :应用聊天插件](/APP/App/Chat_README.md)
  * [Discover       :应用发现模块](/APP/App/Discover_README.md)
  * [Me             :应用我的插件](/APP/App/Me_README.md)
 
### 库工程Library：
  * [Sample         :使用说明Module](/Library/Sample/Sample_README.md)
  * [BaseLib        :项目基类库](/Library/BaseLib/BaseLib_README.md)
  * [UIController   :UI控制者](/Library/UIController/UIController_README.md)
  * [UIKit          :控件库](/Library/UIKit/UIKit_README.md)
  * [Http           :网络库](/Library/Http/Http_README.md)
  * [ImageLoader    :图片加载库](/Library/ImageLoader/ImageLoader_README.md)
  * [PercentLayout  :百分比布局库](/Library/PercentLayout/PercentLayout_README.md)
  * [AudioProvider  :音频提供者](/Library/AudioProvider/AudioProvider_README.md)
  * [QRProvider     :二维码提供者](/Library/QRProvider/QRProvider_README.md)
  * [QCR            :图片识别库](/Library/QCR/QCR_README.md)
  * [PhotoProvider  :图片提供者](/Library/PhotoProvider/PhotoProvider_README.md)
  * [MediaProvider  :视屏图片音频提供者](/Library/MediaProvider/MediaProvider_README.md)
  * [FileProvider   :文件提供者](/Library/FileProvider/FileProvider_README.md)
  * [VideoPlayer    :视屏播放器](/Library/VideoPlayer/VideoPlayer_README.md)
  * [Keyboard       :自定义键盘](/Library/Keyboard/Keyboard_README.md)
  * [Web            :Web封装库](/Library/Web/Web_README.md)
  * [GPSLocation    :GPS定位](/Library/GPSLocation/GPSLocation_README.md)
  * [BlueTooth      :数据库模块](/Library/BlueTooth/BlueTooth_README.md)
  * [DBObject       :对象存储](/Library/DBObject/DBObject_README.md)
  * [Router         :数据路由者](/Library/Router/Router_README.md)
  * [NdkLib         :NDK模块](/Library/NdkLib/NdkLib_README.md)
  * [XMPP           :XMPP模块](/Library/XMPP/XMPP_README.md)
  * [Socket         :NDK模块](/Library/Socket/Socket_README.md)
  
### 第三方SDK库:
  * [Login          :第三方登陆模块](/ThirdSDK/Login/Login_README.md)
  * [Share          :第三方分享模块](/ThirdSDK/Share/Share_README.md)
  * [Pay            :支付宝、微信](/ThirdSDK/Pay/Pay_README.md)
  * [Push           :极光、小米、华为推送](/ThirdSDK/Push/Push_README.md)
  * [BDMap          :百度地图](/ThirdSDK/BDMap/BDMap_README.md)
  * [BDCloud        :百度云存储](/ThirdSDK/BDCloud/BDCloud_README.md)
  * [GDMap          :高德地图](/ThirdSDK/GDMap/GDMap_README.md)
  * [QNCloud        :七牛云存储](/ThirdSDK/QNCloud/QNCloud_README.md)
  * [IM             :及时通讯](/ThirdSDK/IM/IM_README.md)
  * [AR             :AR](/ThirdSDK/AR/AR_README.md)
  * [U3D            :unity3d模块](/ThirdSDK/U3D/U3D_README.md)
  * [Bugly          :热修复](/ThirdSDK/Bugly/Bugly_README.md)

### 关于缓存：
* 0-先读取内存、磁盘可用大小值；优先采用SD卡缓存

* 1-SharedPreferences:
```
a-根路径: /data/data/{packageName}/shared_prefs/xx.xml

b-设备信息：设备ID、app版本号、该版本号第一次启动 /data/data/{packageName}/shared_prefs/appInfo.xml

c-用户信息：账号、loginToken （密码）          /data/data/{packageName}/shared_prefs/userInfo.xml

d-操作信息：启动app后后台检测上次启动未完成任务   /data/data/{packageName}/shared_prefs/actionInfo.xml

e-数据信息：应用中数据状态记录                 /data/data/{packageName}/shared_prefs/dataInfo.xml
```

* 2-cache:
```
a-根路径: /storage/emulated/0/Android/data/{packageName}/cache/
         /data/data/{packageName}/cache/
        
b-网络数据缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/db/data.db
        /data/data/{packageName}/cache/db/data.db
        
c-网络图片缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/image/
        /data/data/{packageName}/cache/image/

e-网络视屏缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/video/
        /data/data/{packageName}/cache/video/
    
f-网络音频缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/audio/
        /data/data/{packageName}/cache/audio/
        
g-Web缓存:
        /storage/emulated/0/Android/data/{packageName}/cache/web/
        /data/data/{packageName}/cache/web/
```

* 3-files:
```
a-根路径: /storage/emulated/0/Android/data/{packageName}/files/
         /data/data/{packageName}/files/
        
b-log异常日志: 上传后删除或超过一周删除
        /storage/emulated/0/Android/data/{packageName}/files/log/
        /data/data/{packageName}/files/log/
        
c-apk下载: 安装成功后删除
        /storage/emulated/0/Android/data/{packageName}/files/apk/
        /data/data/{packageName}/files/apk/
        
e-doc文档: 
        /storage/emulated/0/Android/data/{packageName}/files/doc/
        /data/data/{packageName}/files/doc/
        
f-image编辑:完成操作后删除
        /storage/emulated/0/Android/data/{packageName}/files/image/
        /data/data/{packageName}/files/image/
        
g-audio录制:完成操作后删除
        /storage/emulated/0/Android/data/{packageName}/files/audio/
        /data/data/{packageName}/files/audio/
        
h-video录制:完成操作后删除
        /storage/emulated/0/Android/data/{packageName}/files/video/
        /data/data/{packageName}/files/video/
        
i-adver广告图片:
        /storage/emulated/0/Android/data/{packageName}/files/adver/
        /data/data/{packageName}/files/adver/
        
j-upload: 上传成功删除
        /storage/emulated/0/Android/data/{packageName}/files/upload/
        /data/data/{packageName}/files/upload/
        
k-download:
        /storage/emulated/0/Android/data/{packageName}/files/download/
        /data/data/{packageName}/files/download/
```

* 4-缓存管理:
```
1-应用内存清理：执行在主线程

2-应用缓存清理：执行在子线程

3-定期管理缓存：后台任务管理中检查缓存，缓存时间过长子线程中删除
```

### 关于作者：
```
* Author：阿军
* Date： 2017/1/9
* E-Mail： mr.ajun@foxmail.com
* GitHub：[GitHub](https://github.com/hushengjun)
* Blog：[CSDN](http://blog.csdn.net/hshengjun/article/details/54408704)
```

### 关于License
```text

    Apache License Version 2.0, January 2017
    
    Copyright (c) 2017 HSJ
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

```