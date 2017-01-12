package com.example.zjy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/26.
 * post_list RecyclerView Item 的适配器
 */

public class RVPostListAdapter extends CommonAdapter<ItemDetailBean.DataBean.PostListBean> {
    private int viewPager_id = 1;
    private Context context;
    //item 中viewpager的fragmentManager
    private FragmentManager fragmentManager;
    public RVPostListAdapter(Context context) {
        super(context, R.layout.item_rv_post_list);
        this.context = context;
    }
    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }
    //TODO Viewpager 内嵌Viewpager 嵌套的Viewpager不显示
    @Override
    protected void convert(ViewHolder holder, final ItemDetailBean.DataBean.PostListBean postListBean, int position) {
//        Log.i("tag", "viewpager: "+position);
//        Log.i("tag", "convert: "+postListBean);
        //item布局中的viewpager.0
//        Log.i("tag", "convert: "+postListBean);
        Log.w("tag", "convert: "+position);
        FrameLayout frameLayout = holder.getView(R.id.frame_layout_vp);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewPager viewPager = new ViewPager(context);
        viewPager.setId(viewPager_id++);
        //viewpager的适配器
        VPPostListItemAdapter vpPostListItemAdapter = new VPPostListItemAdapter(fragmentManager,postListBean.getPics());
        viewPager.setAdapter(vpPostListItemAdapter);
        frameLayout.addView(viewPager,params);

        holder.setText(R.id.tv_nickname, postListBean.getUser().getNickname())  //加载用户昵称
                        .setText(R.id.tv_small_title, postListBean.getBrand_product().get(0).getBrand().getName() + "  " +
                                postListBean.getBrand_product().get(0).getBrand().getAlias()
                                + postListBean.getBrand_product().get(0).getTitle()) //加载商品名称
                        .setText(R.id.tv_price, "¥"+" "+postListBean.getBrand_product().get(0).getPrice())
                        .setText(R.id.tv_content,postListBean.getContent())    //加载用户描述商品的内容
                        .setImageUrl(R.id.iv_small,postListBean.getBrand_product().get(0).getThumbnail_pic()); //加载小图
        ImageView user_icon = holder.getView(R.id.user_icon);
        Glide.with(mContext)
                .load(postListBean.getUser().getAvatar())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(user_icon);
        //点击购买跳转到本地浏览器
        holder.setOnClickListener(R.id.tv_buy, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(postListBean.getBrand_product().get(0).getUrl()));
//                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                context.startActivity(intent);
            }
        });
    }
}
