package com.example.zjy.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ItemViewDelegate;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.HeaderAndFooterWrapper;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.widget.FootViewTypeProduct;
import com.example.zjy.widget.HeadViewHomeItemDetail;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.zjy.bantang.R.id.user_icon;

/**
 * Created by zjy on 2016/12/26.
 * 四种布局的RecyclerView的适配器
 */

public class RVItemDetailAdapter extends MultiItemTypeAdapter{
    private Context mContext;
    //给Post_list布局中Recyclerview的item的Viewpager的fragmentManager赋值
    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public RVItemDetailAdapter(Context context) {
        super(context);
        mContext = context;
        addItemViewDelegate(new WebViewDelegate());
        addItemViewDelegate(new ProductDelegate());
        addItemViewDelegate(new PostDelegate());
        addItemViewDelegate(new ContentDelegate());
    }
    public class WebViewDelegate implements ItemViewDelegate<ItemDetailBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_detail_rv_webview;
        }

        @Override
        public boolean isForViewType(ItemDetailBean item, int position) {
            return item.getData().getArticle_content() != null;
        }

        @Override
        public void convert(ViewHolder holder, ItemDetailBean itemDetailBean, int position) {
            WebView webView = holder.getView(R.id.web_view);
            webView.loadData(itemDetailBean.getData().getArticle_content(),"text/html; charset=UTF-8",null);
        }
    }

    /**
     * 接口类型为product_list
     */
    public class ProductDelegate implements ItemViewDelegate<ItemDetailBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_detail_rv_product;
        }

        @Override
        public boolean isForViewType(ItemDetailBean item, int position) {
            return item.getData().getProduct_list() != null;
        }

        @Override
        public void convert(ViewHolder holder, ItemDetailBean itemDetailBean, int position) {
//            CustomListView customListView = holder.getView(rv_product);
//            LVProductAdapter lvProductAdapter  = new LVProductAdapter(mContext);
//            customListView.setAdapter(lvProductAdapter);
//            HeadViewHomeItemDetail headViewHomeItemDetail = new HeadViewHomeItemDetail(mContext);
//            headViewHomeItemDetail.setData(itemDetailBean.getData());
//            customListView.addHeaderView(headViewHomeItemDetail);
//            lvProductAdapter.addDatas(itemDetailBean.getData().getProduct_list());

            /**
             * product列表展示的RecyclerView
             */
            RecyclerView rv_product = holder.getView(R.id.rv_product);
            RVProductAdapter rvProductAdapter = new RVProductAdapter(mContext);
            rv_product.setLayoutManager(new LinearLayoutManager(
                    mContext,
                    LinearLayoutManager.VERTICAL,
                    false
            ));
            /**
             * 添加头部和底部
             */
            HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(rvProductAdapter);
            HeadViewHomeItemDetail headViewHomeItemDetail = new HeadViewHomeItemDetail(mContext);
            headViewHomeItemDetail.setData(itemDetailBean.getData());
            headerAndFooterWrapper.addHeaderView(headViewHomeItemDetail);
            //底部
//            View footView = LayoutInflater.from(mContext).inflate(R.layout.layout_product_foot,null);
//            TextView tvcollection = (TextView) footView.findViewById(R.id.tv_collection);
//            TextView tvdate = (TextView) footView.findViewById(R.id.tv_date);
//            tvcollection.setText(itemDetailBean.getData().getPraises());
//            tvdate.setText(itemDetailBean.getData().getDatestr());
            FootViewTypeProduct footView = new FootViewTypeProduct(mContext);
            footView.setDataBean(itemDetailBean.getData());
            headerAndFooterWrapper.addFootView(footView);
            rv_product.setAdapter(headerAndFooterWrapper);
            rvProductAdapter.addDataAll(itemDetailBean.getData().getProduct_list());


//            holder.setText(R.id.tv_title,itemDetailBean.getData().getTitle())
//            .setText(R.id.tv_views,itemDetailBean.getData().getViews())
//            .setText(R.id.tv_desc,itemDetailBean.getData().getDesc());
//            ImageView user_icon = holder.getView(R.id.user_icon);
//            Glide.with(mContext)
//                    .load(itemDetailBean.getData().getUser().getAvatar())
//                    .centerCrop()
//                    .dontAnimate()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(user_icon);

        }
    }

    public class ContentDelegate implements ItemViewDelegate<ItemDetailBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_detail_rv_content;
        }

        @Override
        public boolean isForViewType(ItemDetailBean item, int position) {
            return item.getData().getContent_list() != null;
        }

        @Override
        public void convert(ViewHolder holder, ItemDetailBean itemDetailBean, int position) {
//            holder.setText(R.id.tv_title,itemDetailBean.getData().getTitle())
//                    .setText(R.id.tv_nickname,itemDetailBean.getData().getUser().getNickname())
//                    .setText(R.id.tv_views,itemDetailBean.getData().getViews());
//            CircleImageView user_icon = holder.getView(R.id.user_icon);
//            Glide.with(mContext)
//                    .load(itemDetailBean.getData().getUser().getAvatar())
//                    .centerCrop()
//                    .dontAnimate()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(user_icon);
            LinearLayout ll_dynamic_add = holder.getView(R.id.ll_dynamic_add);
            ll_dynamic_add.removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,16,0,16);
            /**
             * 添加头部视图（固定格式）
             */
            View head_title = LayoutInflater.from(mContext).inflate(R.layout.layout_content_head_title,null);
            TextView tvviews = (TextView) head_title.findViewById(R.id.tv_views);
            TextView tvnickname = (TextView) head_title.findViewById(R.id.tv_nickname);
            CircleImageView usericon = (CircleImageView) head_title.findViewById(user_icon);
            TextView tvtitle = (TextView) head_title.findViewById(R.id.tv_title);
            //赋值
            tvnickname.setText(itemDetailBean.getData().getUser().getNickname());
            tvviews.setText(itemDetailBean.getData().getViews());
            tvtitle.setText(itemDetailBean.getData().getTitle());
            Glide.with(mContext)
                    .load(itemDetailBean.getData().getUser().getAvatar())
                    .centerCrop()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(usericon);
            ll_dynamic_add.addView(head_title);
            List<ItemDetailBean.DataBean.ContentListBean> content_list = itemDetailBean.getData().getContent_list();
            /**
             * 动态添加TextView和ImageView
             */
            for (int i = 0; i < content_list.size(); i++) {
                ItemDetailBean.DataBean.ContentListBean contentListBean = content_list.get(i);
                int type = contentListBean.getType();
                switch (type){
                    case 1:
                        final ImageView imageView = new ImageView(mContext);
//                        imageView.setMaxWidth(layoutParams.width);
//                        imageView.setAdjustViewBounds(true);
                        Glide.with(mContext)
                                .load(contentListBean.getImage_url())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                        ll_dynamic_add.addView(imageView,layoutParams);
                        break;
                    case 2:
                        TextView textView = new TextView(mContext);
                        textView.setText(contentListBean.getText_content());
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.content_list_text_size));
                        ll_dynamic_add.addView(textView,layoutParams);
                        break;
                    case 5:
                        /**
                         * 添加一个item一样的视图
                         */
                        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_content_list,null);
                        TextView tv_small_title = (TextView) view.findViewById(R.id.tv_small_title);
                        TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
                        ImageView iv_small = (ImageView) view.findViewById(R.id.iv_small);
                        tv_small_title.setText(contentListBean.getItem_name());
                        tv_price.setText("¥"+"  "+contentListBean.getPrice());
                        Glide.with(mContext)
                                .load(contentListBean.getThumbnail_pic())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(iv_small);
                        ll_dynamic_add.addView(view);
                        break;
                }
            }
            View footView = LayoutInflater.from(mContext).inflate(R.layout.layout_content_foot_view,null);
            TextView tvdate = (TextView) footView.findViewById(R.id.tv_date);
            TextView tv_nickname = (TextView) footView.findViewById(R.id.tv_nickname);
            TextView tvcollection = (TextView) footView.findViewById(R.id.tv_collection);
            CircleImageView user_icon = (CircleImageView) footView.findViewById(R.id.user_icon);
            tvdate.setText("创建于"+"  "+itemDetailBean.getData().getCreate_time_str());
            tv_nickname.setText(itemDetailBean.getData().getUser().getNickname());
            tvcollection.setText(itemDetailBean.getData().getLikes());
            CirImageViewUtils.loadCirImage(itemDetailBean.getData().getUser().getAvatar(),mContext,user_icon);
            ll_dynamic_add.addView(footView);
        }
        public float getSize(int id) {
            //返回的是px  18
            return mContext.getResources().getDimensionPixelSize(id);
        }
    }

    /**
     * 接口类型为Post_list
     */
    public class PostDelegate implements ItemViewDelegate<ItemDetailBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_detail_rv_post;
        }

        @Override
        public boolean isForViewType(ItemDetailBean item, int position) {
            return item.getData().getPost_list() != null;
        }

        @Override
        public void convert(ViewHolder holder, ItemDetailBean itemDetailBean, int position) {

//            Log.i("tag", "convert: "+position);
            /**
             * post_list 的列表集合
             */
            RecyclerView rv_post_list = holder.getView(R.id.rv_post_list);
            RVPostListAdapter rvPostListAdapter = new RVPostListAdapter(mContext);
            rvPostListAdapter.setFragmentManager(fragmentManager);
            /**
             * 添加头部
             */
            HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(rvPostListAdapter);
            HeadViewHomeItemDetail headview = new HeadViewHomeItemDetail(mContext);
            headview.setData(itemDetailBean.getData());
            headerAndFooterWrapper.addHeaderView(headview);
            rv_post_list.setLayoutManager(new LinearLayoutManager(
                    mContext,
                    LinearLayoutManager.VERTICAL,
                    false
            ));
            rv_post_list.setAdapter(headerAndFooterWrapper);
            rvPostListAdapter.addDataAll(itemDetailBean.getData().getPost_list());
        }


    }

}
