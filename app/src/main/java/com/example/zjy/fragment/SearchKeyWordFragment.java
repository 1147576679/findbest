package com.example.zjy.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.zjy.adapter.SearchKeywordVPAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/31.
 */

public class SearchKeyWordFragment extends BaseFragment {
    private String keyword;
    @Bind(R.id.tab_layout_keyword_search)
    TabLayout tab_layout_keyword_search;
    @Bind(R.id.vp_keyword_search)
    ViewPager vp_keyword_search;
    private SearchKeywordVPAdapter searchKeywordVPAdapter;
    @Override
    public int getContentId() {
        return R.layout.fragment_search_keyword;
    }

//    public void setKeyword(String keyword){
//        this.keyword = keyword;
//    }

    @Override
    protected void init(View view) {
        searchKeywordVPAdapter = new SearchKeywordVPAdapter(getFragmentManager());
        vp_keyword_search.setOffscreenPageLimit(3);
        vp_keyword_search.setAdapter(searchKeywordVPAdapter);
        tab_layout_keyword_search.setupWithViewPager(vp_keyword_search);
    }
}
