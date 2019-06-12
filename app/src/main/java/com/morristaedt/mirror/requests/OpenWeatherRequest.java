package com.morristaedt.mirror.requests;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 */
public interface OpenWeatherRequest {

    String UNITS_METRIC = "metric";
    String UNITS_IMPERIAL = "imperial";

    @GET("/data/2.5/weather")
    OpenWeatherResponse getCurrentForecast(@Query("APPID") String apiKey,
                                           @Query("lat") String lat,
                                           @Query("lon") String lon,
                                           @Query("units") String units,
                                           @Query("lang") String language);
}
