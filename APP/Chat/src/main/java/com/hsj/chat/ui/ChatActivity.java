package com.hsj.chat.ui;

import android.os.Bundle;
import android.view.View;

import com.hsj.base.app.ui.AppBaseActivity;
import com.hsj.chat.R;
import com.hsj.chat.ui.fragment.ChatFragment;

public class ChatActivity extends AppBaseActivity {

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
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
