package com.example.zjy.fragment.community.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.bean.CommunityBean;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.util.ShareSdkUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2017/3/14.
 */

public class CommunityAdapter extends CommonAdapter<CommunityBean> {
    private Context context;

    public CommunityAdapter(Context context, int layoutId, List<CommunityBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, final CommunityBean communityBean, int position) {
        holder.setOnClickListener(R.id.btn_focus, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                button.setBackgroundColor(context.getResources().getColor(R.color.radio_button_text_color));
                button.setText("已关注");
            }
        });

        holder.setOnClickListener(R.id.iv_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSdkUtils.showShare(
                        context,
                        communityBean.getPost() == null ? communityBean.getTopic().getTitle() : communityBean.getPost().getContent(),
                        communityBean.getPost() == null ? communityBean.getTopic().getShare_url() : communityBean.getPost().getShare_url(),
                        communityBean.getPost() == null ? communityBean.getTopic().getTitle() : communityBean.getPost().getContent()
                        );
            }
        });
        holder.setOnClickListener(R.id.tv_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSdkUtils.showShare(
                        context,
                        communityBean.getPost() == null ? communityBean.getTopic().getTitle() : communityBean.getPost().getContent(),
                        communityBean.getPost() == null ? communityBean.getTopic().getShare_url() : communityBean.getPost().getShare_url(),
                        communityBean.getPost() == null ? communityBean.getTopic().getTitle() : communityBean.getPost().getContent()
                );
            }
        });
        if (communityBean.getPost() != null) {
            holder.setText(R.id.tv_username, communityBean.getPost().getUser().getNickname())
                    .setText(R.id.tv_publish_time, communityBean.getPost().getDatestr())
                    .setText(R.id.tv_views, communityBean.getPost().getDynamic().getViews())
                    .setText(R.id.tv_shop_description, communityBean.getPost().getContent());
            ImageView ivShow = holder.getView(R.id.iv_show);
            Glide.with(context)
                    .load(communityBean.getPost().getPics().get(0).getUrl())
                    .centerCrop()
                    .thumbnail(0.1f)
                    .into(ivShow);
            CircleImageView ivUserIcon = holder.getView(R.id.iv_user_icon);
            CirImageViewUtils.loadCirImage(
                    communityBean.getPost().getUser().getAvatar(),
                    context,
                    ivUserIcon);
        }
        if (communityBean.getTopic() != null) {
            holder.setText(R.id.tv_username, communityBean.getTopic().getUser().getNickname())
                    .setText(R.id.tv_publish_time, communityBean.getTopic().getDatestr())
                    .setText(R.id.tv_views, communityBean.getTopic().getViews())
                    .setText(R.id.tv_shop_description, communityBean.getTopic().getTitle());
            ImageView ivShow = holder.getView(R.id.iv_show);
            Glide.with(context)
                    .load(communityBean.getTopic().getPics().get(0).getUrl())
                    .centerCrop()
                    .thumbnail(0.1f)
                    .into(ivShow);
            CircleImageView ivUserIcon = holder.getView(R.id.iv_user_icon);
            CirImageViewUtils.loadCirImage(
                    communityBean.getTopic().getUser().getAvatar(),
                    context,
                    ivUserIcon);
        }
    }
}
