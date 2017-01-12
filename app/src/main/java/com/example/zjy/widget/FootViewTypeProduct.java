package com.example.zjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;

/**
 * Created by zjy on 2017/1/1.
 */

public class FootViewTypeProduct extends LinearLayout {
    private ItemDetailBean.DataBean dataBean;
    private TextView tvcollection;
    private TextView tvdate;

    public void setDataBean(ItemDetailBean.DataBean dataBean) {
        this.dataBean = dataBean;
        loadData();
    }

    public FootViewTypeProduct(Context context) {
        this(context, null);
    }

    public FootViewTypeProduct(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FootViewTypeProduct(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void loadData() {
        tvcollection.setText(dataBean.getPraises());
        tvdate.setText(dataBean.getDatestr());
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_product_foot,this,true);
        tvcollection = (TextView)findViewById(R.id.tv_collection);
        tvdate = (TextView) findViewById(R.id.tv_date);
    }
}
