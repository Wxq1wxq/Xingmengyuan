package com.example.xingmengyuan.mefrag;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.xingmengyuan.R;
import com.example.xingmengyuan.bean.StarInfoBean;
import com.example.xingmengyuan.luckfrag.LuckBaseAdapter;
import com.example.xingmengyuan.utils.AssetsUtils;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class MeFragment extends Fragment implements View.OnClickListener {
    CircleImageView iconIv;
    TextView nameTv;
    Map<String, Bitmap> contentLogoImageMap;
    StarInfoBean bean;
    private SharedPreferences starPref;
    int index;
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        bean =(StarInfoBean) bundle.getSerializable("info");
        bean.getStarinfo();
        starPref = getContext().getSharedPreferences("star_pref", Context.MODE_PRIVATE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_me, container, false);
        iconIv=view.findViewById(R.id.mefrag_iv);
        nameTv=view.findViewById(R.id.me_name_tv);

        contentLogoImageMap = AssetsUtils.getContentLogoImageMap();
        String name=starPref.getString("name","白羊座");
        String logoName=starPref.getString("logoname","baiyang");
        Bitmap bitmap = contentLogoImageMap.get(logoName);
        iconIv.setImageBitmap(bitmap);
        nameTv.setText(name);
        iconIv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mefrag_iv:
                showDialog();
                break;
        }
    }
    private void showDialog(){
        Dialog dialog=new Dialog(getContext());
        View dialogView=LayoutInflater.from(getContext()).inflate(R.layout.medialog,null);
        dialog.setContentView(dialogView);
        dialog.setTitle("请选择星座");
        GridView dialogGv=dialogView.findViewById(R.id.medialogo_gv);

        LuckBaseAdapter adapter=new LuckBaseAdapter(getContext(),bean.getStarinfo());
        dialogGv.setAdapter(adapter);
        dialog.setCanceledOnTouchOutside(true);
        dialogGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<StarInfoBean.StarinfoDTO> mData = bean.getStarinfo();
                StarInfoBean.StarinfoDTO starinfoBean = mData.get(position);
                String name=starinfoBean.getName();
                String logoName=starinfoBean.getLogoname();
                nameTv.setText(name);
                Bitmap bitmap = contentLogoImageMap.get(logoName);
                iconIv.setImageBitmap(bitmap);
                index=position;
                dialog.cancel();

            }
        });

        dialog.show();

    }
//失去焦点时启动该方法，用于把上次选择的图案的下标index所对应的logoname，name通过SharedPreferences.Editor的putString方法保存，以便下次打开程序时调用
    //共享数据方法，important
    @Override
    public void onPause() {
        super.onPause();
        List<StarInfoBean.StarinfoDTO> mData = bean.getStarinfo();
        StarInfoBean.StarinfoDTO starinfoBean = mData.get(index);
        String name=starinfoBean.getName();
        String logoName=starinfoBean.getLogoname();
        SharedPreferences.Editor editor=starPref.edit();
        editor.putString("name",name);
        editor.putString("logoname",logoName);
        editor.commit();
    }
}