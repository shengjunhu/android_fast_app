package com.hsj.db.core;

import android.app.Application;
import com.hsj.db.BuildConfig;
import com.hsj.db.bean.MyObjectBox;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2018/1/23/17:56
 * @Version:V1.0
 * @Class:DBManager
 * @Description:数据库管理者
 */
public class App extends Application {

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(App.this);
        }
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

}
