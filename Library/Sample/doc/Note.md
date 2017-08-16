
* 1、
    12sp 小字提示
    14sp（桌面端13sp） 正文/按钮文字
    16sp（桌面端15sp） 小标题
    20sp Appbar文字
    24sp 大标题
  
* 2、
    顶部状态栏高度：24dp
    Appbar最小高度：56dp
    底部导航栏高度：48dp
    悬浮按钮尺寸：56x56dp/40x40dp
    用户头像尺寸：64x64dp/40x40dp
    小图标点击区域：48x48dp
    侧边抽屉到屏幕右边的距离：56dp
    卡片间距：8dp
    分隔线上下留白：8dp
    大多元素的留白距离：16dp
    屏幕左右对齐基线：16dp
    文字左侧对齐基线：72dp
    
* 3、Material Design（android material design Demo）
    - 1、AppBarLayout
    - 2、CollapsingToolbarLayout
    - 3、CoordinatorLayout，
    - 4、FloatingActionButton、
    - 5、NavigationView、
    - 6、Snackbar、
    - 7、TabLayout、
    - 8、TextInputLayout
    - 9、NestedScrollView
    - 10、BottomNavigationView

* 4、Theme
    - 1、主题样式
    - 2、通知栏样式（透明显图片、颜色值、白色状态栏黑色值）
    
* 5、控件特性
    - 1、EditText
    - 2、TextView
    - 3、Button
    - 4、ImageView
    - 5、RecycleView
    - 6、GridView
    - 7、LinearLayout
    - 8、RelativeLayout

* 6、线程池使用

* 7、RxJava使用

* 9、res->anim目录
    ```
    1、animated-vector
    
    ```

* 10、res->color目录

* 11、res->drawable目录
    ```
    1.ShapeDrawable         <shape />
    2.BitmapDrawable        <bitmap />
    3.ColorDrawable         <color />
    4.ClipDrawable          <clip />
    5.InsetDrawable         <inset />
    6.ScaleDrawable         <scale /> 
    7.RoateDrawable         <roate />
    8.LevelListDrawable     <level-list />
    9.AnimaitonDrawable     <animation-list />
    10.StateListDrawable    <selector />
    11.LayerDrawable        <layer-list />
    12.TransitionDrawable   <transition />
    13.RippleDrawable       <ripple />
   
    1.shape
            简介
            作用：XML中定义的几何形状
            位置：res/drawable/文件的名称.xml
            使用的方法：
            Java代码中：R.drawable.文件的名称
            XML中：android:background="@drawable/文件的名称"
            属性：
            <shape>  Android:shape=["rectangle" | "oval" | "line" | "ring"]
            其中rectagle矩形，oval椭圆，line水平直线，ring环形
            <shape>中子节点的常用属性：
            <gradient>  渐变
            Android:startColor
            起始颜色
            Android:endColor
            结束颜色
            Android:angle
            渐变角度，0从左到右，90表示从下到上，数值为45的整数倍，默认为0；
            Android:type
            渐变的样式 liner线性渐变 radial环形渐变 sweep
            <solid >  内部填充
            Android:color
            填充的颜色
            <stroke >描边
            Android:width
            描边的宽度
            Android:color
            描边的颜色
            Android:dashWidth
             表示'-'横线的宽度
            Android:dashGap
            表示'-'横线之间的距离
            <corners >圆角
            Android:radius
            圆角的半径 值越大角越圆
            Android:topRightRadius
            右上圆角半径
            Android:bottomLeftRadius
            右下圆角角半径
            Android:topLeftRadius
            左上圆角半径
            Android:bottomRightRadius
            左下圆角半径
            <padding >边界填充
            android:bottom="1.0dip"
            底部填充
            android:left="1.0dip"
            左边填充
            android:right="1.0dip"
            右边填充
            android:top="0.0dip"
            上面填充
    
        2、selector
                    android:state_selected 是否选中
                    android:state_focused 是否获得焦点
                    android:state_pressed 是否按压
                    android:state_enabled 是否设置是否响应事件,指所有事件另：
                    android:state_window_focused 默认时的背景图片
    
        3.layer-list
            简介：将多个图片或上面两种效果按照顺序层叠起来
            例子：
            [html] view plain copy
            <?xml version="1.0" encoding="utf-8"?>
            <layer-list xmlns:android="http://schemas.android.com/apk/res/android">
                <item>
                  <bitmap android:src="@drawable/android_red"
                    android:gravity="center" />
                </item>
                <item android:top="10dp" android:left="10dp">
                  <bitmap android:src="@drawable/android_green"
                    android:gravity="center" />
                </item>
                <item android:top="20dp" android:left="20dp">
                  <bitmap android:src="@drawable/android_blue"
                    android:gravity="center" />
                </item>
            </layer-list>
            
        4、vector
    ```




