package com.example.zjy.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zjy.adapter.RecyclerViewHomeAdapter;
import com.example.zjy.bantang.HomeItemDetailActivity;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.HomeContentBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.LoadMoreWrapper;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;
import com.example.zjy.widget.PtrHeadView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zjy on 2016/12/19.
 */

public class HomeViewPagerFragment extends BaseFragment implements RetrofitUtil.DownListener {
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.iv_frame_anim)
    ImageView mIv_frame_anim;
    private AnimationDrawable mAnimationDrawable;
    //接收HomeViewPagerFragment传过来的值，拼接字符串的字段(extend)
    private String mUrl;
    //recyclerView适配器
    private RecyclerViewHomeAdapter mRecyclerViewHomeAdapter;
    //下拉刷新
    @Bind(R.id.pull_to_refresh)
    PtrClassicFrameLayout ptrClassicFrameLayout;

    /**
     * 标志位 标记0 下拉刷新 标记1 上拉加载更多
     */
    private int limit;
    private LoadMoreWrapper loadMoreWrapper;

    //加载更多视图
    private View mFootView;
    private ProgressBar mProgressbar;
    private TextView mLoading;

    //ViewPager选中了当前的下标（根据这个下标得到不需要分页的位置）
    private int mPosition;
    //url分页用的
    private int page;
    //缓存已经加载的数据集合
    private List<HomeContentBean> mDatas = new ArrayList<>();

    //最终拼接好的url
    private String url;

    @Override
    public int getContentId() {
        return R.layout.fragment_viewpgaer;
    }

    /**
     * 接收主页ViewPager适配器传过来的url要拼接的字段
     * @param url
     * @return
     */
    public static HomeViewPagerFragment getInstance(String url, int position){
        HomeViewPagerFragment homeViewPagerFragment = new HomeViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        bundle.putInt("position",position);
        homeViewPagerFragment.setArguments(bundle);
        return homeViewPagerFragment;
    }

    @Override
    protected void init(View view) {
        //初始化适配器
        mRecyclerViewHomeAdapter = new RecyclerViewHomeAdapter(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL
                ,false));
        /**
         * 开启帧动画（为了更好的用户体验）
         */
        mAnimationDrawable = (AnimationDrawable) mIv_frame_anim.getDrawable();
        mAnimationDrawable.start();
        initLoadMore();
//        setupPtrView();
    }

    private void setupPtrView() {
        PtrHeadView ptrHeadView = new PtrHeadView(getContext());
        ptrClassicFrameLayout.setHeaderView(ptrHeadView);
        ptrClassicFrameLayout.addPtrUIHandler(ptrHeadView);
    }

    /**
     * 初始化上拉加载
     */
    private void initLoadMore() {
//        if(mPosition == 2) {
//            //标签为热门是没有分页
//            rv.setAdapter(mRecyclerViewHomeAdapter);
//        }else {
//            loadMoreWrapper = new LoadMoreWrapper(mRecyclerViewHomeAdapter);
//            mFootView = LayoutInflater.from(getContext()).inflate(R.layout.load_more, null);
//            mProgressbar = (ProgressBar) mFootView.findViewById(R.id.progress_bar);
//            mLoading = (TextView) mFootView.findViewById(R.id.loading);
//            //加载更多底部视图的点击事件
//            mFootView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    loadDatas();
//                }
//            });
//            loadMoreWrapper.setLoadMoreView(mFootView);
//            //分页
//            rv.setAdapter(loadMoreWrapper);
//        }
        loadMoreWrapper = new LoadMoreWrapper(mRecyclerViewHomeAdapter);
        mFootView = LayoutInflater.from(getContext()).inflate(R.layout.load_more, null);
        mProgressbar = (ProgressBar) mFootView.findViewById(R.id.progress_bar);
        mLoading = (TextView) mFootView.findViewById(R.id.loading);
        //加载更多底部视图的点击事件
        mFootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas();
            }
        });
        loadMoreWrapper.setLoadMoreView(mFootView);
        //分页
        rv.setAdapter(loadMoreWrapper);

    }

    @Override
    protected void getDatas(Bundle bundle) {
        super.getDatas(bundle);
        mUrl = bundle.getString("url");
        mPosition = bundle.getInt("position");
        loadDatas();
    }

    @Override
    protected void loadDatas() {
        //加载数据
//        if(mPosition != 2) {
//            String url = String.format(mUrl, page);
//            new RetrofitUtil(getContext()).setDownListener(this).downJson(url, 0x001);
////            Log.i("tag", "------>: "+url);
//        }else {
//            new RetrofitUtil(getActivity()).setDownListener(this).downJson(mUrl,0x002);
//        }
        switch (mPosition){
            case 0:
            case 1:
                url = String.format(Constants.URL_JINXUAN_YUANCHUANG,page);
                break;
            default:
                url = String.format(Constants.URL_OTHER,page,mUrl);
                break;
        }
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url, 0x001);
    }

    @Override
    protected void bindListener() {
        /**
         * 通过适配器来监听点击事件
         */
        mRecyclerViewHomeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<HomeContentBean>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, HomeContentBean homeContentBean, int position) {
                String id = homeContentBean.getId();
                Log.d("tag", "onItemClick: "+position+"<>"+id);
                Intent intent = new Intent(getActivity(), HomeItemDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("datas", (Serializable) mDatas);
                bundle.putString("id",id);
                bundle.putInt("position",position);
                bundle.putInt("vpindex",mPosition);
                bundle.putInt("page",page);
                bundle.putString("extend",mUrl);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, HomeContentBean homeContentBean, int position) {
                return false;
            }
        });
//        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
//            @Override
//            public void onRefreshBegin(final PtrFrameLayout frame) {
//                frame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        frame.refreshComplete();
//                    }
//                },2000);
//                loadDatas();
//            }
//        });
        /**
         * 下拉刷新监听
         */
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                limit = 0;
                page = 0;
                loadDatas();
            }
            //解决下拉刷新与RecyclerView下拉的冲突
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return super.checkCanDoRefresh(frame, rv, header);
//            }
        });
        //设置下拉刷新的时间
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
    }

    //解析数据
    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseContent(json);
    }
    //返回解析成功的值
    @Override
    public void downSucc(Object object, int requestCode) {
        /**
         * 如果是刷新，将之前数据清空
         */
        if(limit == 0) {
            mRecyclerViewHomeAdapter.clearData();
        }

        loadMoreWrapper.notifyDataSetChanged();
        List<HomeContentBean> datas = (List<HomeContentBean>) object;
        mDatas.addAll(datas);
        mRecyclerViewHomeAdapter.addDataAll(datas);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //加载更多是将标志的值修改为1
                limit = 1;
                //页数+1
                page++;
                loadDatas();
            }
        });

//            case 0x002:
//                List<HomeContentBean> datas2 = (List<HomeContentBean>) object;
//                Log.i("tag", "downSucc: "+datas2);
//                mRecyclerViewHomeAdapter.addDataAll(datas2);
//                break;

        //将帧动画结束
        //停止开始动画
        mAnimationDrawable.stop();
        //隐藏图片
        mIv_frame_anim.setVisibility(View.GONE);
        //刷新完成
        ptrClassicFrameLayout.refreshComplete();

//        mLoadMoreWrapper = new LoadMoreWrapper(mRecyclerViewHomeAdapter);
//        mLoadMoreWrapper.setLoadMoreView(R.layout.load_more);
//        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                loadDatas();
//            }
//        });
//        rv.setAdapter(mLoadMoreWrapper);
    }
    //网络异常时回调
    @Override
    public void fail(Throwable throwable) {
        Toast.makeText(getActivity(), "网络有问题，快去检查问题", Toast.LENGTH_SHORT).show();
        mProgressbar.setVisibility(View.GONE);
        mLoading.setText("点击重新加载");
    }


}
