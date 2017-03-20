package com.example.zjy.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.HomeContentBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/19.
 * 主页RecyclerView的适配器
 */

public class RecyclerViewHomeAdapter extends CommonAdapter<HomeContentBean> {
    public RecyclerViewHomeAdapter(Context context) {
        super(context, R.layout.item_rv);
    }

    @Override
    protected void convert(ViewHolder holder, HomeContentBean homeContentBean, int position) {
        final TextView tv_views = holder.getView(R.id.tv_views);
        final TextView tv_likes = holder.getView(R.id.tv_likes);
        final TextView tv_comments = holder.getView(R.id.tv_comments);
        final ImageView iv_views = holder.getView(R.id.iv_views);
        final ImageView iv_likes = holder.getView(R.id.iv_likes);
        final ImageView iv_comments = holder.getView(R.id.iv_comments);
        if("".equals(homeContentBean.getViews())){
            tv_views.setVisibility(View.GONE);
            iv_views.setVisibility(View.GONE);
        }
        if ("".equals(homeContentBean.getLikes())){
            tv_likes.setVisibility(View.GONE);
            iv_likes.setVisibility(View.GONE);
        }
        if ("".equals(homeContentBean.getComments())){
            tv_comments.setVisibility(View.GONE);
            iv_comments.setVisibility(View.GONE);
        }
        holder.setText(R.id.tv_title,homeContentBean.getTitle())
                .setText(R.id.tv_username,homeContentBean.getUser().getNickname())
                .setText(R.id.tv_views,homeContentBean.getViews())
                .setText(R.id.tv_likes,homeContentBean.getLikes())
                .setText(R.id.tv_comments,homeContentBean.getComments())
                .setImageUrl(R.id.iv_image,homeContentBean.getPic());

        /**
         * 设置喜欢，评论，看过文本，如果次数为0时，取消其左边图片的可见性
         */
//        tv_views.post(new Runnable() {
//            @Override
//            public void run() {
//                if("".equals(tv_views.getText())){
//                    iv_views.setVisibility(View.GONE);
//                }
//            }
//        });
//        tv_likes.post(new Runnable() {
//            @Override
//            public void run() {
//                if("".equals(tv_likes.getText())){
//                    iv_likes.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        tv_comments.post(new Runnable() {
//            @Override
//            public void run() {
//                if("".equals(tv_comments.getText())){
////                    Log.i("tag", "run: "+tv_comments.getText());
//                    iv_comments.setVisibility(View.GONE);
//                }
//            }
//        });
    }
}
