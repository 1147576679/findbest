package com.example.zjy.fragment.community.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.CommunityTopicDetailActivity;
import com.example.zjy.fragment.community.bean.TopicRankVo;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.util.ToastUtils;
import com.example.zjy.util.ShareSdkUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2017/4/5.
 */

public class TopicRankAdapter extends CommonAdapter<TopicRankVo> {
    private Context context;

    public TopicRankAdapter(Context context, int layoutId, List<TopicRankVo> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, final TopicRankVo topicRankVo, int position) {
        final CheckBox checkBoxFoucs = holder.getView(R.id.checkbox_focus);
        checkBoxFoucs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxFoucs.setChecked(true);
                    checkBoxFoucs.setText("已关注");
                    ToastUtils.showToast(context, "关注成功");
                } else {
                    checkBoxFoucs.setChecked(false);
                    checkBoxFoucs.setText("+关注");
                }
            }
        });

        holder.setOnClickListener(R.id.iv_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSdkUtils.showShare(context, topicRankVo.content, topicRankVo.shareUrl, topicRankVo.content);
            }
        });
        holder.setOnClickListener(R.id.tv_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSdkUtils.showShare(context, topicRankVo.content, topicRankVo.shareUrl, topicRankVo.content);
            }
        });

        holder.setText(R.id.tv_username, topicRankVo.username)
                .setText(R.id.tv_publish_time, topicRankVo.publish_time)
                .setText(R.id.tv_views, topicRankVo.views)
                .setText(R.id.tv_shop_description, topicRankVo.content);
        holder.setOnClickListener(R.id.tv_shop_description, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(CommunityTopicDetailActivity.newInstance(context, topicRankVo.id));
            }
        });
        ImageView ivShow = holder.getView(R.id.iv_show);
        ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(CommunityTopicDetailActivity.newInstance(context, topicRankVo.id));
            }
        });
        Glide.with(context)
                .load(topicRankVo.ivUrl)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .thumbnail(0.1f)
                .into(ivShow);
        CircleImageView ivUserIcon = holder.getView(R.id.iv_user_icon);
        CirImageViewUtils.loadCirImage(
                topicRankVo.avatar,
                context,
                ivUserIcon);
    }
}
