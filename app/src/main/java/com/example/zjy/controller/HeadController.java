package com.example.zjy.controller;

import com.example.zjy.bean.HeadAndTabBean;
import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.rxhelper.RxCallBack;
import com.example.zjy.rxhelper.RxSubscriber;
import com.example.zjy.util.ParseJsonUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zjy on 2017/4/12.
 */

public class HeadController extends BaseRxController {
    public HeadController() {
    }

    public Subscription head(String url, final RxCallBack<HeadAndTabBean> rxCallBack) {
        return rxAdd(ServiceAccessor.getService()
                .getModel(url)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, HeadAndTabBean>() {
                    @Override
                    public HeadAndTabBean call(String s) {
                        return ParseJsonUtils.parse(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<HeadAndTabBean>(rxCallBack) {
                    @Override
                    public void _onNext(HeadAndTabBean headAndTabBean) {
                        rxCallBack.onNext(headAndTabBean);
                    }

                    @Override
                    public void _onError(String msg, Throwable e) {
                        rxCallBack.onError(msg, e);
                    }
                }));
    }


}
