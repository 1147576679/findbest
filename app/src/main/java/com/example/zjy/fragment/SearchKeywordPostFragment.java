package com.example.zjy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.SearchPostBean;
import com.example.zjy.bean.event.KeywordEvent;
import com.example.zjy.fragment.community.CommunityPostDetailActivity;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2016/12/31.
 * 关键字搜索晒单fragment
 */

public class SearchKeywordPostFragment extends BaseFragment implements RetrofitUtil.DownListener {
    private static final String post = "post";
    private String url;

    @Bind(R.id.rv_keyword_post)
    RecyclerView rv_post;
    private CommonAdapter commonAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_search_keyword_post;
    }

    //通过粘性eventBus接收搜索界面发过的用户输入的关键字
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getKeyword(String keyword){
//        Log.i("tag", "SearchKeywordPostFragment收到: "+keyword);
//        url = String.format(Constants.URL_KEYWORD_SEARCH_SINGLE_TOPIC_USER_POST,post,keyword);
//        // TODO: 2017/4/10  备选方案
////        String cache = DiskLruCacheUtil.getJsonCache(url);
//        loadDatas();
//    }

    //通过粘性eventBus接收搜索界面发过的用户输入的关键字
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getKeyword(KeywordEvent keywordEvent){
        Log.i("tag", "SearchKeywordSingleFragment收到: "+keywordEvent.keyword);
        url = String.format(Constants.URL_KEYWORD_SEARCH_SINGLE_TOPIC_USER_POST,post,keywordEvent.keyword);
        loadDatas();
    }
    //加载关键字数据
    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x001);
    }

    //初始化控件视图
    @Override
    protected void init(View view) {
        commonAdapter = new CommonAdapter<SearchPostBean>(getContext(),R.layout.item_search_keyword_post) {

            @Override
            protected void convert(ViewHolder holder, SearchPostBean searchPostBean, int position) {
                CircleImageView user_icon = holder.getView(R.id.user_icon);
                CirImageViewUtils.loadCirImage(
                        searchPostBean.getUser().getAvatar(),
                        getContext(),user_icon);
                holder.setText(R.id.tv_nickname,searchPostBean.getUser().getNickname())
                        .setText(R.id.tv_likes,searchPostBean.getDynamic().getLikes())
                        .setText(R.id.tv_post_content,searchPostBean.getContent())
                        .setImageUrl(R.id.iv_search_product,searchPostBean.getMiddle_pic_url());
            }
        };
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<SearchPostBean>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, SearchPostBean o, int position) {
                startActivity(CommunityPostDetailActivity.newInstance(getContext(),o.getId()));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, SearchPostBean o, int position) {
                return false;
            }
        });
        rv_post.setAdapter(commonAdapter);
        rv_post.setLayoutManager(new GridLayoutManager(getContext(),2));
    }



    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseSearchPost(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null){
            List<SearchPostBean> datas = (List<SearchPostBean>) object;
            commonAdapter.clearData();
            commonAdapter.addDataAll(datas);
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);
    }
}
