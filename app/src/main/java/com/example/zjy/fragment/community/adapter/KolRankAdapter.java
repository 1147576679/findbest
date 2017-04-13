package com.example.zjy.fragment.community.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.CommunityPostDetailActivity;
import com.example.zjy.fragment.community.CommunityTopicDetailActivity;
import com.example.zjy.fragment.community.bean.KolRankVo;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.widget.CustomGridView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2017/4/5.
 */

public class KolRankAdapter extends CommonAdapter<KolRankVo> {
    private Context mContext;
    private static final int TOPIC = 1;
    private static final int POST = 2;
    public KolRankAdapter(Context context, int layoutId, List<KolRankVo> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, final KolRankVo kolRankVo, int position) {
        holder.setText(R.id.nickname, kolRankVo.nickname)
                .setText(R.id.rank, kolRankVo.rank)
                .setText(R.id.rankCount, kolRankVo.rankCount);
        CircleImageView circleImageView = holder.getView(R.id.user_icon);
        CirImageViewUtils.loadCirImage(kolRankVo.avatar, mContext, circleImageView);
        final CheckBox checkBoxFoucs = holder.getView(R.id.checkbox_focus);
        checkBoxFoucs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxFoucs.setChecked(true);
                    checkBoxFoucs.setText("已关注");
                } else {
                    checkBoxFoucs.setChecked(false);
                    checkBoxFoucs.setText("+关注");
                }

            }
        });

        CustomGridView customGridView = holder.getView(R.id.gridView);
        KolRankGridViewAdapter kolRankGridViewAdapter = new KolRankGridViewAdapter(mContext,R.layout.item_community_kol_rank_gridview);
        kolRankGridViewAdapter.setDatas(kolRankVo.ivUrl);
        customGridView.setAdapter(kolRankGridViewAdapter);
        customGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer integer = kolRankVo.flag.get(position);
                if(integer == TOPIC ){
                    mContext.startActivity(CommunityTopicDetailActivity.newInstance(mContext,kolRankVo.id.get(position)+""));
                }else if(integer == POST){
                    mContext.startActivity(CommunityPostDetailActivity.newInstance(mContext,kolRankVo.id.get(position)+""));
                }
            }
        });
    }
}
