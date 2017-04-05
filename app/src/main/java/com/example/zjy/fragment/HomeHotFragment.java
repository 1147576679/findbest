package com.example.zjy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.adapter.RecyclerViewHomeAdapter;
import com.example.zjy.bantang.HomeItemDetailActivity;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.HomeContentBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;
import com.example.zjy.widget.PtrHeadView;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zjy on 2016/12/24.
 * Hot得fragment，热门没有分页，其他标签的Fragment有分页
 */

public class HomeHotFragment extends BaseFragment implements RetrofitUtil.DownListener {
    @Bind(R.id.rv)
    RecyclerView rv;

    private RecyclerViewHomeAdapter mRecyclerViewHomeAdapter;
    //下拉刷新
    @Bind(R.id.pull_to_refresh)
    PtrClassicFrameLayout ptrClassicFrameLayout;

    private String mUrl;
    private List<HomeContentBean> datas;
    private int mPosition;
    private String url;

    @Override
    public int getContentId() {
        return R.layout.fragment_viewpgaer;
    }
    public static HomeHotFragment getInstance(String url, int position){
        HomeHotFragment homeHotFragment = new HomeHotFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        bundle.putInt("position",position);
        homeHotFragment.setArguments(bundle);
        return homeHotFragment;
    }

    @Override
    protected void init(View view) {
        mRecyclerViewHomeAdapter = new RecyclerViewHomeAdapter(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL
                ,false));
        rv.setAdapter(mRecyclerViewHomeAdapter);
        rv.setHasFixedSize(true);
        PtrHeadView ptrHeadView = new PtrHeadView(getContext());
        ptrClassicFrameLayout.setHeaderView(ptrHeadView);
        ptrClassicFrameLayout.addPtrUIHandler(ptrHeadView);
        ptrHeadView.setLastUpdateTimeRelateObject(this);
    }

    @Override
    protected void getDatas(Bundle bundle) {
        mUrl = bundle.getString("url");
        mPosition = bundle.getInt("position");
        loadDatas();
    }

    @Override
    protected void loadDatas() {
        url = String.format(Constants.URL_HOT,mUrl);
        Log.d("tag", "HomeHotFragment: "+ url);
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x001);
    }

    @Override
    protected void bindListener() {
        mRecyclerViewHomeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<HomeContentBean>(){

            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, HomeContentBean o, int position) {
                String id = o.getId();
                Log.d("tag", "onItemClick: "+position+"<>"+id);
                Intent intent = new Intent(getActivity(), HomeItemDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("datas", (Serializable) datas);
                bundle.putString("id",id);
                bundle.putInt("position",position);
                bundle.putInt("vpindex",mPosition);
                bundle.putString("extend",mUrl);
                intent.putExtras(bundle);
                startActivity(intent);
//                Log.i("tag", "onItemClick: "+datas.size());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, HomeContentBean o, int position) {
                return false;
            }
        });
        /**
         * 下拉刷新监听
         */
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadDatas();

            }
        });
        //设置下拉刷新的时间
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
    }

    @Override
    public Object paresJson(String json, int requestCode) {
//        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseContent(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        List<HomeContentBean> datas = (List<HomeContentBean>) object;
        this.datas = datas;
        /**
         * 刷新时将前面的数据清除
         */
        mRecyclerViewHomeAdapter.clearData();
        mRecyclerViewHomeAdapter.addDataAll(datas);
        ptrClassicFrameLayout.refreshComplete();
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
