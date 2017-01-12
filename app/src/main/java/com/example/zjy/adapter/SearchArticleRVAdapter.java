package com.example.zjy.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchArticleBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/29.
 */

public class SearchArticleRVAdapter extends CommonAdapter<SearchArticleBean> {

    public SearchArticleRVAdapter(Context context) {
        super(context, R.layout.item_rv_search_article);
    }

    @Override
    protected void convert(ViewHolder holder, SearchArticleBean searchArticleBean, int position) {
        holder.setText(R.id.tv_chinese_name,searchArticleBean.getName())
                .setText(R.id.tv_en_name,searchArticleBean.getEn_name())
                .setImageUrl(R.id.iv_article_show,searchArticleBean.getIcon());
    }
}
