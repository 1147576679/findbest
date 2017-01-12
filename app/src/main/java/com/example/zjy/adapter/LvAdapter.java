package com.example.zjy.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchSingleBean;
import com.example.zjy.niklauslibrary.lvhelper.UniversalAdapter;

/**
 * Created by zjy on 2016/12/28.
 */

public class LvAdapter extends UniversalAdapter<SearchSingleBean.DataBean> {

    public LvAdapter(Context context) {
        super(context, R.layout.item_rv_search_tab);
    }

    @Override
    public void bindView(ViewHolder viewHolder, SearchSingleBean.DataBean data) {
        viewHolder.setText(R.id.tv_tab_title,data.getName());
    }
}
