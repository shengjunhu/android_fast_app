# FastAndroid
工作中Android开发用的项目框架：模块化、组件化开发，可快速集成
## 开发框架结构组成：
- Sample Module 功能展示Demo

## 插件功能：

### 编写阶段模块结构
- Base Module
    ```
    1、base包是封装的基类
    
    2、tool包是工具类
    
    3、其他包是工具的二次封装
    ```
- Https Module
    ```txt
    1、基于Retrofit网络请求轻量级封装
    
    2、
    
    3、
    ```
- Widget Module
    ```txt
    1、自定义控件Module
    
    2、
    
    3、
    ```
    
### 缓存路径：
sd卡缓存路径--日志     :log
          --图片     :image
          --数据库    :db
          --下载数据  :download
          --临时数据  :temp
          
sp缓存: config.xml
        1、账户、密码(加密)
        2、userToken、imToken
        3、apk新版本启动欢迎页flag

SharedPreferences: /data/data/{packageName}/shared_prefs/config.xml



sd卡缓存路径       : sdPath/{packageName}/
缓存路径：1、有sd卡 :
        2、无sd卡 :
        
内置SD卡路径：/storage/emulated/0
外置SD卡路径：/storage/extSdCard
