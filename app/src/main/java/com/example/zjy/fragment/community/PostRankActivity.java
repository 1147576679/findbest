package com.example.zjy.fragment.community;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.adapter.PostRankAdapter;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.community.bean.PostRankVo;
import com.example.zjy.fragment.community.vm.PostRankVM;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class PostRankActivity extends BaseActivity {

    @Bind(R.id.rv_post_rank)
    RecyclerView mRvPostRank;
    
    private PostRankAdapter mPostRankAdapter;
    private PostRankVM mPostRankVM;
    
    @Override
    public int getContentId() {
        return R.layout.activity_post_rank;
    }

    public static Intent newInstance(Context context){
        return new Intent(context,PostRankActivity.class);
    }

    @OnClick(R.id.iv_back_arrow)
    public void finishClick(View view){
        finish();
    }

    @Override
    protected void init() {
        mRvPostRank.setHasFixedSize(true);
        mRvPostRank.setLayoutManager(new LinearLayoutManager(this));
        mRvPostRank.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.divider_bg02));
        mPostRankVM = new PostRankVM();
        mPostRankVM.getData(new CallBack<List<PostRankVo>>() {
            @Override
            public void callBack(List<PostRankVo> postRankVos) {
                mPostRankAdapter = new PostRankAdapter(PostRankActivity.this,R.layout.item_community_post_rank,postRankVos);
                mRvPostRank.setAdapter(mPostRankAdapter);
            }
        });
    }
}
