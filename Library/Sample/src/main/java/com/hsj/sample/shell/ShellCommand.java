package com.hsj.sample.shell;

import android.view.View;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/8/28 10:28
 * @Class:ShellCommand
 * @Description:使用shell命令行
 */
public class ShellCommand {

    // 冻结日历应用
    public void disableCalendar(View view) {
        runCommand("pm disable com.android.calendar");
    }

    //解冻日历
    public void enableCalendar(View view) {
        runCommand("pm enable com.android.calendar");
    }

    private void runCommand(String cmd) {
        try {
            //提权，申请su权限
            Process process = Runtime.getRuntime().exec("su");

            OutputStream out = process.getOutputStream();
            out.write(cmd.getBytes());

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
