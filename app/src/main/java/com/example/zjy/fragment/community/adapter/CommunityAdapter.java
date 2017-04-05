package com.example.zjy.fragment.community.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.CommunityPostDetailActivity;
import com.example.zjy.fragment.community.CommunityTopicDetailActivity;
import com.example.zjy.fragment.community.bean.CommunityVo;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.util.ToastUtils;
import com.example.zjy.util.ShareSdkUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2017/3/14.
 */

public class CommunityAdapter extends CommonAdapter<CommunityVo> {
    private Context context;
    private static final int TOPIC = 1;
    private static final int POST = 2;
    public CommunityAdapter(Context context, int layoutId, List<CommunityVo> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, final CommunityVo communityVo, int position) {
        final CheckBox checkBoxFoucs = holder.getView(R.id.checkbox_focus);
        checkBoxFoucs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxFoucs.setChecked(true);
                    checkBoxFoucs.setText("已关注");
                    ToastUtils.showToast(context,"关注成功");
                }else {
                    checkBoxFoucs.setChecked(false);
                    checkBoxFoucs.setText("+关注");
                }
            }
        });

        holder.setOnClickListener(R.id.iv_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSdkUtils.showShare(context, communityVo.content, communityVo.shareUrl, communityVo.content);
            }
        });
        holder.setOnClickListener(R.id.tv_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSdkUtils.showShare(context, communityVo.content, communityVo.shareUrl, communityVo.content);
            }
        });

        holder.setText(R.id.tv_username, communityVo.username)
                .setText(R.id.tv_publish_time, communityVo.publish_time)
                .setText(R.id.tv_views, communityVo.views)
                .setText(R.id.tv_shop_description, communityVo.content);
        ImageView ivShow = holder.getView(R.id.iv_show);
        ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(communityVo.flag == TOPIC){
                    context.startActivity(CommunityTopicDetailActivity.newInstance(context,communityVo.id));
                }else if (communityVo.flag == POST){
                    context.startActivity(CommunityPostDetailActivity.newInstance(context,communityVo.id));
                }
            }
        });
        Glide.with(context)
                .load(communityVo.ivUrl)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .thumbnail(0.1f)
                .into(ivShow);
        CircleImageView ivUserIcon = holder.getView(R.id.iv_user_icon);
        CirImageViewUtils.loadCirImage(
                communityVo.avatar,
                context,
                ivUserIcon);
    }
}
