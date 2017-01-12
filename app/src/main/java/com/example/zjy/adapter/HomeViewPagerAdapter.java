package com.example.zjy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zjy.bean.HeadAndTabBean;
import com.example.zjy.fragment.HomeHotFragment;
import com.example.zjy.fragment.HomeViewPagerFragment;
import com.example.zjy.util.Constants;

import java.util.List;

/**
 * Created by zjy on 2016/12/19.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private List<HeadAndTabBean.DataBean.CategoryElementBean> datas;
    public HomeViewPagerAdapter(FragmentManager fm,List<HeadAndTabBean.DataBean.CategoryElementBean> datas) {
        super(fm);
        this.datas = datas;

    }

    @Override
    public Fragment getItem(int position) {
        String url;
        switch (position){
            case 0:
            case 1:
//                url = String.format(Constants.URL_JINXUAN_YUANCHUANG,0);
                url = Constants.URL_JINXUAN_YUANCHUANG;
                break;
            case 2:
//                url = String.format(Constants.URL_HOT,datas.get(position).getExtend());
                url = datas.get(position).getExtend();
                return HomeHotFragment.getInstance(url,position);
            default:
//                url = String.format(Constants.URL_OTHER,0,datas.get(position).getExtend());
                url = datas.get(position).getExtend();
                break;

        }
        return HomeViewPagerFragment.getInstance(url,position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).getTitle();
    }
}
