package com.example.zjy.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.adapter.RVContentAdapter;
import com.example.zjy.adapter.RVPostListAdapter;
import com.example.zjy.adapter.RVProductAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.HeaderAndFooterWrapper;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;
import com.example.zjy.widget.FootViewTypeProduct;
import com.example.zjy.widget.FooterView;
import com.example.zjy.widget.HeadViewHomeItemDetail;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2016/12/26.
 * 点击首页recyclerView的item跳转到的Activity中Viewpager的fragment
 */

public class TestHomeSecondDetailFragment extends BaseFragment implements RetrofitUtil.DownListener {
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
//    RVItemDetailAdapter mRVItemDetailAdapter;

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

    public static TestHomeSecondDetailFragment getInstance(String id){
        TestHomeSecondDetailFragment homeSecondDetailFragment = new TestHomeSecondDetailFragment();
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
        //开场的帧动画
        animationDrawable = (AnimationDrawable) iv_frame_anim.getDrawable();
        animationDrawable.start();
//        mRVItemDetailAdapter = new RVItemDetailAdapter(getContext());
//        mRVItemDetailAdapter.setFragmentManager(getChildFragmentManager());
////        rv_item_detail.setAdapter(mRVItemDetailAdapter);
//        rv_item_detail.setLayoutManager(new LinearLayoutManager(
//                getContext(),
//                LinearLayoutManager.VERTICAL,
//                false
//        ));
//        initFootView();
    }


    @Override
    protected void getDatas(Bundle bundle) {
        mId = bundle.getString("id");
        loadDatas();
    }

    /**
     * 加载数据
     */
    @Override
    protected void loadDatas() {
        url = String.format(Constants.URL_ITEM_DETAIL,mId);
        //从磁盘缓存中得到json字符串
//        String jsonCache = DiskLruCacheUtil.getJsonCache(url);
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
        DiskLruCacheUtil.putJsonCache(url,json);
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
        if(itemDetailBean.getData().getProduct_list() != null){
            //product_list 不为空，使用该布局适配器
            commonAdapter = new RVProductAdapter(getContext());
//            headerAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
//            HeadViewHomeItemDetail headViewHomeItemDetail = new HeadViewHomeItemDetail(getContext());
//            headViewHomeItemDetail.setData(itemDetailBean.getData());
//            headerAndFooterWrapper.addHeaderView(headViewHomeItemDetail);
//            //加载底部创建改文章日期以及收藏数的视图
//            FootViewTypeProduct footView = new FootViewTypeProduct(getContext());
//            footView.setDataBean(itemDetailBean.getData());
//            headerAndFooterWrapper.addFootView(footView);
//
////            initFootView();
//            footerView = new FooterView(getContext());
//            footerView.setID(mId);
//            headerAndFooterWrapper.addFootView(footerView);
//            rv_item_detail.setAdapter(headerAndFooterWrapper);
            initInnerHeadFoot(itemDetailBean);
            commonAdapter.addDataAll(itemDetailBean.getData().getProduct_list());
        }else if(itemDetailBean.getData().getPost_list() != null){
            //post_list使用此布局适配器
            commonAdapter = new RVPostListAdapter(getContext());
            RVPostListAdapter rvPostListAdapter = (RVPostListAdapter) commonAdapter;
            rvPostListAdapter.setFragmentManager(getChildFragmentManager());
            initInnerHeadFoot(itemDetailBean);
            commonAdapter.addDataAll(itemDetailBean.getData().getPost_list());
        }else if(itemDetailBean.getData().getContent_list() != null){
            //content_list 不为空时使用此布局适配器
            commonAdapter = new RVContentAdapter(getContext());

            initContent_listFootView(itemDetailBean);
            commonAdapter.addDataAll(itemDetailBean.getData().getContent_list());
        }else if(itemDetailBean.getData().getArticle_content() != null){
            //HTml文本 用webView展示页面
            commonAdapter = new CommonAdapter<ItemDetailBean>(getContext(),R.layout.item_detail_rv_webview) {
                @Override
                protected void convert(ViewHolder holder, ItemDetailBean o, int position) {
                    WebView webView = holder.getView(R.id.web_view);
                    webView.loadData(o.getData().getArticle_content(),"text/html; charset=UTF-8",null);
                }
            };
            initFootView();
            commonAdapter.addData(itemDetailBean);
        }
        rv_item_detail.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        animationDrawable.stop();
        iv_frame_anim.setVisibility(View.GONE);

    }
    //初始化动态添加布局的头部顶部
    private void initContent_listFootView(ItemDetailBean itemDetailBean) {
        headerAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
        View head_title = LayoutInflater.from(getContext()).inflate(R.layout.layout_content_head_title,null);
        TextView tvviews = (TextView) head_title.findViewById(R.id.tv_views);
        TextView tvnickname = (TextView) head_title.findViewById(R.id.tv_nickname);
        CircleImageView usericon = (CircleImageView) head_title.findViewById(R.id.user_icon);
        TextView tvtitle = (TextView) head_title.findViewById(R.id.tv_title);
        //赋值
        tvnickname.setText(itemDetailBean.getData().getUser().getNickname());
        tvviews.setText(itemDetailBean.getData().getViews());
        tvtitle.setText(itemDetailBean.getData().getTitle());
        Glide.with(getContext())
                .load(itemDetailBean.getData().getUser().getAvatar())
                .centerCrop()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(usericon);
        headerAndFooterWrapper.addHeaderView(head_title);
        //此布局的内部footview与其他三种布局不一样添加了发表文章的用户信息
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.layout_content_foot_view,null);
        TextView tvdate = (TextView) footView.findViewById(R.id.tv_date);
        TextView tv_nickname = (TextView) footView.findViewById(R.id.tv_nickname);
        TextView tvcollection = (TextView) footView.findViewById(R.id.tv_collection);
        CircleImageView user_icon = (CircleImageView) footView.findViewById(R.id.user_icon);
        tvdate.setText("创建于"+"  "+itemDetailBean.getData().getCreate_time_str());
        tv_nickname.setText(itemDetailBean.getData().getUser().getNickname());
        tvcollection.setText(itemDetailBean.getData().getLikes());
        CirImageViewUtils.loadCirImage(itemDetailBean.getData().getUser().getAvatar(),getContext(),user_icon);
        headerAndFooterWrapper.addFootView(footView);
        footerView = new FooterView(getContext());
        footerView.setID(mId);
        headerAndFooterWrapper.addFootView(footerView);
        rv_item_detail.setAdapter(headerAndFooterWrapper);
    }

    //初始化底部的评论视图
    private void initFootView() {
        headerAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
        footerView = new FooterView(getContext());
        footerView.setID(mId);
        headerAndFooterWrapper.addFootView(footerView);
        rv_item_detail.setAdapter(headerAndFooterWrapper);
    }

    //Product_list 和Post_list 两种布局添加同样的头部和尾部

    private void initInnerHeadFoot(ItemDetailBean itemDetailBean) {
        /**
         * 添加头部
         */
        headerAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
        HeadViewHomeItemDetail headview = new HeadViewHomeItemDetail(getContext());
        headview.setData(itemDetailBean.getData());
        headerAndFooterWrapper.addHeaderView(headview);

        /**
         * 加载底部创建改文章日期以及收藏数的视图
         */
        FootViewTypeProduct footView = new FootViewTypeProduct(getContext());
        footView.setDataBean(itemDetailBean.getData());
        headerAndFooterWrapper.addFootView(footView);

        /**
         * 所有四种类型布局都要添加的尾部（用户评论的视图）最后添加，所以不好调用initFootView();
         */
        footerView = new FooterView(getContext());
        footerView.setID(mId);
        headerAndFooterWrapper.addFootView(footerView);
        rv_item_detail.setAdapter(headerAndFooterWrapper);
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
