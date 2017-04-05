package com.example.zjy.fragment.community.vm;

import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.community.bean.TopicRankVo;
import com.example.zjy.fragment.community.bean.dto.TopicRankDto;
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
 * Created by zjy on 2017/4/5.
 */

public class TopicRankVM {
    public static void getData(final CallBack<List<TopicRankVo>> callBack) {
        ServiceAccessor.getService()
                .getModel(Constants.URL_TOPIC_RANK)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, TopicRankDto>() {
                    @Override
                    public TopicRankDto call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s).getJSONObject("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return new Gson().fromJson(jsonObject.toString(), TopicRankDto.class);
                    }
                })
                .map(new Func1<TopicRankDto, List<TopicRankVo>>() {
                    @Override
                    public List<TopicRankVo> call(TopicRankDto topicRankDto) {
                        return topicRankDto.transform();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<TopicRankVo>>() {
                    @Override
                    public void call(List<TopicRankVo> topicRankVo) {
                        callBack.callBack(topicRankVo);
                    }
                });
    }
}
