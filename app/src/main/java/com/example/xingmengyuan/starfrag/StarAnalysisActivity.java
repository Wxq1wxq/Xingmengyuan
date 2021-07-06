package com.example.xingmengyuan.starfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xingmengyuan.R;
import com.example.xingmengyuan.bean.StarInfoBean;
import com.example.xingmengyuan.utils.AssetsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAnalysisActivity extends AppCompatActivity implements View.OnClickListener {
    TextView titleTv;//title标签
    ImageView backIv;//
    CircleImageView iconIv;//圆形图标
    TextView nameTv,dateTv;//姓名日期
    ListView analysisLv;//listview列表
    StarInfoBean.StarinfoDTO bean;
    Map<String, Bitmap> contentLogoImageMap;
    TextView footTv;//listView底部布局
    List<StarAnalysisBean> mData;
    AnalysisBaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_analysis);
        Intent intent=getIntent();
        bean = (StarInfoBean.StarinfoDTO)intent.getSerializableExtra("star");

        initView();
        mData=new ArrayList<>();
        adapter=new AnalysisBaseAdapter(this,mData);
        analysisLv.setAdapter(adapter);
        addDataToList();

    }
    public  void addDataToList(){
        StarAnalysisBean sab1=new StarAnalysisBean("性格特点:",bean.getTd(),R.color.orange);
        StarAnalysisBean sab2=new StarAnalysisBean("掌管宫位:",bean.getGw(),R.color.pink);
        StarAnalysisBean sab3=new StarAnalysisBean("显阴阳性:",bean.getYy(),R.color.white);
        StarAnalysisBean sab4=new StarAnalysisBean("最大特征:",bean.getTz(),R.color.colorAccent);
        StarAnalysisBean sab5=new StarAnalysisBean("主管星球:",bean.getZg(),R.color.colorPrimaryDark);
        StarAnalysisBean sab6=new StarAnalysisBean("幸运颜色:",bean.getYs(),R.color.design_default_color_error);
        StarAnalysisBean sab7=new StarAnalysisBean("搭配珠宝:",bean.getZb(),R.color.design_default_color_primary_dark);
        StarAnalysisBean sab8=new StarAnalysisBean("幸运号码:",bean.getHm(),R.color.material_on_surface_disabled);
        StarAnalysisBean sab9=new StarAnalysisBean("相配金属:",bean.getJs(),R.color.lightred);
        mData.add(sab1);
        mData.add(sab2);
        mData.add(sab3);
        mData.add(sab4);
        mData.add(sab5);
        mData.add(sab6);
        mData.add(sab7);
        mData.add(sab8);
        mData.add(sab9);
        adapter.notifyDataSetChanged();//提示适配器数据更新了
    }

    private void initView() {
        titleTv=findViewById(R.id.title_tv);
        backIv=findViewById(R.id.title_iv_back);
        iconIv=findViewById(R.id.staranalysis_iv);
        nameTv=findViewById(R.id.star_tv_name);
        dateTv=findViewById(R.id.star_tv_date);
        analysisLv=findViewById(R.id.staranalysis_lv);

        View footView=LayoutInflater.from(this).inflate(R.layout.footer_star_analysis,null);
        analysisLv.addFooterView(footView);
        footTv=footView.findViewById(R.id.footstar_info);


        titleTv.setText("星座详情");
        backIv.setOnClickListener(this);

        nameTv.setText(bean.getName());
        dateTv.setText(bean.getDate());
        contentLogoImageMap = AssetsUtils.getContentLogoImageMap();
        Bitmap bitmap=contentLogoImageMap.get(bean.getLogoname());
        iconIv.setImageBitmap(bitmap);
        footTv.setText(bean.getInfo());

    }

    @Override
    public void onClick(View v) {
        finish();//结束当前activity，返回上一级activity
    }
}