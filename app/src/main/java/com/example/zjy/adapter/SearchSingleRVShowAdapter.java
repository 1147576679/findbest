package com.example.zjy.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchSingleBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/29.
 */

public class SearchSingleRVShowAdapter extends CommonAdapter<SearchSingleBean.DataBean.SubclassBean> {
    public SearchSingleRVShowAdapter(Context context) {
        super(context, R.layout.item_rv_search_show);
    }

    @Override
    protected void convert(ViewHolder holder, SearchSingleBean.DataBean.SubclassBean subclassBean, int position) {
        holder.setText(R.id.tv_product_name,subclassBean.getName())
                .setImageUrl(R.id.iv_product,subclassBean.getIcon());
    }
}
