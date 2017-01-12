package com.example.zjy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.zjy.bean.HomeContentBean;
import com.example.zjy.fragment.TestHomeSecondDetailFragment;

import java.util.List;

/**
 * Created by zjy on 2016/12/24.
 * 首页item详情页的ViewPager 的适配器
 */

public class HomeItemDetailAdapter extends FragmentStatePagerAdapter {
    private List<HomeContentBean> mDatas;
    public HomeItemDetailAdapter(FragmentManager fm,List<HomeContentBean> mDatas) {
        super(fm);
        this.mDatas = mDatas;
    }

    @Override
    public Fragment getItem(int position) {
//        return HomeItemDetailFragment.getInstance(mDatas.get(position).getId());
        return TestHomeSecondDetailFragment.getInstance(mDatas.get(position).getId());
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
