package com.example.xingmengyuan.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.xingmengyuan.MainActivity;
import com.example.xingmengyuan.R;

public class WelcomeActivity extends AppCompatActivity {
TextView tv;
int count=5;
   private SharedPreferences guide;
Handler handler=new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {

        if (msg.what==1) {
            count--;
            if(count==0){
                boolean isfirst = guide.getBoolean("first", true);
                Intent intent=new Intent();
                if (isfirst) {
                    intent.setClass(WelcomeActivity.this, GuideActivity.class);
                    SharedPreferences.Editor editor= guide.edit();
                    editor.putBoolean("first",false);
                    editor.commit();
                }else{
                    intent.setClass(WelcomeActivity.this,MainActivity.class);
                }
                startActivity(intent);
                finish();
            }else{
                tv.setText(String.valueOf(count));
                handler.sendEmptyMessageDelayed(1,1000);//当count!=0时，继续发送消息，更新页面显示的数字
            }
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv=findViewById(R.id.welcome_tv);
        handler.sendEmptyMessageDelayed(1,1000);//利用handler传递消息
        guide = getSharedPreferences("first", Context.MODE_PRIVATE);
    }
}