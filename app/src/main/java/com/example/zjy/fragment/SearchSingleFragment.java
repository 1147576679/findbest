package com.example.zjy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.adapter.SearchSingleRVShowAdapter;
import com.example.zjy.adapter.SearchSingleRVTabAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.bantang.SearchProductActivity;
import com.example.zjy.bean.SearchSingleBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.ParseJsonUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/28.
 * 搜索单品的Fragment
 */

public class SearchSingleFragment extends BaseFragment implements RetrofitUtil.DownListener {
//    @Bind(R.id.lv_tab)
//    ListView lvTab;
//    private LvAdapter lvAdapter;
    //展示标签的rv
    @Bind(R.id.rv_tab)
    RecyclerView rv_tab;
    private SearchSingleRVTabAdapter searchSingleRVTabAdapter;

    //展示该标签下的rv
    @Bind(R.id.rv_show)
    RecyclerView rv_show;
    private SearchSingleRVShowAdapter searchSingleRVShowAdapter;

    private String url;
    private List<SearchSingleBean.DataBean> data;

    //    @Bind(R.id.rv_show)
//    RecyclerView rv_show;
    @Override
    public int getContentId() {
        return R.layout.fragment_vp_search_single;
    }

    public static SearchSingleFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        SearchSingleFragment fragment = new SearchSingleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(View view) {
        //展示标签的适配器
        searchSingleRVTabAdapter = new SearchSingleRVTabAdapter(getContext());
        rv_tab.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        ));
        rv_tab.setAdapter(searchSingleRVTabAdapter);
//        lvAdapter = new LvAdapter(getContext());
//        lvTab.setAdapter(lvAdapter);
        //展示该标签下内容的适配器
        searchSingleRVShowAdapter = new SearchSingleRVShowAdapter(getContext());
        rv_show.setAdapter(searchSingleRVShowAdapter);
        rv_show.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    protected void getDatas(Bundle bundle) {
        url = bundle.getString("url");
        // TODO: 2017/4/10  备选方案
//        String cache = DiskLruCacheUtil.getJsonCache(url);
        loadDatas();
    }

    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url, 0x001);
    }

    @Override
    protected void bindListener() {
//        lvTab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("tag", "onItemClick: "+position);
//            }
//        });
//        lvTab.getChildAt(0).setSelected(true);
        //标签的RecyclerView的监听事件
        searchSingleRVTabAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
//                Log.i("tag", "onItemClick: "+position);
                //setPosition（）作用是为了 "rv_tab" item点击背景和textview颜色发生变化
                searchSingleRVTabAdapter.setPosition(position);
                searchSingleRVTabAdapter.notifyDataSetChanged();
                searchSingleRVShowAdapter.clearData();
                searchSingleRVShowAdapter.addDataAll(data.get(position).getSubclass());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });
        //展示标签的内容RecyclerView的item的点击事件
        searchSingleRVShowAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<SearchSingleBean.DataBean.SubclassBean>() {

            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, SearchSingleBean.DataBean.SubclassBean o, int position) {
                Log.i("tag", "onItemClick: "+position);
                //点击跳转到展示搜索产品界面
                Intent intent = new Intent(getActivity(), SearchProductActivity.class);
                intent.putExtra("id",o.getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, SearchSingleBean.DataBean.SubclassBean o, int position) {
                return false;
            }
        });
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseSearchSingle(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        SearchSingleBean searchSingleBean = (SearchSingleBean) object;
        data = searchSingleBean.getData();
//        Log.i("tag", "downSucc: " + data.size());
        searchSingleRVTabAdapter.addDataAll(data);
//        lvAdapter.setDatas(data);
        //给一个默认的数据
        searchSingleRVShowAdapter.addDataAll(data.get(0).getSubclass());
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
