package com.example.zjy.fragment.message;

import android.os.Bundle;

import com.example.zjy.bantang.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;

/**
 * Created by zjy on 2017/3/13.
 */

public class CommentFragment extends BaseFragment {
    @Override
    public int getContentId() {
        return R.layout.fragment_message_comment;
    }

    public static CommentFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
