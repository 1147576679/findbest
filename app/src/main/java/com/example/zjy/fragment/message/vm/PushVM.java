package com.example.zjy.fragment.message.vm;

import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.message.bean.PushVo;
import com.example.zjy.fragment.message.bean.dto.PushDto;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.util.Constants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zjy on 2017/4/6.
 */

public class PushVM {
    public static void getData(final CallBack<List<PushVo>> callBack) {
        // TODO: 2017/4/10  备选方案
//        String cache = DiskLruCacheUtil.getJsonCache(Constants.URL_MESSAGE_PUSH);
        ServiceAccessor.getService()
                .getModel(Constants.URL_MESSAGE_PUSH)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, PushDto>() {
                    @Override
                    public PushDto call(String s) {
                        DiskLruCacheUtil.removeCache(Constants.URL_MESSAGE_PUSH);
                        DiskLruCacheUtil.putJsonCache(Constants.URL_MESSAGE_PUSH,s);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s).getJSONObject("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return new Gson().fromJson(jsonObject.toString(), PushDto.class);
                    }
                })
                .map(new Func1<PushDto, List<PushVo>>() {
                    @Override
                    public List<PushVo> call(PushDto pushDto) {
                        return pushDto.transform();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PushVo>>() {
                    @Override
                    public void call(List<PushVo> topicRankVo) {
                        callBack.callBack(topicRankVo);
                    }
                });
    }

    public static void refreshData(CallBack<List<PushVo>> callBack){
        getData(callBack);
    }
}
