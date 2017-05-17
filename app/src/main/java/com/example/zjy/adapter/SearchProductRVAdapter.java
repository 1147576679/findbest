package com.example.zjy.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchProductBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/30.
 */

public class SearchProductRVAdapter extends CommonAdapter<SearchProductBean.DataBean> {
    private Context mContext;
    public SearchProductRVAdapter(Context context) {
        super(context, R.layout.item_search_rv_product_show);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, SearchProductBean.DataBean dataBean, int position) {
        holder.setText(R.id.tv_product_name,dataBean.getTitle())
                .setText(R.id.tv_product_content,dataBean.getDesc())
                .setText(R.id.tv_product_price,dataBean.getPrice())
        .setText(R.id.tv_views,dataBean.getLikes());
        ImageView imageView = holder.getView(R.id.iv_search_product);
        Glide.with(mContext)
                .load(dataBean.getPic())
                .centerCrop()
                .into(imageView);

    }
}
