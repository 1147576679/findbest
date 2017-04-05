package com.example.zjy.bantang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.zjy.adapter.HomeItemDetailAdapter;
import com.example.zjy.bean.HomeContentBean;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import java.util.List;

import butterknife.Bind;

/**
 *
 * 四种布局 根据接口返回的四种类型分别有不同的布局
 * WebView  根据该字段  private String article_content ItemDetailBean的108行;
 * Product_list 接口返回一个集合 再次布局中在嵌套一层RecyclerView
 * Content_list 接口返回一个集合 动态添加控件的布局
 * Post_list  接口返回一个集合  再次布局中在嵌套一层RecyclerView
 */

public class HomeItemDetailActivity extends BaseActivity implements RetrofitUtil.DownListener {
    @Bind(R.id.vp_home_item_detail)
    ViewPager vp_home_item_detail;
    private HomeItemDetailAdapter homeItemDetailAdapter;

    //点击item传过来的id
    private String mId;

    //点击item调转到此activity的当前item的下标，调用viewpager.setCurrentPosition（mposition）；
    private int mPosition;

    //缓存当前有多少条数据
    private List<HomeContentBean> mDatas;

    //将Home Viewpager的的下标传过来（hotFragment 没有分页）
    private int vpindex;

    //分页用的，传过来的当前的页数，当用户查看item详情到最后一个时，加载下一页数据，提高用户体验，
    //不然用户用户又要退出此activity去上拉加载更多的item
    private int page;

    //拼接字符串
    private String extend;


    @Override
    public int getContentId() {
        return R.layout.activity_home_item_detail;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
//        mId = intent.getStringExtra("id");
//        mPosition = intent.getIntExtra("position",-1);
        Bundle bundle = intent.getExtras();
        mDatas = (List<HomeContentBean>) bundle.getSerializable("datas");
//        Log.i("tag", "init: "+mDatas);
        mId = bundle.getString("id");
        mPosition = bundle.getInt("position");
        vpindex = bundle.getInt("vpindex");
        Log.i("tag", "init: "+vpindex);
        page = bundle.getInt("page");
        extend = bundle.getString("extend");
        Log.i("tag", "init: "+mId);
        homeItemDetailAdapter = new HomeItemDetailAdapter(getSupportFragmentManager(),mDatas);
        vp_home_item_detail.setAdapter(homeItemDetailAdapter);
        Log.i("tag", "init: "+mDatas.size());
    }


    @Override
    protected void bindListener() {
        vp_home_item_detail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当home viewpager传过来的下标是2时，不用加载后面的数据，没有分页
                if(vpindex != 2) {
                    //当用户查看item详情到倒数第二个时，加载下一页数据，提高用户体验
                    if (position == mDatas.size() - 2) {
                        page++;
                        Log.i("tag", "onPageSelected: 调用了"+page);
                        String url;
                        switch (vpindex){
                            case 0:
                            case 1:
                                url = String.format(Constants.URL_JINXUAN_YUANCHUANG,page);
                                break;
                            default:
                                url = String.format(Constants.URL_OTHER,page,extend);
                                break;
                        }
                        new RetrofitUtil(HomeItemDetailActivity.this).setDownListener(HomeItemDetailActivity.this).downJson(url, 0x001);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_home_item_detail.setCurrentItem(mPosition);
    }

    @Override
    protected boolean isOpenTranslucent() {
        return false;
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        return ParseJsonUtils.parseContent(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        List<HomeContentBean> datas = (List<HomeContentBean>) object;
        mDatas.addAll(datas);
//        vp_home_item_detail.setCurrentItem(datas.size());
        homeItemDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
