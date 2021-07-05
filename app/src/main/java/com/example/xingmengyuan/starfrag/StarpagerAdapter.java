package com.example.xingmengyuan.starfrag;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class StarpagerAdapter extends PagerAdapter {
    Context context;
    List<ImageView> imgList;

    public StarpagerAdapter(Context context, List<ImageView> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject( View view,  Object object) {

        return view==object;
    }


    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imgList.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        ImageView imageView=imgList.get(position);
        container.removeView(imageView);
    }
}
