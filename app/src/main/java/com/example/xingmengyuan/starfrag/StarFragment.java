package com.example.xingmengyuan.starfrag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.example.xingmengyuan.R;
import com.example.xingmengyuan.bean.StarInfoBean;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class StarFragment extends Fragment {
ViewPager starVp;//用作star页广告位置
GridView starGv;//用于star页星座信息位置
LinearLayout pointLayout;
int[] imageId={R.mipmap.pic_guanggao,R.mipmap.pic_star};//数组存储广告内容，共2张图片
List<ImageView>ivList;//list数组存储广告页ImageView
List<ImageView>pointList;//用于广告页翻转“..”
private List<StarInfoBean.StarinfoDTO> mData;//数据源
StarpagerAdapter starpagerAdapter;
Handler handler=new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
       if(msg.what==1){
           int currentItem = starVp.getCurrentItem();
           if(currentItem==ivList.size()-1){
               starVp.setCurrentItem(0);
           }else{
               currentItem++;
               starVp.setCurrentItem(currentItem);
           }
           handler.sendEmptyMessageDelayed(1,10000);
       }
    }
};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_star, container, false);//创建一个starFrag的View
        initView(view);
        //----------------------------------?----------------------------------------------
        Bundle arguments = getArguments();
        StarInfoBean infoBean=(StarInfoBean)arguments.getSerializable("info");//创建一个json对象，并且把key=“info”的值拿到
        mData=infoBean.getStarinfo();
        StarBaseAdapter starBaseAdapter = new StarBaseAdapter(getContext(), mData);
        starGv.setAdapter(starBaseAdapter);
        initPage();
        setVPListener();
        setGvLinstener();
        handler.sendEmptyMessageDelayed(1,10000);
        return view;
    }

    private void setGvLinstener() {
        //跳转到staranalysis页面，使用Intent
        starGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarInfoBean.StarinfoDTO bean = mData.get(position);//点击的数据
                Intent intent=new Intent(getContext(),StarAnalysisActivity.class);
                intent.putExtra("star",bean);//传输数据到StarAnalysisActivity.class
                startActivity(intent);
            }
        });
    }

    private void initPage() {
        ivList=new ArrayList<>();
        pointList=new ArrayList<>();
        for(int i=0;i<imageId.length;i++){
            ImageView imageView=new ImageView(getContext());
            imageView.setImageResource(imageId[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片显示格式：充满整个View，不按照长宽比例缩放
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);//设置View的Layout属性
            ivList.add(imageView);
            //设置点的属性
            ImageView piv=new ImageView(getContext());
            piv.setImageResource(R.mipmap.point_normal);
            LinearLayout.LayoutParams plp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            plp.setMargins(20,0,0,0);
            piv.setLayoutParams(plp);
            pointLayout.addView(piv);
            pointList.add(piv);
        }
        pointList.get(0).setImageResource(R.mipmap.point_focus);
        starpagerAdapter=new StarpagerAdapter(getContext(),ivList);
        starVp.setAdapter(starpagerAdapter);

    }

    private void initView(View view) {
        starVp=view.findViewById(R.id.starfrag_vp);
        starGv=view.findViewById(R.id.starfrag_gv);
        pointLayout=view.findViewById(R.id.starfrag_linearlayout);
    }
    private void setVPListener(){//使点的checked和图片一致
        starVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<pointList.size();i++){
                    pointList.get(i).setImageResource(R.mipmap.point_normal);
                }
                pointList.get(position).setImageResource(R.mipmap.point_focus);
            }
        });
    }
}
