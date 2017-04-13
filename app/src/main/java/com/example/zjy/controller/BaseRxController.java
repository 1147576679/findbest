package com.example.zjy.controller;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zjy on 2017/4/12.
 */

public abstract class BaseRxController {
    protected CompositeSubscription mSubscription = new CompositeSubscription();

    protected Subscription rxAdd(Subscription subscription){
        mSubscription.add(subscription);
        return subscription;
    }

    public void detachView(){
        if(!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}
