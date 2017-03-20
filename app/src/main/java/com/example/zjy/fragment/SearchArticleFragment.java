package com.example.zjy.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zjy.adapter.SearchArticleRVAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchArticleBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.ParseJsonUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/28.
 * 文章与单品一起的 文章Fragment （设计感，吃货，生活。。。）
 */

public class SearchArticleFragment extends BaseFragment implements RetrofitUtil.DownListener {
    @Bind(R.id.rv_article)
    RecyclerView rv_article;
    private SearchArticleRVAdapter searchArticleRVAdapter;
    private String url;

    @Override
    public int getContentId() {
        return R.layout.fragment_vp_search_article;
    }

    public static SearchArticleFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url",url);
        SearchArticleFragment fragment = new SearchArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(View view) {
        searchArticleRVAdapter = new SearchArticleRVAdapter(getContext());
        rv_article.setAdapter(searchArticleRVAdapter);
        rv_article.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    protected void getDatas(Bundle bundle) {
        url = bundle.getString("url");
        loadDatas();
    }

    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x001);
    }

    @Override
    public Object paresJson(String json, int requestCode) {
//        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseSearchArticle(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null){
            List<SearchArticleBean> datas = (List<SearchArticleBean>) object;
            searchArticleRVAdapter.addDataAll(datas);
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
