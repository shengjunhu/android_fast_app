/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.hsj.common.utils;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 16:41
 * @Class:AppTool
 * @Description:
 */
public class AppTool {

    //////////////////////////////////////////////////////////////
    // AppTool 功能如下：
    //      1、获取包名
    //      2、获取app版本号
    //      3、获取app版本名
    //      4、获取版本信息
    //      5、更新app时下载apk、安装apk
    //      6、Activity跳转动画
    //      7、App退出
    //////////////////////////////////////////////////////////////

    /**
     * 获取程序的版本号
     *
     * @param context
     * @param packName
     * @return
     */
    public int getAppVersionCode(@NonNull Context context, @NonNull String packName) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(packName, 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取程序的版本名
     *
     * @param context
     * @param packName
     * @return
     */
    public String getAppVersionName(@NonNull Context context, @NonNull String packName) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(packName, 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return packName;
        }
    }

    /**
     * 获取程序所有的权限
     *
     * @param context
     * @param packName
     * @return
     */
    public String[] getAppPermissions(@NonNull Context context, @NonNull String packName) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packInfo = pm.getPackageInfo(packName, PackageManager.GET_PERMISSIONS);
            return packInfo.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前Activity名字
     *
     * @param context
     * @return
     */
    private static String getCurrentActivityName(@NonNull Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        } else {
            return am.getRunningTasks(1).get(0).topActivity.getClassName();
        }
    }

    /**
     * 判断App是否处于前台
     *
     * @param context
     * @param packName
     * @return
     */
    public static boolean isAppForeground(@NonNull Context context, @NonNull String packName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return false;
        } else {
            List<ActivityManager.RunningAppProcessInfo> info = manager.getRunningAppProcesses();
            if (info == null || info.size() == 0) return false;
            for (ActivityManager.RunningAppProcessInfo aInfo : info) {
                if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return aInfo.processName.equals(packName);
                }
            }
            return false;
        }
    }

    /**
     * 下载APK
     *
     * @param context
     * @param apkUrl
     */
    public static void downLoadApk(@NonNull Context context,
                                   @IdRes int appIcon,
                                   @NonNull String appName,
                                   @NonNull String channelId,
                                   @NonNull String apkUrl) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId);
        Intent intent = new Intent(context, context.getClass());
        intent.putExtra("isRetry", false);
        //未完成
    }

    /**
     * 安装APK(带通知栏直接进入安装)
     * <p>8.0 需添加权限 {@code android:name="android.permission.REQUEST_INSTALL_PACKAGES"}</p>
     * <p>7.0 需添加权限 {@code android.support.v4.content.FileProvider}</p>
     * <p>6.0 需添加权限 {@code android.permission.WRITE_EXTERNAL_STORAGE}</p>
     *
     * @param file
     * @param context
     * @param authority
     * @param finalBuilder
     * @param finalManager
     */
    private void installApk(@NonNull File file,
                            @NonNull Context context,
                            @NonNull String authority,
                            @NonNull NotificationCompat.Builder finalBuilder,
                            @NonNull NotificationManager finalManager) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(context, authority, file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        finalBuilder.setContentIntent(pendingIntent);
        finalBuilder.setContentText("正在安装...");
        finalBuilder.setProgress(100, 100, false);
        finalManager.notify(0, finalBuilder.build());

        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 安装APK（不带通知栏）
     * <p>8.0 需添加权限 {@code android:name="android.permission.REQUEST_INSTALL_PACKAGES"}</p>
     * <p>7.0 需添加权限 {@code android.support.v4.content.FileProvider}</p>
     * <p>6.0 需添加权限 {@code android.permission.WRITE_EXTERNAL_STORAGE}</p>
     *
     * @param context
     * @param apkPath
     * @param authority
     */
    public static void installApk(@NonNull Context context,
                                  @NonNull String apkPath,
                                  @NonNull String authority) {
        File file = new File(apkPath);
        if (!file.exists() || file.isDirectory()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(context, authority, file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 静默安装APK
     * <p>
     * <p>静默 需添加权限{@code android:name="android.permission.INSTALL_PACKAGES"}</p>
     * <p>8.0 需添加权限 {@code android:name="android.permission.REQUEST_INSTALL_PACKAGES"}</p>
     * <p>7.0 需添加权限 {@code android.support.v4.content.FileProvider}</p>
     * <p>6.0 需添加权限 {@code android.permission.WRITE_EXTERNAL_STORAGE}</p>
     *
     * @param apkPath
     */
    public static boolean installApkSilent(@NonNull String apkPath) {
        File fileApk = new File(apkPath);
        if (!fileApk.exists() || fileApk.isDirectory()) return false;
        boolean isRoot = isDeviceRooted();
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + apkPath;
        CommandResult commandResult = execCmd(command, isRoot);
        if (commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success")) {
            return true;
        } else {
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib64 pm install " + apkPath;
            commandResult = execCmd(command, isRoot, true);
            return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
        }
    }

    /**
     * 静默卸载 App
     * <p>非 root 需添加权限
     * {@code <uses-permission android:name="android.permission.DELETE_PACKAGES" />}</p>
     *
     * @param packageName 包名
     * @param isKeepData  是否保留数据
     * @return {@code true}: 卸载成功<br>{@code false}: 卸载失败
     */
    public static boolean uninstallAppSilent(@NonNull String packageName,
                                             @NonNull boolean isKeepData) {
        boolean isRoot = isDeviceRooted();
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall "
                + (isKeepData ? "-k " : "")
                + packageName;
        CommandResult commandResult = execCmd(command, isRoot, true);
        if (commandResult.successMsg != null
                && commandResult.successMsg.toLowerCase().contains("success")) {
            return true;
        } else {
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib64 pm uninstall "
                    + (isKeepData ? "-k " : "")
                    + packageName;
            commandResult = execCmd(command, isRoot, true);
            return commandResult.successMsg != null
                    && commandResult.successMsg.toLowerCase().contains("success");
        }
    }

    /**
     * 设备是否被Root(开源的android没有百分百的对于错)
     * <p> /system/xbin/which  or  /system/bin/which<p/>
     *
     * @return
     */
    private static boolean isDeviceRooted() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return in.readLine() != null;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) {
                process.destroy();
                return false;
            }
        }
    }

    private static final String LINE_SEP = System.getProperty("line.separator");

    /**
     * 是否是在 root 下执行命令
     *
     * @param command 命令
     * @param isRoot  是否需要 root 权限执行
     * @return CommandResult
     */
    public static CommandResult execCmd(@NonNull String command, boolean isRoot) {
        return execCmd(new String[]{command}, isRoot, true);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands 多条命令链表
     * @param isRoot   是否需要 root 权限执行
     * @return CommandResult
     */
    public static CommandResult execCmd(@NonNull List<String> commands, boolean isRoot) {
        return execCmd(commands.toArray(new String[]{}), isRoot, true);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands 多条命令数组
     * @param isRoot   是否需要 root 权限执行
     * @return CommandResult
     */
    public static CommandResult execCmd(@NonNull String[] commands, boolean isRoot) {
        return execCmd(commands, isRoot, true);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param command         命令
     * @param isRoot          是否需要 root 权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public static CommandResult execCmd(@NonNull String command,
                                        boolean isRoot,
                                        boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, isRoot, isNeedResultMsg);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands        命令链表
     * @param isRoot          是否需要 root 权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public static CommandResult execCmd(@NonNull List<String> commands,
                                        boolean isRoot,
                                        boolean isNeedResultMsg) {
        return execCmd(commands.toArray(new String[]{}), isRoot, isNeedResultMsg);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands        命令数组
     * @param isRoot          是否需要 root 权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public static CommandResult execCmd(@NonNull String[] commands, boolean isRoot, boolean isNeedResultMsg) {
        int result = -1;
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(isRoot ? "su" : "sh");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) continue;
                os.write(command.getBytes());
                os.writeBytes(LINE_SEP);
                os.flush();
            }
            os.writeBytes("exit" + LINE_SEP);
            os.flush();
            result = process.waitFor();
            if (isNeedResultMsg) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
                String line;
                if ((line = successResult.readLine()) != null) {
                    successMsg.append(line);
                    while ((line = successResult.readLine()) != null) {
                        successMsg.append(LINE_SEP).append(line);
                    }
                }
                if ((line = errorResult.readLine()) != null) {
                    errorMsg.append(line);
                    while ((line = errorResult.readLine()) != null) {
                        errorMsg.append(LINE_SEP).append(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeIO(os, successResult, errorResult);
            if (process != null) {
                process.destroy();
            }
        }
        return new CommandResult(
                result,
                successMsg == null ? null : successMsg.toString(),
                errorMsg == null ? null : errorMsg.toString()
        );
    }

    /**
     * 关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 返回的命令结果
     */
    public static class CommandResult {
        /**
         * 结果码
         **/
        public int result;
        /**
         * 成功信息
         **/
        public String successMsg;
        /**
         * 错误信息
         **/
        public String errorMsg;

        public CommandResult(final int result, final String successMsg, final String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }

}
