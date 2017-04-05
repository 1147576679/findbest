package com.example.zjy.fragment.community;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.adapter.RVContentAdapter;
import com.example.zjy.adapter.RVPostListAdapter;
import com.example.zjy.adapter.RVProductAdapter;
import com.example.zjy.bantang.PhotoViewActivity;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.fragment.community.vm.CommunityTopicVM;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.HeaderAndFooterWrapper;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.util.ToastUtils;
import com.example.zjy.widget.FootViewTypeProduct;
import com.example.zjy.widget.FooterView;
import com.example.zjy.widget.HeadViewHomeItemDetail;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityTopicDetailActivity extends BaseActivity {
    private static final String ID = "id";
    @Bind(R.id.topic_rv_detail)
    RecyclerView mTopicRvDetail;

    @Bind(R.id.iv_back_arrow)
    ImageView mIvBackArrow;

    private CommunityTopicVM mCommunityTopicVM;

    private CommonAdapter mCommonAdapter;
    private FooterView mFooterView;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private String mId;

    @Override
    public int getContentId() {
        return R.layout.activity_community_topic_detail;
    }

    public static Intent newInstance(Context context,String id){
        Intent intent = new Intent(context,CommunityTopicDetailActivity.class);
        intent.putExtra(ID,id);
        return intent;
    }

    @OnClick(R.id.iv_back_arrow)
    public void ivClick(View view){
        finish();
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        mId = intent.getStringExtra(ID);
        mCommunityTopicVM = new CommunityTopicVM();
        mTopicRvDetail.setHasFixedSize(true);
        mTopicRvDetail.setLayoutManager(new LinearLayoutManager(CommunityTopicDetailActivity.this));
        mCommunityTopicVM.getData(mId, new CommunityTopicVM.CallBack() {
            @Override
            public void callBack(ItemDetailBean data) {
//                mRVItemDetailAdapter.addData(data);
                ItemDetailBean itemDetailBean = data;
                if(itemDetailBean.getData().getProduct_list() != null){
                    //product_list 不为空，使用该布局适配器
                    mCommonAdapter = new RVProductAdapter(CommunityTopicDetailActivity.this);
                    initInnerHeadFoot(itemDetailBean);
                    mCommonAdapter.addDataAll(itemDetailBean.getData().getProduct_list());
                }else if(itemDetailBean.getData().getPost_list() != null){
                    //post_list使用此布局适配器
                    mCommonAdapter = new RVPostListAdapter(CommunityTopicDetailActivity.this);
                    RVPostListAdapter rvPostListAdapter = (RVPostListAdapter) mCommonAdapter;
                    rvPostListAdapter.setFragmentManager(getSupportFragmentManager());
                    initInnerHeadFoot(itemDetailBean);
                    mCommonAdapter.addDataAll(itemDetailBean.getData().getPost_list());
                }else if(itemDetailBean.getData().getContent_list() != null){
                    //content_list 不为空时使用此布局适配器
                    mCommonAdapter = new RVContentAdapter(CommunityTopicDetailActivity.this, new RVContentAdapter.ClickImageListener() {
                        @Override
                        public void onClick(String url, int position) {
                            Intent intent = new Intent(CommunityTopicDetailActivity.this, PhotoViewActivity.class);
                            intent.putExtra("picUrl",url);
                            Log.i("tag", "onClick:点击了图片 "+ position);
                            CommunityTopicDetailActivity.this.startActivity(intent);
                        }
                    });
                    initContent_listFootView(itemDetailBean);
                    mCommonAdapter.addDataAll(itemDetailBean.getData().getContent_list());
                }else if(itemDetailBean.getData().getArticle_content() != null){
                    //HTml文本 用webView展示页面
                    mCommonAdapter = new CommonAdapter<ItemDetailBean>(CommunityTopicDetailActivity.this,R.layout.item_detail_rv_webview) {
                        @Override
                        protected void convert(ViewHolder holder, ItemDetailBean o, int position) {
                            WebView webView = holder.getView(R.id.web_view);
                            webView.loadData(o.getData().getArticle_content(),"text/html; charset=UTF-8",null);
                        }
                    };
                    initFootView();
                    mCommonAdapter.addData(itemDetailBean);
                }
                mTopicRvDetail.setLayoutManager(new LinearLayoutManager(CommunityTopicDetailActivity.this,LinearLayoutManager.VERTICAL,false));
            }
        });
    }

    //初始化动态添加布局的头部顶部
    private void initContent_listFootView(ItemDetailBean itemDetailBean) {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
        View head_title = LayoutInflater.from(CommunityTopicDetailActivity.this).inflate(R.layout.layout_content_head_title,null);
        TextView tvviews = (TextView) head_title.findViewById(R.id.tv_views);
        TextView tvnickname = (TextView) head_title.findViewById(R.id.tv_nickname);
        CircleImageView usericon = (CircleImageView) head_title.findViewById(R.id.user_icon);
        TextView tvtitle = (TextView) head_title.findViewById(R.id.tv_title);
        //赋值
        tvnickname.setText(itemDetailBean.getData().getUser().getNickname());
        tvviews.setText(itemDetailBean.getData().getViews());
        tvtitle.setText(itemDetailBean.getData().getTitle());
        Glide.with(CommunityTopicDetailActivity.this)
                .load(itemDetailBean.getData().getUser().getAvatar())
                .centerCrop()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(usericon);
        mHeaderAndFooterWrapper.addHeaderView(head_title);
        //此布局的内部footview与其他三种布局不一样添加了发表文章的用户信息
        View footView = LayoutInflater.from(CommunityTopicDetailActivity.this).inflate(R.layout.layout_content_foot_view,null);
        TextView tvdate = (TextView) footView.findViewById(R.id.tv_date);
        TextView tv_nickname = (TextView) footView.findViewById(R.id.tv_nickname);
        TextView tvcollection = (TextView) footView.findViewById(R.id.tv_collection);
        CircleImageView user_icon = (CircleImageView) footView.findViewById(R.id.user_icon);
        final CheckBox checkBoxFoucs = (CheckBox) footView.findViewById(R.id.checkbox_focus);
        checkBoxFoucs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxFoucs.setChecked(true);
                    checkBoxFoucs.setText("已关注");
                    ToastUtils.showToast(CommunityTopicDetailActivity.this,"关注成功");
                }else {
                    checkBoxFoucs.setChecked(false);
                    checkBoxFoucs.setText("+关注");
                }
            }
        });
        tvdate.setText("创建于"+"  "+itemDetailBean.getData().getCreate_time_str());
        tv_nickname.setText(itemDetailBean.getData().getUser().getNickname());
        tvcollection.setText(itemDetailBean.getData().getLikes());
        CirImageViewUtils.loadCirImage(itemDetailBean.getData().getUser().getAvatar(),CommunityTopicDetailActivity.this,user_icon);
        mHeaderAndFooterWrapper.addFootView(footView);
        mFooterView = new FooterView(CommunityTopicDetailActivity.this);
        mFooterView.setID(mId);
        mHeaderAndFooterWrapper.addFootView(mFooterView);
        mTopicRvDetail.setAdapter(mHeaderAndFooterWrapper);
    }

    //初始化底部的评论视图
    private void initFootView() {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
        mFooterView = new FooterView(CommunityTopicDetailActivity.this);
        mFooterView.setID(mId);
        mHeaderAndFooterWrapper.addFootView(mFooterView);
        mTopicRvDetail.setAdapter(mHeaderAndFooterWrapper);
    }

    //Product_list 和Post_list 两种布局添加同样的头部和尾部

    private void initInnerHeadFoot(ItemDetailBean itemDetailBean) {
        /**
         * 添加头部
         */
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
        HeadViewHomeItemDetail headview = new HeadViewHomeItemDetail(CommunityTopicDetailActivity.this);
        headview.setData(itemDetailBean.getData());
        mHeaderAndFooterWrapper.addHeaderView(headview);

        /**
         * 加载底部创建改文章日期以及收藏数的视图
         */
        FootViewTypeProduct footView = new FootViewTypeProduct(CommunityTopicDetailActivity.this);
        footView.setDataBean(itemDetailBean.getData());
        mHeaderAndFooterWrapper.addFootView(footView);

        /**
         * 所有四种类型布局都要添加的尾部（用户评论的视图）最后添加，所以不好调用initFootView();
         */
        mFooterView = new FooterView(CommunityTopicDetailActivity.this);
        mFooterView.setID(mId);
        mHeaderAndFooterWrapper.addFootView(mFooterView);
        mTopicRvDetail.setAdapter(mHeaderAndFooterWrapper);
    }




    @Override
    protected boolean isOpenTranslucent() {
        return false;
    }
}
