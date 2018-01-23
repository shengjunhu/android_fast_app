package com.hsj.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hsj.db.bean.UserInfo;
import com.hsj.db.core.App;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class DBMainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);

        initUI();

        initData();
    }

    private void initUI() {

    }

    @Override
    public void onClick(View view) {

    }

    private void initData() {
        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        Box<UserInfo> userInfoBox = boxStore.boxFor(UserInfo.class);
    }


}
