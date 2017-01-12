package com.example.zjy.bantang;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.zjy.adapter.RVItemDetailAdapter;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.HeaderAndFooterWrapper;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;
import com.example.zjy.widget.FooterView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 关键字搜索文章结果点击查看详情的界面
 */
public class SearchKeywordTopicResultActivity extends BaseActivity implements RetrofitUtil.DownListener {

    private String id;

    @Bind(R.id.iv_frame_anim)
    ImageView iv_frame_anim;
    @Bind(R.id.rv_keyword_search_result)
    RecyclerView rv_keyword_search_result;

    private RVItemDetailAdapter rvItemDetailAdapter;

    private AnimationDrawable animationDrawable;

    //底部评论
    private FooterView footerView;
    private HeaderAndFooterWrapper headerAndFooterWrapper;

    @Override
    public int getContentId() {
        return R.layout.activity_search_keyword_topic_result;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        animationDrawable = (AnimationDrawable) iv_frame_anim.getDrawable();
        animationDrawable.start();
        rvItemDetailAdapter = new RVItemDetailAdapter(this);
        rv_keyword_search_result.setAdapter(rvItemDetailAdapter);
        rv_keyword_search_result.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        initFootView();
    }

    private void initFootView() {
        //初始化底部的评论视图
        headerAndFooterWrapper = new HeaderAndFooterWrapper(rvItemDetailAdapter);
        footerView = new FooterView(this);
        footerView.setID(id);
        headerAndFooterWrapper.addFootView(footerView);
        rv_keyword_search_result.setAdapter(headerAndFooterWrapper);
    }

    @Override
    protected void loadDatas() {
        String format = String.format(Constants.URL_ITEM_DETAIL, id);
        new RetrofitUtil(this).setDownListener(this).downJson(format,0x001);
    }

    //返回，分享，收藏imageview的点击事件
    @OnClick({R.id.iv_back_arrow,R.id.iv_share,R.id.iv_like})
    public void ivClick(ImageView imageView){
        switch (imageView.getId()){
            case R.id.iv_back_arrow:
                finish();
                break;
            case R.id.iv_like:
                break;
            case R.id.iv_share:
                break;
        }
    }

    //Retrofit 下载json 转化为Gson的是三个监听回调方法
    @Override
    public Object paresJson(String json, int requestCode) {
        return ParseJsonUtils.parseItemDetail(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null) {
//            iv_frame_anim.clearAnimtion();
            animationDrawable.stop();
            iv_frame_anim.setVisibility(View.GONE);
            ItemDetailBean itemDetailBean = (ItemDetailBean) object;
            rvItemDetailAdapter.addData(itemDetailBean);
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
