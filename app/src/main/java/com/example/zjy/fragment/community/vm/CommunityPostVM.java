package com.example.zjy.fragment.community.vm;

import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.fragment.community.bean.CommunityPostVO;
import com.example.zjy.fragment.community.bean.dto.CommunityPostDTO;
import com.example.zjy.util.Constants;
import com.google.gson.Gson;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zjy on 2017/4/1.
 */

public class CommunityPostVM {
    public static void getData(String id, final CallBack callBack){
        String url = String.format(Constants.URL_COMMUNITY_POST_DETAIL,id);
        ServiceAccessor.getService()
                .getModel(url)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, CommunityPostDTO>() {
                    @Override
                    public CommunityPostDTO call(String s) {
                        return new Gson().fromJson(s,CommunityPostDTO.class);
                    }
                })
                .map(new Func1<CommunityPostDTO, CommunityPostVO>() {
                    @Override
                    public CommunityPostVO call(CommunityPostDTO communityPostDTO) {
                        return communityPostDTO.transform();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommunityPostVO>() {
                    @Override
                    public void call(CommunityPostVO communityPostVO) {
                        callBack.callback(communityPostVO);
                    }
                });
    }

    public interface CallBack{
        void callback(CommunityPostVO communityPostVO);
    }
}
