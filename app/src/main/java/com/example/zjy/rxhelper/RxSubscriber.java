package com.example.zjy.rxhelper;

import rx.Subscriber;

/**
 * Created by zjy on 2017/4/12.
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private RxCallBack mRxCallBack;

    public RxSubscriber(RxCallBack rxCallBack){
        mRxCallBack = rxCallBack;
    }

    @Override
    public void onCompleted() {
        _onComplete();
        mRxCallBack.onCompleted();
        mRxCallBack.onTerminate();
    }

    @Override
    public void onError(Throwable e) {
        _onError("",e);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }


    public abstract void _onNext(T t);


    public void _onComplete(){}

    public abstract void _onError(String msg, Throwable e);
}
