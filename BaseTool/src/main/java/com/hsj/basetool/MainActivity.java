package com.hsj.basetool;

import android.os.Bundle;
import com.hsj.basetool.helper.Logger;
import com.hsj.basetool.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        Logger.e("MainActivity1");
        super.onDestroy();
        Logger.e("MainActivity2");
    }
}
