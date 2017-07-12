package com.hsj.chat.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.hsj.base.app.ui.BaseActivity;
import com.hsj.chat.R;
import com.hsj.chat.ui.fragment.ChatFragment;

public class ChatActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_chat, new ChatFragment())
                .commit();
    }

    @Override
    protected void initToolbar(Toolbar toolbar, TextView tv_left, TextView tv_center, TextView tv_right) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
