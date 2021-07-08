package com.example.xingmengyuan.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuideAdapter extends PagerAdapter {
    List<ImageView> mData;

    public GuideAdapter(List<ImageView> mData) {
        this.mData = mData;
    }

    public List<ImageView> getmData() {
        return mData;
    }

    public void setmData(List<ImageView> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        ImageView imageView=mData.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        ImageView imageView = mData.get(position);
        container.removeView(imageView);
    }
}
