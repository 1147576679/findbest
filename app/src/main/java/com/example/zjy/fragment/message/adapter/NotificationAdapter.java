package com.example.zjy.fragment.message.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.message.bean.dto.NotificationVo;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2017/4/13.
 */

public class NotificationAdapter extends CommonAdapter<NotificationVo> {
    private Context mContext;
    public NotificationAdapter(Context context, int layoutId, List<NotificationVo> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, NotificationVo notificationVo, int position) {
        holder.setText(R.id.tv_nickname,notificationVo.nickname)
                .setText(R.id.tv_content,notificationVo.content);
        CircleImageView circleImageView = holder.getView(R.id.user_icon);
        CirImageViewUtils.loadCirImage(notificationVo.avatar,mContext,circleImageView);
    }
}
