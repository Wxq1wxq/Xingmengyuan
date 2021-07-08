package com.example.xingmengyuan.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LuckURLContent {
    public static String getPartnerURL(String consName,String type){
        try {
            URLEncoder.encode(consName,"UTF-8");
            URLEncoder.encode(type,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        type="year";
        //http://web.juhe.cn:8080/constellation/getAll?consName=%E5%8F%8C%E9%B1%BC%E5%BA%A7&type=today&key=4b87562979559cbf2667447d4fc8f543
        String url="http://web.juhe.cn:8080/constellation/getAll?consName="+consName+"&type="+type+"&key=4b87562979559cbf2667447d4fc8f543";
        return url;
    }
}
