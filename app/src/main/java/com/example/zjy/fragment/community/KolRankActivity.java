package com.example.zjy.fragment.community;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.adapter.KolRankAdapter;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.community.bean.KolRankVo;
import com.example.zjy.fragment.community.vm.KolRankVM;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class KolRankActivity extends BaseActivity {
    @Bind(R.id.rv_kol_rank)
    RecyclerView mRvKolRank;
    private KolRankAdapter mKolRankAdapter;
    private KolRankVM mKolRankVM;
    @Override
    public int getContentId() {
        return R.layout.activity_kol_rank;
    }

    public static Intent newInstance(Context context){
        return new Intent(context,KolRankActivity.class);
    }

    @OnClick(R.id.iv_back_arrow)
    public void finishClick(View view){
        finish();
    }

    @Override
    protected void init() {
        mRvKolRank.setHasFixedSize(true);
        mRvKolRank.setLayoutManager(new LinearLayoutManager(this));
        mRvKolRank.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.divider_bg02));
        mKolRankVM = new KolRankVM();
        mKolRankVM.getData(new CallBack<List<KolRankVo>>() {
            @Override
            public void callBack(List<KolRankVo> kolRankVos) {
                mKolRankAdapter = new KolRankAdapter(KolRankActivity.this,R.layout.item_community_kol_rank,kolRankVos);
                mRvKolRank.setAdapter(mKolRankAdapter);
            }
        });
    }
}
