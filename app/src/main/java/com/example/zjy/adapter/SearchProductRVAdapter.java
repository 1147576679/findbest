package com.example.zjy.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchProductBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/30.
 */

public class SearchProductRVAdapter extends CommonAdapter<SearchProductBean.DataBean> {
    public SearchProductRVAdapter(Context context) {
        super(context, R.layout.item_search_rv_product_show);
    }

    @Override
    protected void convert(ViewHolder holder, SearchProductBean.DataBean dataBean, int position) {
        holder.setText(R.id.tv_product_name,dataBean.getTitle())
                .setText(R.id.tv_product_content,dataBean.getDesc())
                .setImageUrl(R.id.iv_search_product,dataBean.getPic())
                .setText(R.id.tv_product_price,dataBean.getPrice())
        .setText(R.id.tv_views,dataBean.getLikes());

    }
}
