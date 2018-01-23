package com.hsj.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hsj.db.bean.UserInfo;
import com.hsj.db.core.App;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class DBMainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);

        initUI();

        initData();
    }

    private void initUI() {
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_modify).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        tv_db = findViewById(R.id.tv_db);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                break;
            case R.id.btn_delete:
                break;
            case R.id.btn_modify:
                break;
            case R.id.btn_query:
                break;
            default:
                break;
        }
    }

    private void initData() {
        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        Box<UserInfo> userInfoBox = boxStore.boxFor(UserInfo.class);
    }


}
