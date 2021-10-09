package com.example.xingmengyuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.xingmengyuan.bean.StarInfoBean;
import com.example.xingmengyuan.luckfrag.LuckFragment;
import com.example.xingmengyuan.mefrag.MeFragment;
import com.example.xingmengyuan.partenterfrag.PartnerFragment;
import com.example.xingmengyuan.starfrag.StarFragment;
import com.example.xingmengyuan.utils.AssetsUtils;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
RadioGroup mainRg;
Fragment starFag;
Fragment partnerFag;
Fragment luckFag;
Fragment meFag;
private FragmentManager manger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //下3行：影藏导航栏
        View decorView=getWindow().getDecorView();
        int uiOptions=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        StarInfoBean infoBean=loadData();
        Bundle bundle=new Bundle();
        bundle.putSerializable("info",infoBean);
        mainRg=findViewById(R.id.main_rg);
        starFag=new StarFragment();
        starFag.setArguments(bundle);
        partnerFag=new PartnerFragment();
        partnerFag.setArguments(bundle);
        luckFag=new LuckFragment();
        luckFag.setArguments(bundle);
        meFag=new MeFragment();
        meFag.setArguments(bundle);
        addFragmentPage();
        mainRg.setOnCheckedChangeListener(this);
    }

    private StarInfoBean loadData() {
        String json=AssetsUtils.getJsonFromAssets(this,"xzcontent/xzcontent.json");//获取本地json数据为什么不需要子线程中进行(非耗时操作，没有耗时2.5s)，获取网络json数据则需要
        Gson gson=new Gson();
        StarInfoBean starInfoBean = gson.fromJson(json, StarInfoBean.class);
        AssetsUtils.saveBitmapFromAssets(this,starInfoBean);
        return starInfoBean;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manger.beginTransaction();
        switch (checkedId) {
            case R.id.main_rb_star:
                transaction.hide(partnerFag);
                transaction.hide(luckFag);
                transaction.hide(meFag);
                transaction.show(starFag);
                break;
            case R.id.main_rb_partner:
                transaction.hide(starFag);
                transaction.hide(luckFag);
                transaction.hide(meFag);
                transaction.show(partnerFag);
                break;
            case R.id.main_rb_luck:
                transaction.hide(partnerFag);
                transaction.hide(starFag);
                transaction.hide(meFag);
                transaction.show(luckFag);
                break;
            case R.id.main_rb_me:
                transaction.hide(partnerFag);
                transaction.hide(luckFag);
                transaction.hide(starFag);
                transaction.show(meFag);
                break;
        }
        transaction.commit();
    }
    public  void addFragmentPage()
    {
       manger= getSupportFragmentManager();
        FragmentTransaction transaction = manger.beginTransaction();
        transaction.add(R.id.main_layout_center,starFag);
        transaction.add(R.id.main_layout_center,partnerFag);
        transaction.add(R.id.main_layout_center,luckFag);
        transaction.add(R.id.main_layout_center,meFag);
        transaction.hide(partnerFag);
        transaction.hide(luckFag);
        transaction.hide(meFag);
        transaction.commit();

    }

}