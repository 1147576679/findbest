package com.example.zjy.fragment.message.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zjy.bantang.R;
import com.example.zjy.fragment.message.bean.PushVo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by zjy on 2017/4/5.
 */

public class PushRvAdapter extends CommonAdapter<PushVo> {
    private Context mContext;

    public PushRvAdapter(Context context, int layoutId, List<PushVo> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, PushVo pushVo, int position) {
        ImageView imageView = holder.getView(R.id.iv);
        Glide.with(mContext)
                .load(pushVo.ivUrl)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .into(imageView);
        holder.setText(R.id.dateStr, pushVo.dataStr)
                .setText(R.id.title,pushVo.title);
    }
}
