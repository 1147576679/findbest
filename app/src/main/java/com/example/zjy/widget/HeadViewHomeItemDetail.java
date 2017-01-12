package com.example.zjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2016/12/26.
 *  首页RecyclerView item 详情多布局之product_list 的RecyclerView的头部布局
 */

public class HeadViewHomeItemDetail extends LinearLayout {
    private TextView tv_title,tv_views,tv_desc;
    private CircleImageView user_icon;
    private ItemDetailBean.DataBean data;
    private Context mContext;
    public HeadViewHomeItemDetail(Context context) {
        this(context, null);
    }

    public HeadViewHomeItemDetail(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadViewHomeItemDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        mContext = context;
    }
    public void setData(ItemDetailBean.DataBean data){
        this.data = data;
        loadData();
    }
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.head_view_home_detail,this,true);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_views = (TextView) findViewById(R.id.tv_views);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        user_icon = (CircleImageView) findViewById(R.id.user_icon);
    }

    private void loadData(){
        tv_title.setText(data.getTitle());
        tv_views.setText(data.getViews());
        tv_desc.setText(data.getDesc());
        Glide.with(mContext)
                .load(data.getUser().getAvatar())
                .dontAnimate()
                .centerCrop()
                .into(user_icon);
    }
}
