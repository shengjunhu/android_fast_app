# Permission 权限框架

###使用方法
0、可在Activity、Fragment、Context中使用

1、请求权限：
PermissionManager.with(Activity)
    .requestId(300)//请求码
    .param(String ...Params)//请求数组
    .hint("提示对话框")//空为默认提示对话框
    .callback(this)
    .start();

2、同意回调：
@Grant(300)
private void getPermissionYes() {
    // TODO 申请权限成功。
}

3、拒绝回调
@eny(300)
private void getPermissionNo(List<String> deniedPermissions) {
    // TODO 申请权限失败。
}

4、拒绝后的温馨提示


5、被拒绝后去setting返回后,从新请求权限
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     switch (requestCode) {
         case 301: {
             break;
         }
     }
 }

