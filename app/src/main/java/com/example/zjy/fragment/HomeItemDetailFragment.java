package com.example.zjy.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.WebviewBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/21.
 * 第一种最简单实现RecyclerView的item的详情页（WebView）
 */

public class HomeItemDetailFragment extends BaseFragment implements RetrofitUtil.DownListener {
    @Bind(R.id.web_view)
    WebView webView;
    private String mId;
    private WebSettings mSettings;
    @Bind(R.id.load_anim)
    ImageView imageView;
    private AnimationDrawable animationDrawable;
    @Override
    public int getContentId() {
        return R.layout.fragment_home_item_detail;
    }

    public static HomeItemDetailFragment getInstance(String id){
        HomeItemDetailFragment homeItemDetailFragment = new HomeItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        homeItemDetailFragment.setArguments(bundle);
        return homeItemDetailFragment;
    }

    @Override
    protected void init(View view) {
        mSettings = webView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setDefaultTextEncodingName("UTF-8");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                animationDrawable.stop();
                imageView.setVisibility(View.GONE);
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void getDatas(Bundle bundle) {
        mId = bundle.getString("id");
//        Log.i("tag", "getDatas: "+mId);
        loadDatas();
    }

    @Override
    protected void loadDatas() {
        String url = String.format(Constants.URL_ITEM_DETAIL,mId);
//        Log.i("tag", "loadDatas: "+url);
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x001);
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        return ParseJsonUtils.parseWebViewUrl(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        WebviewBean webviewBean = (WebviewBean) object;
        String share_url = webviewBean.getData().getShare_url();
        webView.loadUrl(share_url);
    }

    @Override
    public void fail(Throwable throwable) {

    }

}
