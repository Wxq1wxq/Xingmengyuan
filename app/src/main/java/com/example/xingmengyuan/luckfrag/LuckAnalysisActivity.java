package com.example.xingmengyuan.luckfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xingmengyuan.R;
import com.example.xingmengyuan.partenterfrag.PartnerAnalysisBean;
import com.example.xingmengyuan.utils.LoadDataAsyncTask;
import com.example.xingmengyuan.utils.LuckURLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LuckAnalysisActivity extends AppCompatActivity implements View.OnClickListener,LoadDataAsyncTask.OnGetNetDataListener{
ListView luckLv;//analysis中的list
TextView titleTv;//标题
ImageView backIv;//返回按键
List<LuckItemBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_analysis);
        Intent intent = getIntent();
        String name=intent.getStringExtra("name");
        mDatas=new ArrayList<>();
        String partnerURL = LuckURLContent.getPartnerURL(name, "year");
        initView(name);
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(partnerURL);
    }

    private void initView(String name) {
        luckLv=findViewById(R.id.luckanalysis_lv);
        titleTv=findViewById(R.id.title_tv);
        backIv=findViewById(R.id.title_iv_back);
        titleTv.setText(name);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.title_iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(String json) {
//        if(!TextUtils.isEmpty(json)){
//            PartnerAnalysisBean analysisBean= new Gson().fromJson(json,PartnerAnalysisBean.class);
//            PartnerAnalysisBean.ResultDTO result=analysisBean.getResult();
//            pfTv.setText("配对评分:"+result.getZhishu()+"   "+result.getJieguo());
//            Log.d("TAG", "评分onSuccess: ");
//            bzTv.setText("星座比重:"+result.getBizhong());
//            Log.d("TAG", "星座比重onSuccess: ");
//            jxTv.setText("解析\n\n"+result.getLianai());
//            zyTv.setText("注意事项："+result.getZhuyi());
        if(!TextUtils.isEmpty(json)/* 判断json数据是否为空*/){
            LuckAnalysisBean bean = new Gson().fromJson(json,LuckAnalysisBean.class);
            addDataToList(bean);
            //建立item的Adapter
            LuckAnalysisiAdapter adapter=new LuckAnalysisiAdapter(this,mDatas);
            luckLv.setAdapter(adapter);

        }
    }

    private void addDataToList(LuckAnalysisBean bean) {
        LuckItemBean luckBean1=new LuckItemBean("综合运势",bean.getMima().getText().get(0), Color.BLACK);
        LuckItemBean luckBean2=new LuckItemBean("爱情运势",bean.getLove().get(0), Color.GRAY);
        LuckItemBean luckBean3=new LuckItemBean("事业运势",bean.getCareer().get(0),Color.BLUE );
        LuckItemBean luckBean4=new LuckItemBean("健康运势",bean.getHealth().get(0), Color.LTGRAY);
        LuckItemBean luckBean5=new LuckItemBean("财富运势",bean.getFinance().get(0),Color.GREEN );
        mDatas.add(luckBean1);
        mDatas.add(luckBean2);
        mDatas.add(luckBean3);
        mDatas.add(luckBean4);
        mDatas.add(luckBean5);
    }
}