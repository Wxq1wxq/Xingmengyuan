package com.example.xingmengyuan.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StarInfoBean implements Serializable {


    @SerializedName("starinfo")
    private List<StarinfoDTO> starinfo;

    @NoArgsConstructor
    @Data
    public static class StarinfoDTO implements Serializable {
        @SerializedName("logoname")
        private String logoname;
        @SerializedName("name")
        private String name;
        @SerializedName("date")
        private String date;
        @SerializedName("td")
        private String td;
        @SerializedName("gw")
        private String gw;
        @SerializedName("yy")
        private String yy;
        @SerializedName("tz")
        private String tz;
        @SerializedName("zg")
        private String zg;
        @SerializedName("ys")
        private String ys;
        @SerializedName("zb")
        private String zb;
        @SerializedName("hm")
        private String hm;
        @SerializedName("js")
        private String js;
        @SerializedName("info")
        private String info;
    }
}
