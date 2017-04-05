package com.example.zjy.fragment.community.vm;

import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.community.bean.PostRankVo;
import com.example.zjy.fragment.community.bean.dto.PostRankDto;
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

public class PostRankVM {
    public static void getData(final CallBack<List<PostRankVo>> callBack) {
        ServiceAccessor.getService()
                .getModel(Constants.URL_POST_RANK)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, PostRankDto>() {
                    @Override
                    public PostRankDto call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s).getJSONObject("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return new Gson().fromJson(jsonObject.toString(), PostRankDto.class);
                    }
                })
                .map(new Func1<PostRankDto, List<PostRankVo>>() {
                    @Override
                    public List<PostRankVo> call(PostRankDto postRankDto) {
                        return postRankDto.transform();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PostRankVo>>() {
                    @Override
                    public void call(List<PostRankVo> postRankVos) {
                        callBack.callBack(postRankVos);
                    }
                });
    }
}
