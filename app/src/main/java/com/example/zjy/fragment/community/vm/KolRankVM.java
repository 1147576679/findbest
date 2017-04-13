package com.example.zjy.fragment.community.vm;

import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.community.bean.KolRankVo;
import com.example.zjy.fragment.community.bean.dto.KolRankDto;
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
 * Created by zjy on 2017/4/5.
 */

public class KolRankVM {
    public static void getData(final CallBack<List<KolRankVo>> callBack) {
        // TODO: 2017/4/10  备选方案
//        String cache = DiskLruCacheUtil.getJsonCache(Constants.URL_KOL_RANK);
        ServiceAccessor.getService()
                .getModel(Constants.URL_KOL_RANK)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, KolRankDto>() {
                    @Override
                    public KolRankDto call(String s) {
                        DiskLruCacheUtil.removeCache(Constants.URL_KOL_RANK);
                        DiskLruCacheUtil.putJsonCache(Constants.URL_KOL_RANK,s);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s).getJSONObject("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return new Gson().fromJson(jsonObject.toString(), KolRankDto.class);
                    }
                })
                .map(new Func1<KolRankDto, List<KolRankVo>>() {
                    @Override
                    public List<KolRankVo> call(KolRankDto kolRankDto) {
                        return kolRankDto.transform();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<KolRankVo>>() {
                    @Override
                    public void call(List<KolRankVo> kolRankVos) {
                        callBack.callBack(kolRankVos);
                    }
                });
    }
}
