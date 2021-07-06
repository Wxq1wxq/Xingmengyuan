package com.example.xingmengyuan.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLcontent {

    public static String getPartnerURL(String man,String woman){
        String url="131213";
        try {
            URLEncoder.encode(man,"UTF-8");
            URLEncoder.encode(woman,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
