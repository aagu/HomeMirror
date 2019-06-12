package com.morristaedt.mirror.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HttpUtil {

    public static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1000L, TimeUnit.MILLISECONDS)
                .readTimeout(1000L, TimeUnit.MILLISECONDS)
                .build();
    }
}
