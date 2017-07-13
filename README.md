# FastAndroid
工作中Android开发用的项目框架：模块化、组件化开发，可快速集成

## 开发框架：
- 1、MVC框架 ： 使用简单（结合DataBinding）更好用
- 2、MVP框架 ： 高解耦、类多复杂（View、Data、IPresenter、IContract）
- 3、MVVM框架： 一般般

## 结构组成：
### 主工程APP： 
  * [App: app主模块](/APP/App/App_README.md)
  * Home:首页模块
  * Shop:商城模块
  * Chat:聊天模块
  * Zone:朋友圈模块
  * Me:我的模块
 
### 库工程Library：
  * Base:项目基类库
  * Tool:工具库
  * Widget:自定义框架
  * Http:网络请求框架
  * DB:数据库
  * DBObject:数据本地对象存储
  * AutoLayout:自动布局、使用px
  * AudioProvider:音频提供者
  * QRProvider:二维码提供者
  * ImageManager:图片处理者
  * ImageProvider:图片提供者
  * Permission:权限申请库
  * Keyboard:虚拟键盘与表情、自定义密码键盘
  * FileProvider:文件提供者
  * VideoPlayer:视屏播放器
  * VideoProvider:视屏提供者
  * Web:js、html与android交互
  * Sample:使用介绍库
  
### 第三方SDK库:
  * AliPay:支付宝
  * BDCloud:百度云存储
  * BDMap:百度地图
  * Bugly:腾讯bugly
  * GDMap:高德地图
  * LoginShare:QQ、微信、微博登陆分享
  * QNCloud:七牛云
  * WxPay:微信支付
  * IM:即时通讯（融云）

## 插件功能：

### 编写阶段模块结构

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
