# QRProvider
Android扫描、生成二维码

### 特点
1、支持生成带图标二维码
2、支持扫描二维码、解析度较高
3、解析摄像头扫描到的图片 - > bitmap
4、对扫到的bitmap进行优化（io线程）
5、对bitmap解析（io线程）
6、弹出提示对话框

### 使用方法
1、扫码：QRProvider.scan()
2、生成二维码：QRProvider.createQr(int width,int height,Bitmap bitmap)