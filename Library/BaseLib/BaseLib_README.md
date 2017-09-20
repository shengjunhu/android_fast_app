# BaseLib Module

### 最底层基类库

* 1、XLog：
```
a、开发环境->打印所有相关日志输出

b、改写日志输出行数为全部打印输出

c、发布环境->仅打印Error日志

d、定期删除无用日志   
```

* 2、SharedPrefer：
```
a-根路径: /data/data/{packageName}/shared_prefs/xx.xml

b-设备信息：设备ID、app版本号、该版本号第一次启动 /data/data/{packageName}/shared_prefs/appInfo.xml

c-用户信息：账号、loginToken （密码）          /data/data/{packageName}/shared_prefs/userInfo.xml

d-操作信息：启动app后后台检测上次启动未完成任务   /data/data/{packageName}/shared_prefs/actionInfo.xml

e-数据信息：应用中数据状态记录                 /data/data/{packageName}/shared_prefs/dataInfo.xml
```

* 2、FileManager：文件管理者

- cache：
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

- files:
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

- manager:
```
1-应用内存清理：执行在主线程

2-应用缓存清理：执行在子线程

3-定期管理缓存：后台任务管理中检查缓存，缓存时间过长子线程中删除

```