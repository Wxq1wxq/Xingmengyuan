package com.example.xingmengyuan.partenterfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xingmengyuan.R;
import com.example.xingmengyuan.utils.AssetsUtils;
import com.example.xingmengyuan.utils.LoadDataAsyncTask;
import com.example.xingmengyuan.utils.URLcontent;
import com.google.gson.Gson;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PartnerAnalysisActivity extends AppCompatActivity implements LoadDataAsyncTask.OnGetNetDataListener{
TextView manTv,womanTv,pdTv,vsTv,pfTv,bzTv,jxTv,zyTv,titleTv;
CircleImageView manIv,womanIv;
ImageView backIv;
    String man_name,woman_name,man_logoname,woman_logoname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_analysis);
        initView();
        getLastData();

        String partnerURL= URLcontent.getPartnerURL(man_name,woman_name);

        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(partnerURL);


    }

    private void getLastData() {
        Intent intent=getIntent();
        man_name = intent.getStringExtra("man_name");
        woman_name=intent.getStringExtra("woman_name");
        man_logoname=intent.getStringExtra("man_logoname");
        woman_logoname=intent.getStringExtra("woman_logoname");
        Map<String, Bitmap> contentLogoImageMap = AssetsUtils.getContentLogoImageMap();
        Bitmap manBitmap = contentLogoImageMap.get(man_logoname);
        manIv.setImageBitmap(manBitmap);
        Bitmap womanBitmap = contentLogoImageMap.get(woman_logoname);
        womanIv.setImageBitmap(womanBitmap);

        manTv.setText(man_name);
        womanTv.setText(woman_name);
        pdTv.setText("星座配对:"+man_name+"和"+woman_name+"配对");
        vsTv.setText(man_name+"vs"+woman_name);
    }

    private void initView() {
        manTv=findViewById(R.id.partneranalysis_tv_man);
        womanTv=findViewById(R.id.partneranalysis_tv_woman);
        pdTv=findViewById(R.id.partneranalysis_tv_pd);
        vsTv=findViewById(R.id.partneranalysis_tv_vs);
        pfTv=findViewById(R.id.partneranalysis_tv_pf);
        bzTv=findViewById(R.id.partneranalysis_tv_bz);
        jxTv=findViewById(R.id.partneranalysis_tv_jx);
        zyTv=findViewById(R.id.partneranalysis_tv_zy);
        manIv=findViewById(R.id.partneranalysis_iv_man);
        womanIv=findViewById(R.id.partneranalysis_iv_woman);
        titleTv=findViewById(R.id.title_tv);
        backIv=findViewById(R.id.title_iv_back);


    }

    @Override
    public void onSuccess(String json) {
      if(!TextUtils.isEmpty(json)){
          PartnerAnalysisBean analysisBean=new Gson().fromJson(json,PartnerAnalysisBean.class);
          PartnerAnalysisBean.ResultBean result=analysisBean.getResult();
      }
    }
}