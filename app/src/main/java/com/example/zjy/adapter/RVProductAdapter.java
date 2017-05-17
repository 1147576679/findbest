package com.example.zjy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.bean.ItemDetailBean.DataBean.ProductListBean.PicsBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

import java.util.List;

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
        holder.setText(R.id.tv_title, product.getTitle());
        holder.setText(R.id.tv_desc, product.getDesc());
        holder.setText(R.id.tv_small_title, product.getTitle());
        holder.setText(R.id.tv_price, "¥" + product.getPrice());
        holder.setText(R.id.tv_likes, product.getLikes());
//        holder.setImageUrl(R.id.iv_big,product.getPic());
        holder.setImageUrl(R.id.iv_small, product.getThumbnail_pic());
        holder.setOnClickListener(R.id.tv_buy, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(product.getUrl()));
//                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                context.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.rl_buy, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(product.getUrl()));
                context.startActivity(intent);
            }
        });
        LinearLayout llDynamic = holder.getView(R.id.ll_dynamic_add);
        //移出所有视图
        llDynamic.removeAllViews();
        List<PicsBean> pics = product.getPics();
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        for (int i = 0; i < pics.size(); i++) {
            PicsBean picsBean = pics.get(i);
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ivParams.width = screenWidth;
            ivParams.topMargin = context.getResources().getDimensionPixelOffset(R.dimen.internal);
//            if(TextUtils.isEmpty(picsBean.getWidth()) && TextUtils.isEmpty(picsBean.getHeight())){
//                ivParams.height = context.getResources().getDimensionPixelOffset(R.dimen.product_pic_list);
//            }else {
//                int width = Integer.parseInt(picsBean.getWidth());
//                float height = Integer.parseInt(picsBean.getHeight());
//                ivParams.height = (int) (height / width * screenWidth);
//            }
            ivParams.height = context.getResources().getDimensionPixelOffset(R.dimen.product_pic_list);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(ivParams);
            Glide.with(context)
                    .load(picsBean.getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.place_holder)
                    .thumbnail(0.1f)
                    .into(imageView);
            llDynamic.addView(imageView);
        }
    }
}
