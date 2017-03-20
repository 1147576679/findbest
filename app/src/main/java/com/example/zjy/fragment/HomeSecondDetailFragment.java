package com.example.zjy.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.zjy.adapter.RVItemDetailAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.HeaderAndFooterWrapper;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;
import com.example.zjy.widget.FooterView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zjy on 2016/12/26.
 * 点击首页recyclerView的item跳转到的Activity中Viewpager的fragment
 */

public class HomeSecondDetailFragment extends BaseFragment implements RetrofitUtil.DownListener {
    @Bind(R.id.iv_back_arrow)
    ImageView iv_back_arrow;
    @Bind(R.id.iv_share)
    ImageView iv_share;
    @Bind(R.id.iv_like)
    ImageView iv_like;

    @Bind(R.id.iv_frame_anim)
    ImageView iv_frame_anim;
    private AnimationDrawable animationDrawable;
    /**
     * 不同接口不同类型布局的RecyclerView
     */
    @Bind(R.id.rv_item_detail)
    RecyclerView rv_item_detail;
    /**
     * 多布局的适配器
     */
    RVItemDetailAdapter mRVItemDetailAdapter;

    private CommonAdapter commonAdapter;

    //拼接接口的字段
    private String mId;
    private FooterView footerView;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private String url;

    @Override
    public int getContentId() {
        return R.layout.fragment_item_detail2;
    }

    public static HomeSecondDetailFragment getInstance(String id){
        HomeSecondDetailFragment homeSecondDetailFragment = new HomeSecondDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        homeSecondDetailFragment.setArguments(bundle);
        return homeSecondDetailFragment;
    }
    //返回，分享，收藏imageview的点击事件
    @OnClick({R.id.iv_back_arrow,R.id.iv_share,R.id.iv_like})
    public void ivClick(ImageView imageView){
        switch (imageView.getId()){
            case R.id.iv_back_arrow:
                getActivity().finish();
                break;
            case R.id.iv_like:
                break;
            case R.id.iv_share:
                break;
        }
    }

    @Override
    protected void init(View view) {
        animationDrawable = (AnimationDrawable) iv_frame_anim.getDrawable();
        animationDrawable.start();
        mRVItemDetailAdapter = new RVItemDetailAdapter(getContext());
        mRVItemDetailAdapter.setFragmentManager(getChildFragmentManager());
//        rv_item_detail.setAdapter(mRVItemDetailAdapter);
        rv_item_detail.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        ));
        initFootView();
    }
    //初始化底部的评论视图
    private void initFootView() {
        headerAndFooterWrapper = new HeaderAndFooterWrapper(mRVItemDetailAdapter);
        footerView = new FooterView(getContext());
        headerAndFooterWrapper.addFootView(footerView);
        rv_item_detail.setAdapter(headerAndFooterWrapper);
    }

    @Override
    protected void getDatas(Bundle bundle) {
        mId = bundle.getString("id");
        footerView.setID(mId);
        loadDatas();
    }

    /**
     * 加载数据
     */
    @Override
    protected void loadDatas() {
        url = String.format(Constants.URL_ITEM_DETAIL,mId);
        Log.i("tag", "loadDatas: "+ url);
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x001);
    }

    /**
     * 解析数据
     * @param json
     * @param requestCode
     * @return
     */
    @Override
    public Object paresJson(String json, int requestCode) {
//        Log.i("tag", "paresJson: "+json);
//        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseItemDetail(json);
    }

    /**
     * 解析成功
     * @param object
     * @param requestCode
     */
    @Override
    public void downSucc(Object object, int requestCode) {
        ItemDetailBean itemDetailBean = (ItemDetailBean) object;
//        Log.i("tag", "downSucc: "+itemDetailBean.getData().getPost_list());
        mRVItemDetailAdapter.addData(itemDetailBean);
        animationDrawable.stop();
        iv_frame_anim.setVisibility(View.GONE);

//        webView.loadData(itemDetailBean.getData().getArticle_content(),"text/html; charset=UTF-8",null);
    }

    /**
     * 下载失败回调
     * @param throwable
     */
    @Override
    public void fail(Throwable throwable) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("AAAAAAAAAAAAAA", "onCreate: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("AAAAAAAAAAAAAA", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("AAAAAAAAAAAAAA", "onDestroy: ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("AAAAAAAAAAAAAA", "onDetach: ");
    }
}
