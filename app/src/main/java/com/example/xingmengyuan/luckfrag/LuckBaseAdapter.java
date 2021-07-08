package com.example.xingmengyuan.luckfrag;

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

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LuckBaseAdapter extends BaseAdapter {
    //数据源：1.星座图片来源<contentlogoName>  2.星座名字<StarInforBean>本地json数据
   private Context context;
   private List<StarInfoBean.StarinfoDTO> mData;
   private Map<String, Bitmap> contentLogoName;

    public LuckBaseAdapter(Context context, List<StarInfoBean.StarinfoDTO> mData) {
        this.context = context;
        this.mData = mData;
        contentLogoName= AssetsUtils.getContentLogoImageMap();//初始化contenlogoName
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
        //在getView里写清楚适配器怎么工作，position下标的元素该怎么赋值操作
        ViewHolder vh=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_luck_gv,null);//convertView绑定到具体的Activity上
            vh=new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        StarInfoBean.StarinfoDTO starinfoDTO = mData.get(position);
        vh.luckTv.setText(starinfoDTO.getName());
        Bitmap bitmap = contentLogoName.get(starinfoDTO.getLogoname());
        vh.luckIv.setImageBitmap(bitmap);
        return convertView;
    }
    class ViewHolder{
        CircleImageView luckIv;
        TextView luckTv;
        public ViewHolder( View view){
            luckIv=view.findViewById(R.id.item_luck_iv);
            luckTv=view.findViewById(R.id.item_luck_tv);

        }

    }
}
