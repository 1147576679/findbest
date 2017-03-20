package com.example.zjy.fragment.community.vm;

import android.util.Log;

import com.example.zjy.fragment.community.api.CommunityService;
import com.example.zjy.fragment.community.bean.CommunityBean;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zjy on 2017/3/15.
 */

public class CommunityVM  {

    //ViewModel(业务逻辑与数据处理)
    //View只负责ui层面
    //数据处理完成 ->  View层

    //只负责数据处理
    //什么时候触发该方法


    //VM层复用
    //将公共部分提出去
    private Retrofit retrofit;

    private int page;
    private String url;

    public void getDataFromServer(final CallBack callback){
        url = String.format(Constants.URL_COMMUNITY,page);
        retrofit = new Retrofit.Builder()
                .baseUrl(CommunityService.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        retrofit.create(CommunityService.class)
                .getModel(url)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String,List<CommunityBean>>() {
                    @Override
                    public List<CommunityBean> call(String s) {
                        return ParseJsonUtils.parseCommunity(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CommunityBean>>() {
                    @Override
                    public void call(List<CommunityBean> communityBeen) {
                        callback.callBack(communityBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("tag", "错误信息"+throwable.getMessage());
                    }
                });

    }

    public void refresh(CallBack callBack){
        page = 0;
        getDataFromServer(callBack);
    }

    public void loadMore(CallBack callBack){
        page ++;
        getDataFromServer(callBack);
    }

//    public void getDataFromServer(String url, final Object o, final CallBack callback){
//        retrofit = new Retrofit.Builder()
//                .baseUrl(CommunityService.BASE_URL)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        retrofit.create(CommunityService.class)
//                .getModel(url)
//                .subscribeOn(Schedulers.io())
//                .map(new Func1<String,Object>() {
//                    @Override
//                    public Object call(String s) {
//                        return o;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object object) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        Log.i("tag", "错误信息"+throwable.getMessage());
//                    }
//                });

//    }



    public interface CallBack{
        void callBack(List<CommunityBean> data);
    }
}
