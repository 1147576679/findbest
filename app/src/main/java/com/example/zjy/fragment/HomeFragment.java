package com.example.zjy.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.zjy.adapter.HomeViewPagerAdapter;
import com.example.zjy.bantang.IdsActivity;
import com.example.zjy.bantang.R;
import com.example.zjy.bantang.SearchActivity;
import com.example.zjy.bean.HeadAndTabBean;
import com.example.zjy.controller.HeadController;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.util.ConvenientBannerUtils;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zjy on 2016/12/19.
 */

public class HomeFragment extends BaseFragment implements RetrofitUtil.DownListener {
    //加载头部广告栏
    @Bind(R.id.convenient_banner)
    ConvenientBanner mConvenientBanner;
    //主页标签栏  -- 精选 热门...
    @Bind(R.id.home_tab)
    TabLayout mHome_tab;
    //    @Bind(R.id.ll_err)
//    LinearLayout ll_err;
    //主页的显示列表
    @Bind(R.id.home_vp)
    ViewPager mHome_vp;
    //ViewPager适配器
    private HomeViewPagerAdapter mHomeViewPagerAdapter;
    //缓存广告栏图片的集合
    private List<String> mList;

    private HeadController mHeadController;


    public static Intent newInstance(Context context) {
        return new Intent(context, SearchActivity.class);
    }

//    @Bind(R.id.tv_search)
//    TextView tv_search;

    //缓存Tab标题的集合
    @Override
    public int getContentId() {
        return R.layout.fragment_home_copy;
    }

    //初始化
    @Override
    protected void init(View view) {
        mHeadController = new HeadController();
//        mHeadController.head(Constants.URL_HEAD_TAB, new RxCallBack<HeadAndTabBean>() {
//            @Override
//            public void onNext(HeadAndTabBean headAndTabBean) {
//                Log.i("tag", "onNext: "+headAndTabBean);
//            }
//
//            @Override
//            public void onTerminate() {
//            }
//
//        });
        String jsonCache = DiskLruCacheUtil.getJsonCache(Constants.URL_HEAD_TAB);
        //Todo 备选方案
//        if(jsonCache == null){
//            loadDatas();
//            Log.i("tag", "网络下载数据");
//        }else {
//            HeadAndTabBean headAndTabBean = ParseJsonUtils.parse(jsonCache);
//            initViews(headAndTabBean);
//            Log.i("tag", "从缓存中读取数据 ");
//        }
        loadDatas();
    }

    //加载数据
    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(Constants.URL_HEAD_TAB, 0x001);
    }

    //解析json
    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(Constants.URL_HEAD_TAB, json);
        return ParseJsonUtils.parse(json);
    }

    //解析完成
    @Override
    public void downSucc(Object object, int requestCode) {
//        ll_err.setVisibility(View.GONE);
        initViews((HeadAndTabBean) object);
    }

    private void initViews(HeadAndTabBean object) {
        EventBus.getDefault().post(0x001);
        HeadAndTabBean headAndTabBean = object;
        final List<HeadAndTabBean.DataBean.BannerBean> banner = headAndTabBean.getData().getBanner();
        mList = new ArrayList<>();
        for (int i = 0; i < banner.size(); i++) {
            String photo = banner.get(i).getPhoto();
            mList.add(photo);
        }
//        mList.add("http://pic1.bantangapp.com/topic/201704/11/99551011_144023_1.jpg");
        //加载头部广告栏
        new ConvenientBannerUtils.Builder()
                .mList(mList)
                .indicatorSelect(R.drawable.ic_indicator_image_selected)
                .indicatorUnselect(R.drawable.ic_indicator_image)
                .orientation(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .time(3000)
                .onConvenientBannerListener(new ConvenientBannerUtils.OnConvenientBannerListener() {
                    @Override
                    public void onClick(int position) {
                        //广告栏的点击事件
//                        Toast.makeText(getActivity(), "...."+position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), IdsActivity.class);
                        String extend = banner.get(position).getExtend();
                        intent.putExtra("extend", extend);
                        intent.putExtra("title", banner.get(position).getTitle());
                        startActivity(intent);
                    }
                })
                .build()
                .ConvenientBanner(mConvenientBanner);

        //初始化标签栏
        List<HeadAndTabBean.DataBean.CategoryElementBean> category_element = headAndTabBean.getData().getCategory_element();
        mHomeViewPagerAdapter = new HomeViewPagerAdapter(getFragmentManager(), category_element);
        mHome_vp.setAdapter(mHomeViewPagerAdapter);
        mHome_tab.setupWithViewPager(mHome_vp);
    }

    @Override
    public void fail(Throwable throwable) {
//        ll_err.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "没有网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void bindListener() {
        //加载失败后点击事件重新加载
//        ll_err.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadDatas();
//            }
//        });

    }

    //点击文本搜索框跳转到搜索Activity
    @OnClick(R.id.tv_search)
    public void click(TextView textView) {
        getActivity().startActivity(newInstance(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mHeadController.detachView();
    }
}

