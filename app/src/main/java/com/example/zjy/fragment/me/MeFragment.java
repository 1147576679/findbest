package com.example.zjy.fragment.me;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.zjy.bantang.R;
import com.example.zjy.bantang.SettingsActivity;
import com.example.zjy.niklauslibrary.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zjy on 2017/3/14.
 */

public class MeFragment extends BaseFragment {
    @Bind(R.id.settings)
    RelativeLayout settings;
    @Override
    public int getContentId() {
        return R.layout.fragment_me;
    }

    @OnClick(R.id.settings)
    public void rlClick(View view){
        startActivity(SettingsActivity.newInstance(getContext()));
    }
}
