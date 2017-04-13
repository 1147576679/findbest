package com.example.zjy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.adapter.SearchProductRVAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchProductBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerGridItemDecoration;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/31.
 * 关键字搜索单品fragment
 */

public class SearchKeywordSingleFragment extends BaseFragment implements RetrofitUtil.DownListener {
    //搜索单品字符串拼接
    private static final String single ="product";
    private String url;
    private String keyword;

    @Bind(R.id.rv_search_product)
    RecyclerView rv_search_product;
    private SearchProductRVAdapter searchProductRVAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Log.i("tag", "onCreate: ");
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_search_keyword_single;
    }

    //通过粘性eventBus接收搜索界面发过的用户输入的关键字
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getKeyword(String keyword){
//        Toast.makeText(getContext(), "SearchKeywordSingleFragment收到"+keyword, Toast.LENGTH_SHORT).show();
        Log.i("tag", "SearchKeywordSingleFragment收到: "+keyword);
        this.keyword = keyword;
        Log.i("tag", "SearchKeywordSingleFragment收到: "+url);
        loadDatas();
    }

    @Override
    protected void init(View view) {
        searchProductRVAdapter = new SearchProductRVAdapter(getContext());
        rv_search_product.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        rv_search_product.setAdapter(searchProductRVAdapter);
        rv_search_product.addItemDecoration(new DividerGridItemDecoration(getContext()));
    }

    @Override
    protected void loadDatas() {
        url = String.format(Constants.URL_KEYWORD_SEARCH_SINGLE_TOPIC_USER_POST, single, keyword);
        // TODO: 2017/4/10  备选方案
//        String cache = DiskLruCacheUtil.getJsonCache(url);
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x001);
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseSearchProduct(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null) {
            SearchProductBean searchProductBean = (SearchProductBean) object;
            List<SearchProductBean.DataBean> dataBeanList = searchProductBean.getData();
            Log.i("tag", "downSucc: "+dataBeanList);
            searchProductRVAdapter.clearData();
            searchProductRVAdapter.addDataAll(dataBeanList);
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);
        Log.i("tag", "onDestroy: ");
    }


}
