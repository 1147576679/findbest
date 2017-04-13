package com.example.zjy.rxhelper;

import android.support.annotation.Nullable;

/**
 * Created by zjy on 2017/4/12.
 */

public abstract class RxCallBack<T> implements ICallBack<T> {

    public abstract  void onNext(T t) ;


    public void onError(String msg, @Nullable Throwable e) {

    }


    public void onCompleted() {

    }

    public void onTerminate() {

    }
}
