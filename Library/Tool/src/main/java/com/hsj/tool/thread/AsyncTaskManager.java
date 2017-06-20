package com.hsj.tool.thread;

import android.os.AsyncTask;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017-06-15 23:50
 * @Class:AsyncTaskManager
 * @Description:异步任务管理
 */
public class AsyncTaskManager extends AsyncTask<String,Object,Long>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
