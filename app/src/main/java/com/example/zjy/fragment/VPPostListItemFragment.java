package com.example.zjy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.bantang.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/29.
 * Post_list布局中ViewPager的fragment，用来展示图片
 */

public class VPPostListItemFragment extends BaseFragment {

    private String url;
    private int position;
    private int size;
    //展示图片集合的viewpager
    @Bind(R.id.iv_vp_show)
    ImageView iv_vp_show;
    //记录当前viewpager的下标
    @Bind(R.id.tv_now_position)
    TextView tv_now_position;
    //记录viewpager总共的size
    @Bind(R.id.tv_total_pics)
    TextView tv_total_pics;
    //显示当前位置和总是的线性布局 size == 1 时 不显示
    @Bind(R.id.ll_count_tag)
    LinearLayout ll_count_tag;
    @Override
    public int getContentId() {
        return R.layout.fragment_vp_post_list_item;
    }

    public static VPPostListItemFragment newInstance(String url,int position,int size) {
        Bundle args = new Bundle();
        args.putString("url",url);
        args.putInt("position",position);
        args.putInt("size",size);
        VPPostListItemFragment fragment = new VPPostListItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getDatas(Bundle bundle) {
        url = bundle.getString("url");
        position = bundle.getInt("position");
        size = bundle.getInt("size");
        initData();
    }

    private void initData() {
        Log.e("tag", "VPPostListItemFragment: "+url+"----"+position);
        if(size == 1){
            ll_count_tag.setVisibility(View.GONE);
        }else {
            tv_total_pics.setText(size+"");
            tv_now_position.setText((position+1)+"");
        }
        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.bg_personal_wish_item_drawable_day)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_vp_show);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.w("TAG","onViewCreate~~~~~~~~~~"+url);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("tag", "onCreate:~~~~~~~~~~~~~~ ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("tag", "onDestroy: ~~~~~~~~~~~~~");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.w("tag", "onDestroyView: ~~~~~~~~~~~~~~~~~~~~~");
    }
}
