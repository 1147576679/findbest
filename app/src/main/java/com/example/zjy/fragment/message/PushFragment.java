package com.example.zjy.fragment.message;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.zjy.bantang.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by zjy on 2017/3/13.
 */

public class PushFragment extends BaseFragment {

    @Bind(R.id.rv_push)
    RecyclerView mRvPush;

    @Override
    public int getContentId() {
        return R.layout.fragment_message_push;
    }

    public static PushFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PushFragment fragment = new PushFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
