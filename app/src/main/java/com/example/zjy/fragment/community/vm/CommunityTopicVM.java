package com.example.zjy.fragment.community.vm;

import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.fragment.community.api.ServiceAccessor;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zjy on 2017/4/1.
 */

public class CommunityTopicVM {

    public static void getData(String id, final CallBack callBack){
        final String url = String.format(Constants.URL_COMMUNITY_DETAIL,id);
        // TODO: 2017/4/10  备选方案
//        String cache = DiskLruCacheUtil.getJsonCache(url);
        ServiceAccessor.getService()
                .getModel(url)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, ItemDetailBean>() {
                    @Override
                    public ItemDetailBean call(String s) {
                        DiskLruCacheUtil.removeCache(url);
                        DiskLruCacheUtil.putJsonCache(url,s);
                        return ParseJsonUtils.parseItemDetail(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ItemDetailBean>() {
                    @Override
                    public void call(ItemDetailBean itemDetailBean) {
                        callBack.callBack(itemDetailBean);
                    }
                });
    }

    public interface CallBack {
        void callBack(ItemDetailBean data);
    }
}
