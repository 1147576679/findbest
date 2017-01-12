package com.example.zjy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/26.
 */

public class RVProductAdapter extends CommonAdapter<ItemDetailBean.DataBean.ProductListBean> {
    private Context context;
    public RVProductAdapter(Context context) {
        super(context, R.layout.item_product_rv);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, final ItemDetailBean.DataBean.ProductListBean product, int position) {
        holder.setText(R.id.tv_title,product.getTitle());
        holder.setText(R.id.tv_desc,product.getDesc());
        holder.setText(R.id.tv_small_title,product.getTitle());
        holder.setText(R.id.tv_price,"¥"+product.getPrice());
        holder.setText(R.id.tv_likes,product.getLikes());
        holder.setImageUrl(R.id.iv_big,product.getPic());
        holder.setImageUrl(R.id.iv_small,product.getThumbnail_pic());
        holder.setOnClickListener(R.id.tv_buy, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(product.getUrl()));
//                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                context.startActivity(intent);
            }
        });
    }
}
