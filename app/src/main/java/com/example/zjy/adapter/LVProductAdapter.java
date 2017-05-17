package com.example.zjy.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.lvhelper.UniversalAdapter;

/**
 * Created by zjy on 2016/12/27.
 */

public class LVProductAdapter extends UniversalAdapter<ItemDetailBean.DataBean.ProductListBean> {
    public LVProductAdapter(Context context) {
        super(context, R.layout.item_product_rv);
    }

    @Override
    public void bindView(ViewHolder holder, ItemDetailBean.DataBean.ProductListBean product) {
        holder.setText(R.id.tv_title,product.getTitle());
        holder.setText(R.id.tv_desc,product.getDesc());
        holder.setText(R.id.tv_small_title,product.getTitle());
        holder.setText(R.id.tv_price,"¥"+product.getPrice());
        holder.setText(R.id.tv_likes,product.getLikes());
//        holder.setImageUrl(R.id.iv_big,product.getPic(),R.drawable.bg_personal_wish_item_drawable_day);
        holder.setImageUrl(R.id.iv_small,product.getThumbnail_pic(),0);
    }

}
