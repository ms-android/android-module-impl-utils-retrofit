package com.ms.module.impl.utils.retrofit;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {

    private static Map<String, Retrofit> caches = new HashMap<>();

    public static Retrofit getRetrofit(String baseUrl) {

        if (baseUrl == null) {
            throw new NullPointerException("baseUrl null");
        }

        Retrofit retrofit = caches.get(baseUrl);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            caches.put(baseUrl, retrofit);
        }
        return retrofit;
    }
}
