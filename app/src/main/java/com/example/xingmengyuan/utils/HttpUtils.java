package com.example.xingmengyuan.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
                    public static String getJsonFromNet(String path){

                        String json="";
                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                        try {
                            URL url=new URL(path);
                            try {
                                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                                con.connect();
                                InputStream is=con.getInputStream();
                                int count=0;
                                byte[] buf=new byte[1024];
                                while((count=is.read(buf))!=-1){
                                    baos.write(buf,0,count);
                                }
                                is.close();
                json=baos.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
       return json;
    }
}
