package com.morristaedt.mirror.requests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HannahMitt on 8/23/15.
 */
public class ForecastResponse {

    @SerializedName("HeWeather6")
    public List<HeWeather6> heWeather6;

    public class HeWeather6{

        //城市信息
        public Basic basic;
        //天气信息
        public Now now;
        //获取状态
        public String status;
        //更新时间
        public Update update;

        @Override
        public String toString() {
            return "HeWeather6{" +
                    "status='" + status + '\'' +
                    '}';
        }
    }

    public class Basic{

        //地区id
        public String cid;
        //地区名称
        public String location;
        //地区所在的市级单位
        @SerializedName(value = "parentCity",alternate = "parent_city")
        public String parentCity;
        //地区所在的省级单位
        @SerializedName(value = "adminArea",alternate = "admin_area")
        public String adminArea;
        //地区所在的国家
        public String cnty;
        //经度
        public String lat;
        //纬度
        public String lon;
        //时区
        public String tz;

        @Override
        public String toString() {
            return "Basic{" +
                    "cid='" + cid + '\'' +
                    ", location='" + location + '\'' +
                    ", parentCity='" + parentCity + '\'' +
                    ", adminArea='" + adminArea + '\'' +
                    ", cnty='" + cnty + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    ", tz='" + tz + '\'' +
                    '}';
        }
    }

    public class Now{

        //体感温度
        public String fl;
        //温度
        public String tmp;
        //实况天气状况代码
        @SerializedName(value = "condCode",alternate = "cond_code")
        public String condCode;
        //实况天气状况描述
        @SerializedName(value = "condTxt",alternate = "cond_txt")
        public String condTxt;
        //风向360角度
        @SerializedName(value = "windDeg",alternate = "wind_deg")
        public String windDeg;
        //风向
        @SerializedName(value = "windDir",alternate = "wind_dir")
        public String windDir;
        //风力
        @SerializedName(value = "windSc",alternate = "wind_sc")
        public String windSc;
        //风速
        @SerializedName(value = "windSpd",alternate = "wind_spd")
        public String windSpd;
        //相对湿度
        public String hum;
        //降水量
        public String pcpn;
        //大气压强
        public String pres;
        //能见度
        public String vis;
        //云量
        public String cloud;

        @Override
        public String toString() {
            return "Now{" +
                    "fl='" + fl + '\'' +
                    ", tmp='" + tmp + '\'' +
                    ", condCode='" + condCode + '\'' +
                    ", condTxt='" + condTxt + '\'' +
                    ", windDeg='" + windDeg + '\'' +
                    ", windDir='" + windDir + '\'' +
                    ", windSc='" + windSc + '\'' +
                    ", windSpd='" + windSpd + '\'' +
                    ", hum='" + hum + '\'' +
                    ", pcpn='" + pcpn + '\'' +
                    ", pres='" + pres + '\'' +
                    ", vis='" + vis + '\'' +
                    ", cloud='" + cloud + '\'' +
                    '}';
        }
    }

    public class Update{

        //当地时间
        public String loc;
        //UTC时间
        public String utc;

        @Override
        public String toString() {
            return "Update{" +
                    "loc='" + loc + '\'' +
                    ", utc='" + utc + '\'' +
                    '}';
        }
    }
}
