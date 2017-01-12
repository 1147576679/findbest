package com.example.zjy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchSingleBean;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;

/**
 * Created by zjy on 2016/12/28.
 */

public class SearchSingleRVTabAdapter extends CommonAdapter<SearchSingleBean.DataBean> {
    private int mPosition;
    public SearchSingleRVTabAdapter(Context context) {
        super(context, R.layout.item_rv_search_tab);
    }

    @Override
    protected void convert(ViewHolder holder, SearchSingleBean.DataBean dataBean, int position) {
        TextView view = holder.getView(R.id.tv_tab_title);
        holder.setText(R.id.tv_tab_title,dataBean.getName());
        if(mPosition == position){
            view.setBackgroundColor(Color.WHITE);
            view.setTextColor(Color.parseColor("#FD6363"));
        }else {
            view.setBackgroundColor(Color.parseColor("#F0F0F0"));
            view.setTextColor(Color.parseColor("#9B9B9B"));
        }
    }
    public void setPosition(int position){
        mPosition = position;
    }
}
