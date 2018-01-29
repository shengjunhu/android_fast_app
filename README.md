# FastAndroid
工作中Android开发用的项目框架：模块化、组件化开发，可快速集成

## 开发框架：
* 1、MVC框架 ： 使用简单（结合DataBinding更好用）
* 2、MVP框架 ： 高解耦、提高维护效率、类文件多（View、Data、IPresenter、IContract）
* 3、MVVM框架： 使用简单（结合DataBinding更好用）

## 结构组成：
### 主工程main：
  * [app            :应用宿主程序](/main/pp/App_README.md)
  * [home           :应用首页插件](/main/home/Home_README.md)
  * [shop           :应用商城插件](/main/shop/Shop_README.md)
  * [chat           :应用聊天插件](/main/chat/Chat_README.md)
  * [discover       :应用发现模块](/main/discover/Discover_README.md)
  * [me             :应用我的插件](/main/me/Me_README.md)
 
### 常用功能库library:
  * [sample         :使用说明Module](/library/sample/Sample_README.md)
  * [baseLib        :项目基类封装和工具类库](/library/baselib/BaseLib_README.md)
  * [security    :项目安全加密库](/library/security/Security_README.md)
  * [uicontroller   :UI控制者](/library/uicontroller/UIController_README.md)
  * [uikit          :自定义控件库](/library/uikit/UIKit_README.md)
  * [http           :网络请求库](/library/http/Http_README.md)
  * [imageloader    :图片加载库](/library/imageloader/ImageLoader_README.md)
  * [percentlayout  :百分比布局库](/library/percentlayout/PercentLayout_README.md)
  * [audioprovider  :音频提供者](/library/audioprovider/AudioProvider_README.md)
  * [qrprovider     :二维码提供者](/library/qrprovider/QRProvider_README.md)
  * [ocr            :图片识别库](/library/ocr/OCR_README.md)
  * [photoprovider  :图片提供者](/library/photoprovider/PhotoProvider_README.md)
  * [mediaprovider  :视屏图片音频提供者](/library/mediaprovider/MediaProvider_README.md)
  * [fileprovider   :文件提供者](/library/fileprovider/FileProvider_README.md)
  * [videoplayer    :视屏播放器](/library/videoplayer/VideoPlayer_README.md)
  * [keyboard       :自定义键盘](/library/keyboard/Keyboard_README.md)
  * [gps            :GPS定位](/library/gps/GPS_README.md)
  * [bluetooth      :数据库模块](/library/bluetooth/BlueTooth_README.md)
  * [db             :对象存储](/library/db/DB_README.md)
  * [router         :数据路由者](/library/router/Router_README.md)
  * [ndklib         :编译C代码模块](/library/ndklib/NdkLib_README.md)
  * [xmpp           :xmpp模块](/library/xmpp/XMPP_README.md)
  * [socket         :NDK模块](/library/socket/Socket_README.md)
  * [ar             :AR录制与播放](/library/ar/AR_README.md)
  * [u3d            :unity3d模块](/library/u3d/U3D_README.md)
  
### 第三方SDK库:
  * [auth           :第三方登陆、分享模块（qq、微信、微博）](/sdk/auth/Auth_README.md)
  * [pay            :支付宝、微信](/sdk/pay/Pay_README.md)
  * [push           :极光、小米、华为、魅族推送](/sdk/push/Push_README.md)
  * [mapbaidu       :百度地图](/sdk/mapbaidu/MapBaidu_README.md)
  * [cloudbaidu     :百度云存储](/sdk/cloudbaidu/CloudBaidu_README.md)
  * [mapa           :高德地图](/sdk/mapa/AMap_README.md)
  * [cloudqiniu     :七牛云存储](/sdk/cloudqiniu/CloudQiNiu_README.md)
  * [web            :TBS X5 web模块](/sdk/web/Web_README.md)
  * [bugly          :热修复](/sdk/bugly/Bugly_README.md)
  * [im             :及时通讯](/sdk/im/IM_README.md)

### 关于缓存：
* 0-优先采用SD卡缓存，先读取内存、磁盘可用大小值。

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