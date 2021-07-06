package com.example.xingmengyuan.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.xingmengyuan.bean.StarInfoBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetsUtils {
    private  static Map<String,Bitmap> logoImgMap;
    private  static Map<String,Bitmap> contentLogoImageMap;
public  static String getJsonFromAssets(Context context, String name)
{   //获取数据
    AssetManager am = context.getResources().getAssets();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {//从文件中读取到内存
        InputStream inputStream = am.open(name);
        int count=0;
        byte[] buf=new byte[1024];
        while((count=inputStream.read(buf))!=-1){
            byteArrayOutputStream.write(buf,0,count);
        }
        String msg=byteArrayOutputStream.toString();
        inputStream.close();
        return msg;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}
public  static Bitmap getBitmapFormAssets(Context context,String filename){
    Bitmap bitmap=null;
    AssetManager am=context.getResources().getAssets();
    try {
        InputStream is=am.open(filename);
        bitmap= BitmapFactory.decodeStream(is);
        is.close();
        return bitmap;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}
public  static void saveBitmapFromAssets(Context context, StarInfoBean starInfoBean){
    logoImgMap=new HashMap<>();
    contentLogoImageMap=new HashMap<>();
    List<StarInfoBean.StarinfoDTO> starList=starInfoBean.getStarinfo();
    for(int i=0;i<starList.size();i++)
    {
        String logoname=starList.get(i).getLogoname();
        String filename="xzlogo/"+logoname+".png";
        Bitmap bitmap=getBitmapFormAssets(context,filename);
        logoImgMap.put(logoname,bitmap);
        String contentName="xzcontentlogo/"+logoname+".png";
        Bitmap bitmap1=getBitmapFormAssets(context,contentName);
        contentLogoImageMap.put(logoname,bitmap1);
    }

}

    public static Map<String, Bitmap> getLogoImgMap() {
        return logoImgMap;
    }

    public static Map<String, Bitmap> getContentLogoImageMap() {
        return contentLogoImageMap;
    }
}
