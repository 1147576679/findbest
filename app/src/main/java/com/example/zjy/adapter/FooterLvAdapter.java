package com.example.zjy.adapter;

import android.content.Context;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.UserCommentBean;
import com.example.zjy.niklauslibrary.lvhelper.UniversalAdapter;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2016/12/27.
 */

public class FooterLvAdapter extends UniversalAdapter<UserCommentBean.DataBean> {
    private Context context;
    public FooterLvAdapter(Context context) {
        super(context, R.layout.footer_item_list);
        this.context = context;
    }

    @Override
    public void bindView(ViewHolder viewHolder, UserCommentBean.DataBean data) {
        if(data != null) {
            viewHolder.setText(R.id.tv_nickname, data.getUser().getNickname());
            viewHolder.setText(R.id.tv_content, data.getConent());
            viewHolder.setText(R.id.tv_date, data.getDatestr());
            CircleImageView user_icon = (CircleImageView) viewHolder.getView(R.id.user_icon);
            CirImageViewUtils
                    .loadCirImage(data.getUser().getAvatar(), context, user_icon);
        }
    }
}
