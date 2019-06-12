package com.morristaedt.mirror.requests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HannahMitt on 8/23/15.
 */
public interface ForecastRequest {

    String UNITS_M = "m";
    String UNITS_I = "i";

    @GET("/s6/weather/now")
    Call<ForecastResponse> getHourlyForecast(@Query("location") String location,
                                             @Query("key") String key,
                                             @Query("unit") String unit);
}
