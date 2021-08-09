package com.example.a20_androidretrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/08/06
 * </pre>
 */
public interface API {

    @GET("/get/text")
    Call<ResponseBody> getJson();

}
