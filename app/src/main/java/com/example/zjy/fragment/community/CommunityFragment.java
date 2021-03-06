package com.example.zjy.fragment.community;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.adapter.CommunityAdapter;
import com.example.zjy.fragment.community.bean.CommunityVo;
import com.example.zjy.fragment.community.vm.CommunityVM;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerItemDecoration;
import com.example.zjy.widget.PtrHeadView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zjy on 2017/1/4.
 */

public class CommunityFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.community_ptr)
    PtrClassicFrameLayout communityPtr;

    @Bind(R.id.community_rv)
    RecyclerView communityRv;
    private CommunityAdapter adapter;
    private LoadMoreWrapper loadMore;
    private HeaderAndFooterWrapper headWrapper;
    private View headView;

    private CommunityVM vm;
    private List<CommunityVo> mData = new ArrayList<>();

    @Bind(R.id.frame_anim)
    ImageView frameAnim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        final AnimationDrawable animationDrawable = (AnimationDrawable) frameAnim.getDrawable();
        animationDrawable.start();
        //初始化下拉刷新
        PtrHeadView ptrHeadView = new PtrHeadView(getContext());
        communityPtr.setHeaderView(ptrHeadView);
        communityPtr.addPtrUIHandler(ptrHeadView);
        communityPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                vm.refresh(new CommunityVM.CallBack() {
                    @Override
                    public void callBack(List<CommunityVo> data) {
                        frame.refreshComplete();
                        mData = data;
                        adapter = new CommunityAdapter(getContext(), R.layout.item_community_rv, mData);
                        initHeadAndFoot();
                        initLoadMore();
                    }
                });
            }
        });
        ptrHeadView.setLastUpdateTimeRelateObject(this);

        //初始化RecyclerView
        communityRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        communityRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST, R.drawable.divider_bg02));
        vm = new CommunityVM();
        vm.getDataFromServer(new CommunityVM.CallBack() {
            @Override
            public void callBack(List<CommunityVo> data) {
                mData = data;
                adapter = new CommunityAdapter(getContext(), R.layout.item_community_rv, mData);
                initHeadAndFoot();
                initLoadMore();
                animationDrawable.stop();
                frameAnim.setVisibility(View.GONE);
            }
        });
    }

    //初始化RecyclerView头部
    public void initHeadAndFoot() {
        headWrapper = new HeaderAndFooterWrapper(adapter);
        headView = LayoutInflater.from(getContext()).inflate(R.layout.layout_community_head, communityRv, false);
        //达人榜
        LinearLayout kolRank = (LinearLayout) headView.findViewById(R.id.kol_rank);
        kolRank.setOnClickListener(this);
        //文章榜
        LinearLayout topicRank = (LinearLayout) headView.findViewById(R.id.topic_rank);
        topicRank.setOnClickListener(this);
        //晒单榜
        LinearLayout postRank = (LinearLayout) headView.findViewById(R.id.post_rank);
        postRank.setOnClickListener(this);
        headWrapper.addHeaderView(headView);
    }

    //初始化RecyclerView上啦加载
    private void initLoadMore() {
        loadMore = new LoadMoreWrapper(headWrapper);
        loadMore.setLoadMoreView(R.layout.load_more);
        communityRv.setAdapter(loadMore);
        loadMore.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                vm.loadMore(new CommunityVM.CallBack() {
                    @Override
                    public void callBack(List<CommunityVo> data) {
                        mData.addAll(data);
                        loadMore.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.kol_rank:
                startActivity(KolRankActivity.newInstance(getActivity()));
                break;
            case R.id.topic_rank:
                startActivity(TopicRankActivity.newInstance(getActivity()));
                break;
            case R.id.post_rank:
                startActivity(PostRankActivity.newInstance(getActivity()));
                break;
        }
    }
}
