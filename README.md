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
sp缓存: /data/data/{packageName}/shared_prefs/config.xml
        1、账户、密码(加密)
        2、userToken、imToken
        3、isNewVersion 新版本欢迎页

sd卡缓存路径 (系统可清理、应用本身可清理)
--日志     :log
--图片     :image
--数据库    :db
--下载数据  :download
--临时数据  :temp

sd卡存储路径 (应用本身可清理)
--日志     :log
--图片     :image
--数据库    :db
--下载数据  :download
--临时数据  :temp(图片压缩后的缓存、文件压缩解压)

清理缓存：
1、主线程清理内存
2、子线程清理缓存文件
3、卸载apk：子线程清空app所有相关文件
