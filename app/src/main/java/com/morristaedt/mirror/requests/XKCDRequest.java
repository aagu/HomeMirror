package com.morristaedt.mirror.requests;

import retrofit2.http.GET;

/**
 * Created by HannahMitt on 8/22/15.
 */
public interface XKCDRequest {

    @GET("/info.0.json")
    XKCDResponse getLatestXKCD();
}
