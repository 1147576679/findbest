package com.example.zjy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.fragment.VPPostListItemFragment;

import java.util.List;

/**
 * Created by zjy on 2016/12/29.
 * post_list RecyclerView item中 viewpager的适配器
 */

public class VPPostListItemAdapter extends FragmentStatePagerAdapter {
    List<ItemDetailBean.DataBean.PostListBean.PicsBean> datas;
    public VPPostListItemAdapter(FragmentManager fm, List<ItemDetailBean.DataBean.PostListBean.PicsBean> datas) {
        super(fm);
        this.datas = datas;
//        Log.i("tag", "VPPostListItemAdapter: "+datas);
    }

    @Override
    public Fragment getItem(int position) {
        return VPPostListItemFragment.newInstance(datas.get(position).getUrl(),position,datas.size());
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
