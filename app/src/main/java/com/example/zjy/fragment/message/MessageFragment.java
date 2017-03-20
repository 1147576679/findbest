package com.example.zjy.fragment.message;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.message.adapter.MessageVpAdapter;
import com.example.zjy.niklauslibrary.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by zjy on 2017/3/13.
 */

public class MessageFragment extends BaseFragment {
    //message头部展示
    @Bind(R.id.tab_message)
    TabLayout tabMessage;
    //message的Viewpager
    @Bind(R.id.vp_message)
    ViewPager vpMessage;
    //message的Viewpager的适配器
    private MessageVpAdapter messageVpAdapter;

    @Override
    public int getContentId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init(View view) {
        messageVpAdapter = new MessageVpAdapter(getChildFragmentManager());
        vpMessage.setAdapter(messageVpAdapter);
        tabMessage.setupWithViewPager(vpMessage);
    }
}
