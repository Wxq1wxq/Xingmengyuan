package com.example.xingmengyuan.starfrag;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xingmengyuan.R;
import com.example.xingmengyuan.bean.StarInfoBean;
import com.example.xingmengyuan.utils.AssetsUtils;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarBaseAdapter extends BaseAdapter {
    Context context;
    List<StarInfoBean.StarinfoDTO> mData;
    Map<String, Bitmap> logoMap;
    public StarBaseAdapter(Context context, List<StarInfoBean.StarinfoDTO> mData) {
        this.context = context;
        this.mData = mData;
        logoMap= AssetsUtils.getLogoImgMap();
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_star_gv,null);
            vh=new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        StarInfoBean.StarinfoDTO bean = mData.get(position);
        vh.tv.setText(bean.getName());
       String logoname= bean.getLogoname();
        Bitmap bitmap = logoMap.get(logoname);
        vh.iv.setImageBitmap(bitmap);
        return convertView;
    }

    class ViewHolder{
        CircleImageView iv;
        TextView tv;
        public ViewHolder(View view) {
            iv=view.findViewById(R.id.item_star_iv);
            tv=view.findViewById(R.id.item_star_tv);
        }
    }
}
