package com.example.zjy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zjy.fragment.SearchKeywordPostFragment;
import com.example.zjy.fragment.SearchKeywordSingleFragment;
import com.example.zjy.fragment.SearchKeywordTopicFragment;
import com.example.zjy.fragment.SearchKeywordUserFragment;
import com.example.zjy.util.Constants;

/**
 * Created by zjy on 2016/12/31.
 */

public class SearchKeywordVPAdapter extends FragmentPagerAdapter {
//    private String keyword;
    public SearchKeywordVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SearchKeywordSingleFragment();
            case 1:
                return new SearchKeywordTopicFragment();
            case 2:
                return new SearchKeywordPostFragment();
            case 3:
                return new SearchKeywordUserFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return Constants.KEYWORD_TAB.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.KEYWORD_TAB[position];
    }
}
