package com.example.xingmengyuan.starfrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xingmengyuan.R;

import java.util.List;

public class AnalysisBaseAdapter extends BaseAdapter {
    Context context;
    List<StarAnalysisBean> mData;

    public AnalysisBaseAdapter(Context context, List<StarAnalysisBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_star,null);
            vh=new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        StarAnalysisBean bean = mData.get(position);
        vh.titleTv.setText(bean.getTitle());
        vh.contentTv.setText(bean.getContent());
        vh.contentTv.setBackgroundResource(bean.getColor());
        return convertView;
    }
    class ViewHolder{
        TextView titleTv,contentTv;

        public ViewHolder(View view) {
            this.titleTv = view.findViewById(R.id.itemstar_tv_title);
            this.contentTv =view.findViewById(R.id.itemstar_tv_content);
        }
    }
}
