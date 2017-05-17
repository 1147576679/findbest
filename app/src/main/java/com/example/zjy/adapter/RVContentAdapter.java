package com.example.zjy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
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
import com.example.zjy.widget.PFTextView;


/**
 * Created by zjy on 2017/1/2.
 */

public class RVContentAdapter extends CommonAdapter<ItemDetailBean.DataBean.ContentListBean> {
    private Context mContext;
    private ClickImageListener clickImageListener;
    public RVContentAdapter(Context context,ClickImageListener clickImageListener) {
        super(context, R.layout.item_detail_rv_content);
        mContext = context;
        this.clickImageListener = clickImageListener;
    }

    @Override
    protected void convert(ViewHolder holder, final ItemDetailBean.DataBean.ContentListBean contentListBean, final int position) {
        LinearLayout ll_dynamic_add = holder.getView(R.id.ll_dynamic_add);
        ll_dynamic_add.removeAllViews();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_dynamic_add.getLayoutParams();
        int offset = mContext.getResources().getDimensionPixelOffset(R.dimen.internal);
        int margin = mContext.getResources().getDimensionPixelOffset(R.dimen.margin);
        layoutParams.setMargins(margin,margin,margin,0);
        /**
         * 动态添加TextView和ImageView
         */
            int type = contentListBean.getType();
            float widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;

//            Drawable drawable = mContext.getResources().getDrawable(R.drawable.bg_personal_wish_item_drawable_day);
//            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bg_personal_wish_item_drawable_day);
            switch (type){
                case 1:
                    final ImageView imageView = new ImageView(mContext);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           if(clickImageListener != null){
                               clickImageListener.onClick(contentListBean.getImage_url(),position);
                           }
                        }
                    });
                    float image_width = contentListBean.getImage_width();
                    int image_height = contentListBean.getImage_height();
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.setMargins(0,offset,margin,0);
                    params.width = (int) widthPixels;
                    params.height = (int) ((image_height / image_width ) * widthPixels);
                    imageView.setLayoutParams(params);
                    Log.i("tag", "convert: "+params.width+"========"+params.height);
                    Glide.with(mContext)
                            .load(contentListBean.getImage_url())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.place_holder)
                            .thumbnail(0.1f)
                            .into(imageView);
                    ll_dynamic_add.addView(imageView);
                    break;
                case 2:
                    TextView textView = new PFTextView(mContext);
                    textView.setText(contentListBean.getText_content());
                    textView.setTextColor(ContextCompat.getColor(mContext,R.color.text_color));
//                    FontHelper.injectFont(textView);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.content_list_text_size));
                    ll_dynamic_add.addView(textView);
                    break;
                case 5:
                    /**
                     * 添加有小图片，价格的物品的视图
                     */
                    View view = LayoutInflater.from(mContext).inflate(R.layout.layout_content_list,null);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(contentListBean.getUrl()));
                            mContext.startActivity(intent);
                        }
                    });
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
    public float getSize(int id) {
        //返回的是px  18
        return mContext.getResources().getDimensionPixelSize(id);
    }

    public interface ClickImageListener{
        void onClick(String url,int position);
    }
}
