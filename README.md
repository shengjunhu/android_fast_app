# FastAndroid
工作中Android开发用的项目框架：模块化、组件化开发，可快速集成
##开发框架结构组成：
##插件功能：

### 编写阶段模块结构

            APP(启动页、欢迎页、登陆页、密码相关页、主页)
    ================================================================================

        业务模块：首页、工作、商城、社区、我的

        子业务： 学校、知识库

    ===============================================================================

        数据模块：Server 、 Cache：DB

    ===============================================================================
                  |                 |               |               |
              BaseTool          FileProvider    ImageProvider  MapProvider
                                QRCodeProvider  PayProvider    OAuthLoginProvider
          |              |      PayProvider     CloudProvider  IMProvider
                                VideoPlayerProvider            VideoRecodProvider
       Widget           Https
    ===============================================================================
