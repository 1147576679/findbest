package com.example.zjy.rxhelper;

import android.support.annotation.Nullable;

/**
 * Created by zjy on 2017/4/12.
 */

public interface ICallBack<T> {
    void onNext(T t);

    void onError(String msg, @Nullable Throwable e);

    void onCompleted();

    void onTerminate();
}
