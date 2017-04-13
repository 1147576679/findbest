package com.example.zjy.fragment.message.controller;

import com.example.zjy.controller.BaseRxController;
import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.fragment.message.bean.dto.NotificationDto;
import com.example.zjy.fragment.message.bean.dto.NotificationVo;
import com.example.zjy.rxhelper.RxCallBack;
import com.example.zjy.rxhelper.RxSubscriber;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zjy on 2017/4/13.
 */

public class NotificationController extends BaseRxController {

    public Subscription notification(String url, final RxCallBack<List<NotificationVo>> rxCallBack) {
        return rxAdd(ServiceAccessor.getService()
                .getModel(url)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, NotificationDto>() {
                    @Override
                    public NotificationDto call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s).getJSONObject("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return new Gson().fromJson(jsonObject.toString(), NotificationDto.class);
                    }
                })
                .map(new Func1<NotificationDto, List<NotificationVo>>() {
                    @Override
                    public List<NotificationVo> call(NotificationDto notificationDto) {
                        return notificationDto.transform();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<List<NotificationVo>>(rxCallBack) {
                    @Override
                    public void _onNext(List<NotificationVo> notificationVos) {
                        rxCallBack.onNext(notificationVos);
                    }

                    @Override
                    public void _onError(String msg, Throwable e) {
                        rxCallBack.onError(msg, e);
                    }
                }));
    }
}
