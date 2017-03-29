package com.example.zjy.fragment.community.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zjy on 2017/3/25.
 */

public class ServiceAccessor {
    private static volatile ApiService mService;
    private static volatile OkHttpClient mOkHttpClient;

    public static ApiService getService(){
        if(mService == null){
            synchronized (ApiService.class){
                if(mService == null){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiService.BASE_URL)
                            .client(getOkHttp())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    mService = retrofit.create(ApiService.class);
                }
            }
        }
        return mService;
    }

    public static OkHttpClient getOkHttp(){
        if(mOkHttpClient == null){
            synchronized (OkHttpClient.class){
                if(mOkHttpClient == null){
                    mOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }
}
