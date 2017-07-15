package com.hsj.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SampleMainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setText("启动界面");
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setText("欢迎界面");
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setText("主页抽屉界面");
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setText("刷新SwipeRefreshLayout");
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setText("登陆界面");
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setText("滚动Activity");
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setText("");
        Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setText("");
        Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setText("");
        Button btn10 = (Button) findViewById(R.id.btn10);
        btn10.setText("");
        Button btn11 = (Button) findViewById(R.id.btn11);
        btn11.setText("");
        Button btn12 = (Button) findViewById(R.id.btn12);
        btn12.setText("");
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btn1:
               startActivity(new Intent(this,LaunchActivity.class));
               break;
           case R.id.btn2:
               startActivity(new Intent(this,WelcomeActivity.class));
               break;
           case R.id.btn3:
               startActivity(new Intent(this,DrawerActivity.class));
               break;
           case R.id.btn4:
               startActivity(new Intent(this,SwipeRefreshLayoutActivity.class));
               break;
           case R.id.btn5:
               startActivity(new Intent(this,LoginActivity.class));
               break;
           case R.id.btn6:
               startActivity(new Intent(this,ScrollingActivity.class));
               break;
           case R.id.btn7:
               break;
           case R.id.btn8:
               break;
           case R.id.btn9:
               break;
           case R.id.btn10:
               break;
           case R.id.btn11:
               break;
           case R.id.btn12:
               break;
           default:
               break;
       }
    }

}
