package com.example.xingmengyuan.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLcontent {

    public static String getPartnerURL(String man,String woman){
        man=man.replace("座","");
        woman=woman.replace("座","");

        try {
            URLEncoder.encode(man,"UTF-8");
            URLEncoder.encode(woman,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url="http://apis.juhe.cn/xzpd/query?men="+man+"&women="+woman+"&key=0c59100726a91a8c078fcbe35d2839cb";
        return url;
    }
}
