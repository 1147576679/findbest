package com.example.zjy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchTopicBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2016/12/31.
 */

public class SearchKeywordTopicRVAdapter extends CommonAdapter<SearchTopicBean> {
    private Context context;
    public SearchKeywordTopicRVAdapter(Context context) {
        super(context, R.layout.item_search_keyword_topic);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, final SearchTopicBean searchTopicBean, int position) {
        holder.setText(R.id.tv_title,searchTopicBean.getTitle())
                .setText(R.id.tv_views,searchTopicBean.getViews())
                .setText(R.id.tv_comments,searchTopicBean.getComments())
                .setText(R.id.tv_username,searchTopicBean.getUser().getNickname())
                .setText(R.id.tv_create_time,searchTopicBean.getCreate_time_str())
                .setImageUrl(R.id.iv_search_keyword,searchTopicBean.getPic());
        final TextView tv_views = holder.getView(R.id.tv_views);
        //浏览和评论数如果为null 则赋值0
        tv_views.post(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(searchTopicBean.getViews())){
                    tv_views.setText("0");
                }
            }
        });
        final TextView tv_comment = holder.getView(R.id.tv_comments);
        tv_views.post(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(searchTopicBean.getComments())){
                    tv_comment.setText("0");
                }
            }
        });
        //给用户圆形imageview加载图片
        CircleImageView circleImageView = holder.getView(R.id.user_icon);
        CirImageViewUtils.loadCirImage(searchTopicBean.getUser().getAvatar(),context,circleImageView);
    }
}
