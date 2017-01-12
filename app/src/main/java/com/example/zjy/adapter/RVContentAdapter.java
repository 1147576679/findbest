package com.example.zjy.adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.util.FontHelper;


/**
 * Created by zjy on 2017/1/2.
 */

public class RVContentAdapter extends CommonAdapter<ItemDetailBean.DataBean.ContentListBean> {
    private Context mContext;
    public RVContentAdapter(Context context) {
        super(context, R.layout.item_detail_rv_content);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, ItemDetailBean.DataBean.ContentListBean contentListBean, int position) {
        LinearLayout ll_dynamic_add = holder.getView(R.id.ll_dynamic_add);
        ll_dynamic_add.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,16,0,0);
        /**
         * 添加头部视图（固定格式）
         */
//        View head_title = LayoutInflater.from(mContext).inflate(R.layout.layout_content_head_title,null);
//        TextView tvviews = (TextView) head_title.findViewById(R.id.tv_views);
//        TextView tvnickname = (TextView) head_title.findViewById(R.id.tv_nickname);
//        CircleImageView usericon = (CircleImageView) head_title.findViewById(R.id.user_icon);
//        TextView tvtitle = (TextView) head_title.findViewById(R.id.tv_title);
//        //赋值
//        tvnickname.setText(itemDetailBean.getData().getUser().getNickname());
//        tvviews.setText(itemDetailBean.getData().getViews());
//        tvtitle.setText(itemDetailBean.getData().getTitle());
//        Glide.with(mContext)
//                .load(itemDetailBean.getData().getUser().getAvatar())
//                .centerCrop()
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(usericon);
//        ll_dynamic_add.addView(head_title);
        /**
         * 动态添加TextView和ImageView
         */
            int type = contentListBean.getType();
            float widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
            float heightPixels = mContext.getResources().getDisplayMetrics().heightPixels;

//            Drawable drawable = mContext.getResources().getDrawable(R.drawable.bg_personal_wish_item_drawable_day);
//            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bg_personal_wish_item_drawable_day);
            switch (type){
                case 1:
                    final ImageView imageView = new ImageView(mContext);
                    float image_width = contentListBean.getImage_width();
                    int image_height = contentListBean.getImage_height();
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.setMargins(0,16,0,16);
                    params.width = (int) widthPixels;
                    params.height = (int) ((image_height / image_width ) * widthPixels);
                    imageView.setLayoutParams(params);
                    Log.i("tag", "convert: "+params.weight+"========"+params.height);
//                    Glide.with(mContext)
//                            .load(contentListBean.getImage_url())
//                            .asBitmap()
//                            .into(new SimpleTarget<Bitmap>() {
//                                @Override
//                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                                    int width = resource.getWidth();
//                                    float height = resource.getHeight();
//                                    float widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
//                                    int mHeight = (int) ((height / width) * widthPixels);
//                                    Log.i("tag", "onResourceReady: "+mHeight);
//                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                                    params.width = (int) widthPixels;
//                                    params.height= mHeight;
//                                    imageView.setLayoutParams(params);
//                                    imageView.setImageBitmap(resource);
//                                }
//                            });

//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(image_width, image_height);
//                    float scale = widthPixels / image_width;
//                    Log.i("tag", widthPixels+"=========="+heightPixels+"========="+image_width+"======="+image_height+"===="+ scale);
////                    params.gravity = Gravity.CENTER_HORIZONTAL;
//                    imageView.setLayoutParams(params);
////                    imageView.setBackgroundColor(Color.RED);
//                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(mContext)
                            .load(contentListBean.getImage_url())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bg_personal_wish_item_drawable_day)
                            .thumbnail(0.1f)
                            .into(imageView);
                    ll_dynamic_add.addView(imageView);
                    break;
                case 2:
                    TextView textView = new TextView(mContext);
                    textView.setText(contentListBean.getText_content());
                    FontHelper.injectFont(textView);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.content_list_text_size));
                    ll_dynamic_add.addView(textView,layoutParams);
                    break;
                case 5:
                    /**
                     * 添加有小图片，价格的物品的视图
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
                    ll_dynamic_add.addView(view,layoutParams);
                    break;
        }
        //此布局的内部footview与其他三种布局不一样添加了发表文章的用户信息
//        View footView = LayoutInflater.from(mContext).inflate(R.layout.layout_content_foot_view,null);
//        TextView tvdate = (TextView) footView.findViewById(R.id.tv_date);
//        TextView tv_nickname = (TextView) footView.findViewById(R.id.tv_nickname);
//        TextView tvcollection = (TextView) footView.findViewById(R.id.tv_collection);
//        CircleImageView user_icon = (CircleImageView) footView.findViewById(R.id.user_icon);
//        tvdate.setText("创建于"+"  "+itemDetailBean.getData().getCreate_time_str());
//        tv_nickname.setText(itemDetailBean.getData().getUser().getNickname());
//        tvcollection.setText(itemDetailBean.getData().getLikes());
//        CirImageViewUtils.loadCirImage(itemDetailBean.getData().getUser().getAvatar(),mContext,user_icon);
//        ll_dynamic_add.addView(footView);
    }
    public float getSize(int id) {
        //返回的是px  18
        return mContext.getResources().getDimensionPixelSize(id);
    }
}
