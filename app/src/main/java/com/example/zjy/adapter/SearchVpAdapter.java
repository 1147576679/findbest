package com.example.zjy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zjy.fragment.SearchArticleFragment;
import com.example.zjy.fragment.SearchSingleFragment;
import com.example.zjy.util.Constants;

/**
 * Created by zjy on 2016/12/28.
 * 单品与文章fragment的适配器
 */

public class SearchVpAdapter extends FragmentPagerAdapter {
    public SearchVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return SearchSingleFragment.newInstance(Constants.URL_SEARCH_SINGLE);
        }
        return SearchArticleFragment.newInstance(Constants.URL_SEARCH_ARTICLE);
    }

    @Override
    public int getCount() {
        return Constants.TAB.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.TAB[position];
    }
}
