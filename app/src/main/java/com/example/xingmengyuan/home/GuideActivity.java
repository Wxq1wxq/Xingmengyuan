package com.example.xingmengyuan.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xingmengyuan.MainActivity;
import com.example.xingmengyuan.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
ViewPager guidVp;
int[] guids={R.mipmap.loading1,R.mipmap.loading2,R.mipmap.loading3};
List<ImageView> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        guidVp=findViewById(R.id.guide_vp);
        mData=new ArrayList<>();//存储开始界面的图片
        initPager();
        setOnClickListener();
    }

    private void setOnClickListener() {
        int index=mData.size();
        ImageView imageView = mData.get(index - 1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initPager() {
        //将guid数组中的图片设置后，转化成ImageView存储到List<ImageView> mData中
        for(int i=0;i<guids.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(guids[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams lpl=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lpl);
            mData.add(imageView);
        }
        GuideAdapter adapter=new GuideAdapter(mData);
        guidVp.setAdapter(adapter);
    }
}