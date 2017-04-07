package com.hsj.imageprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hsj.imageprovider.ui.ImageSelectActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_image).setOnClickListener(this);
        findViewById(R.id.btn_video).setOnClickListener(this);

        tv_msg = (TextView) findViewById(R.id.tv_msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_camera:
                break;
            case R.id.btn_image:
                startActivity(new Intent(this, ImageSelectActivity.class));
                break;
            case R.id.btn_video:
                break;
            default:
                break;
        }
    }

}
