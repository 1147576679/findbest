package com.example.zjy.fragment.community.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.niklauslibrary.lvhelper.UniversalAdapter;

/**
 * Created by zjy on 2017/4/5.
 */

public class KolRankGridViewAdapter extends UniversalAdapter<String> {
    public KolRankGridViewAdapter(Context context, int resid) {
        super(context, resid);
    }

    @Override
    public void bindView(ViewHolder viewHolder, String data) {
        viewHolder.setImageUrl(R.id.iv,data,R.drawable.place_holder);
    }
}
