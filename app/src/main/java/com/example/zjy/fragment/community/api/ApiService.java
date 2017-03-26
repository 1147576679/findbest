package com.example.zjy.fragment.community.api;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by zjy on 2017/3/16.
 */

public interface ApiService {
    String BASE_URL = "http://www.baidu.com";
    @GET
    Observable<String> getModel(@Url String url);

}
