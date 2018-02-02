package com.app.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.app.common.R;

public class BaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main);
    }
}
