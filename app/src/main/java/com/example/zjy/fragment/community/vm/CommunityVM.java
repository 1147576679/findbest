package com.example.zjy.fragment.community.vm;

import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.fragment.community.bean.CommunityVo;
import com.example.zjy.fragment.community.bean.dto.CommunityBean;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zjy on 2017/3/15.
 */

public class CommunityVM {

    //ViewModel(业务逻辑与数据处理)
    //View只负责ui层面
    //数据处理完成 ->  View层

    //只负责数据处理
    //什么时候触发该方法


    //VM层复用
    //将公共部分提出去

    private int page;
    private String url;


    public void getDataFromServer(final CallBack callback) {
        url = String.format(Constants.URL_COMMUNITY, page);
        // TODO: 2017/4/10  备选方案
//        String cache = DiskLruCacheUtil.getJsonCache(url);
        ServiceAccessor.getService()
                .getModel(url)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, CommunityBean>() {
                    @Override
                    public CommunityBean call(String s) {
                        DiskLruCacheUtil.removeCache(url);
                        DiskLruCacheUtil.putJsonCache(url,s);
                        return ParseJsonUtils.parseCommunity(s);
                    }
                })
                .map(new Func1<CommunityBean, List<CommunityVo>>() {
                    @Override
                    public List<CommunityVo> call(CommunityBean communityBean) {
                        return communityBean.transform();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CommunityVo>>() {
                    @Override
                    public void call(List<CommunityVo> communityVo) {
                        callback.callBack(communityVo);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        page--;
                       throwable.printStackTrace();
                    }
                });

    }


    public void refresh(CallBack callBack) {
        page = 0;
        getDataFromServer(callBack);
    }

    public void loadMore(CallBack callBack) {
        page++;
        getDataFromServer(callBack);
    }


    public interface CallBack {
        void callBack(List<CommunityVo> data);
    }
}
