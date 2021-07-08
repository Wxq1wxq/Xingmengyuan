package com.example.xingmengyuan.partenterfrag;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.xingmengyuan.MainActivity;
import com.example.xingmengyuan.R;
import com.example.xingmengyuan.bean.StarInfoBean;
import com.example.xingmengyuan.utils.AssetsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PartnerFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ImageView manIv,womanIv;//男女配对分析星座图片
    Spinner manSp,womanSp;//选择星座的下拉选项列表
    Button prizeBtn,analysisBtn;//每日抽奖和配对分析button
    List<StarInfoBean.StarinfoDTO> starInfoList;
    List<String> nameList;
    Map<String, Bitmap> contentLogoImageMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_partner, container, false);
        initView(view);
        Bundle bundle = getArguments();
        StarInfoBean starBean = (StarInfoBean)bundle.getSerializable("info");//获取MainActivity传递过来的数据Bean
        starInfoList = starBean.getStarinfo();

        nameList=new ArrayList<>();
        for(int i=0;i<starInfoList.size();i++){//将starInfoList中存储的name存放到nameList中
            String name=starInfoList.get(i).getName();
            nameList.add(name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.item_partner_sp, R.id.item_partner_tv, nameList);
        manSp.setAdapter(adapter);
        womanSp.setAdapter(adapter);

        String logoname = starInfoList.get(0).getLogoname();
       contentLogoImageMap = AssetsUtils.getContentLogoImageMap();
       //刚进入界面男女都设置成白羊座图案
        Bitmap bitmap = contentLogoImageMap.get(logoname);
        manIv.setImageBitmap(bitmap);
        womanIv.setImageBitmap(bitmap);

        return view;
    }

    private void initView(View view) {
        manIv=view.findViewById(R.id.partnerfrag_iv_man);
        womanIv=view.findViewById(R.id.partnerfrag_iv_woman);
        manSp=view.findViewById(R.id.partnerfrag_sp_man);
        womanSp=view.findViewById(R.id.partnerfrag_sp_woman);
        prizeBtn=view.findViewById(R.id.partnerfrag_btn_prize);
        analysisBtn=view.findViewById(R.id.partnerfrag_btn_analysis);

        prizeBtn.setOnClickListener(this);
        analysisBtn.setOnClickListener(this);

        manSp.setOnItemSelectedListener(this);
        womanSp.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.partnerfrag_btn_prize:
                AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
                dialog.setTitle("榔头警告")
                        .setMessage("还想白嫖？")
                        .setCancelable(true)
                        .setIcon(R.mipmap.ib_luck_focus)
                        .create();
                dialog.show();

                break;
            case R.id.partnerfrag_btn_analysis:
                int manSpPosition = manSp.getSelectedItemPosition();
                int womanSpPosition = womanSp.getSelectedItemPosition();

                Intent intent=new Intent(getContext(),PartnerAnalysisActivity.class);

                intent.putExtra("man_name",starInfoList.get(manSpPosition).getName());
                intent.putExtra("man_logoname",starInfoList.get(manSpPosition).getLogoname());
                intent.putExtra("woman_name",starInfoList.get(womanSpPosition).getName());
                intent.putExtra("woman_logoname",starInfoList.get(womanSpPosition).getLogoname());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.partnerfrag_sp_man:
                String logoname = starInfoList.get(position).getLogoname();
                Bitmap bitmap = contentLogoImageMap.get(logoname);
                manIv.setImageBitmap(bitmap);
                break;
            case R.id.partnerfrag_sp_woman:
               logoname = starInfoList.get(position).getLogoname();
                 bitmap = contentLogoImageMap.get(logoname);
                womanIv.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}