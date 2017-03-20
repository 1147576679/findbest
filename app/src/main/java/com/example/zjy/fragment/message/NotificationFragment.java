package com.example.zjy.fragment.message;

import android.os.Bundle;

import com.example.zjy.bantang.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;

/**
 * Created by zjy on 2017/3/13.
 */

public class NotificationFragment extends BaseFragment {
    @Override
    public int getContentId() {
        return R.layout.fragment_message_notification;
    }

    public static NotificationFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
