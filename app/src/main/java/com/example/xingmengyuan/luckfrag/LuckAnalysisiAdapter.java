package com.example.xingmengyuan.luckfrag;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xingmengyuan.R;

import java.util.List;

public class LuckAnalysisiAdapter extends BaseAdapter {
    Context context;
    List<LuckItemBean> mDatas;

    public LuckAnalysisiAdapter(Context context, List<LuckItemBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_luckanalysis_lv,null);
            vh=new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();
        }
        LuckItemBean luckItemBean = mDatas.get(position);
        vh.contentTv.setText(luckItemBean.getContent());
        vh.titleTv.setText(luckItemBean.getTitle());
        GradientDrawable drawable = (GradientDrawable) vh.titleTv.getBackground();
        drawable.setColor(luckItemBean.getColorId());

        return convertView;
    }
    class ViewHolder{
        TextView titleTv,contentTv;
        public ViewHolder(View view){
            titleTv=view.findViewById(R.id.item_luckanalysis_tv_title);
            contentTv=view.findViewById(R.id.item_luckanalysis_tv_content);
        }
    }
}
