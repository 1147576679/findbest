package com.example.zjy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.zjy.adapter.SearchVpAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/31.
 * 展示有分类标签的fragment
 */

public class SearchCategoryFragment extends BaseFragment {
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.vp_search)
    ViewPager vp_search;
    private SearchVpAdapter searchVpAdapter;
    @Override
    public int getContentId() {
        return R.layout.fragment_search_category;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "onCreate: ");
    }

    @Override
    protected void init(View view) {
        searchVpAdapter = new SearchVpAdapter(getFragmentManager());
        vp_search.setAdapter(searchVpAdapter);
        tabLayout.setupWithViewPager(vp_search);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag", "onDestroy: ");
    }
}
