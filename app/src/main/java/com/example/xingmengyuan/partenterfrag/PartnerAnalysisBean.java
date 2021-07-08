package com.example.xingmengyuan.partenterfrag;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PartnerAnalysisBean {

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @SerializedName("reason")
    private String reason;
    @SerializedName("result")
    private ResultDTO result;
    @SerializedName("error_code")
    private Integer errorCode;

    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        public String getMen() {
            return men;
        }

        public void setMen(String men) {
            this.men = men;
        }

        public String getWomen() {
            return women;
        }

        public void setWomen(String women) {
            this.women = women;
        }

        public String getZhishu() {
            return zhishu;
        }

        public void setZhishu(String zhishu) {
            this.zhishu = zhishu;
        }

        public String getBizhong() {
            return bizhong;
        }

        public void setBizhong(String bizhong) {
            this.bizhong = bizhong;
        }

        public String getXiangyue() {
            return xiangyue;
        }

        public void setXiangyue(String xiangyue) {
            this.xiangyue = xiangyue;
        }

        public String getTcdj() {
            return tcdj;
        }

        public void setTcdj(String tcdj) {
            this.tcdj = tcdj;
        }

        public String getJieguo() {
            return jieguo;
        }

        public void setJieguo(String jieguo) {
            this.jieguo = jieguo;
        }

        public String getLianai() {
            return lianai;
        }

        public void setLianai(String lianai) {
            this.lianai = lianai;
        }

        public String getZhuyi() {
            return zhuyi;
        }

        public void setZhuyi(String zhuyi) {
            this.zhuyi = zhuyi;
        }

        @SerializedName("men")
        private String men;
        @SerializedName("women")
        private String women;
        @SerializedName("zhishu")
        private String zhishu;
        @SerializedName("bizhong")
        private String bizhong;
        @SerializedName("xiangyue")
        private String xiangyue;
        @SerializedName("tcdj")
        private String tcdj;
        @SerializedName("jieguo")
        private String jieguo;
        @SerializedName("lianai")
        private String lianai;
        @SerializedName("zhuyi")
        private String zhuyi;
    }
}
