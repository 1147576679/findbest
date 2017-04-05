package com.example.zjy.fragment.community;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.adapter.TopicRankAdapter;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.community.bean.TopicRankVo;
import com.example.zjy.fragment.community.vm.TopicRankVM;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class TopicRankActivity extends BaseActivity {
    @Bind(R.id.rv_topic_rank)
    RecyclerView mRvTopicRank;
    private TopicRankAdapter mTopicRankAdapter;
    private TopicRankVM mTopicRankVM;
    @Override
    public int getContentId() {
        return R.layout.activity_topic_rank;
    }

    public static Intent newInstance(Context context){
        return new Intent(context,TopicRankActivity.class);
    }

    @OnClick(R.id.iv_back_arrow)
    public void finishClick(View view){
        finish();
    }

    @Override
    protected void init() {
        mRvTopicRank.setHasFixedSize(true);
        mRvTopicRank.setLayoutManager(new LinearLayoutManager(this));
        mRvTopicRank.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.divider_bg02));
        mTopicRankVM = new TopicRankVM();
        mTopicRankVM.getData(new CallBack<List<TopicRankVo>>() {
            @Override
            public void callBack(List<TopicRankVo> topicRankVos) {
                mTopicRankAdapter = new TopicRankAdapter(TopicRankActivity.this,R.layout.item_community_topic_rank,topicRankVos);
                mRvTopicRank.setAdapter(mTopicRankAdapter);
            }
        });
    }
}
