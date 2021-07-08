package com.example.xingmengyuan.luckfrag;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.xingmengyuan.R;
import com.example.xingmengyuan.bean.StarInfoBean;

import java.io.Serializable;
import java.util.List;


public class LuckFragment extends Fragment  implements GridView.OnItemClickListener {
GridView luckGv;
    List<StarInfoBean.StarinfoDTO> mData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_luck, container, false);
        luckGv=view.findViewById(R.id.luckfrag_gv);
//        Bundle bundle =new Bundle();//null
        Bundle bundle=getArguments();//获取
        StarInfoBean bean = (StarInfoBean) bundle.getSerializable("info");
        mData = bean.getStarinfo();
        LuckBaseAdapter adapter=new LuckBaseAdapter(getContext(),mData);
        luckGv.setAdapter(adapter);
        luckGv.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StarInfoBean.StarinfoDTO starinfoDTO = mData.get(position);
        String name=starinfoDTO.getName();
        Intent intent=new Intent(getContext(),LuckAnalysisActivity.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
