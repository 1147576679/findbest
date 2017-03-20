package com.example.zjy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.adapter.SearchKeywordTopicRVAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.bantang.SearchKeywordTopicResultActivity;
import com.example.zjy.bean.SearchTopicBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerItemDecoration;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zjy on 2016/12/31.
 * 关键字搜索文章fragment
 */

public class SearchKeywordTopicFragment extends BaseFragment implements RetrofitUtil.DownListener {
    //关键字搜索字符串拼接
    private static final String topic = "topic";
    private String url;
    @Bind(R.id.rv_keyword_topic)
    RecyclerView rv_keyword_topic;
    private SearchKeywordTopicRVAdapter searchKeywordTopicRVAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventbus
        EventBus.getDefault().register(this);
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_search_keyword_topic;
    }

    //通过粘性eventBus接收搜索界面发过的用户输入的关键字
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getKeyword(String keyword){
//        Toast.makeText(getContext(), "SearchKeywordTopicFragment收到"+keyword, Toast.LENGTH_SHORT).show();
        Log.i("tag", "SearchKeywordTopicFragment收到: "+keyword);
        url = String.format(Constants.URL_KEYWORD_SEARCH_SINGLE_TOPIC_USER_POST,topic,keyword);
        loadDatas();
    }

    @Override
    protected void init(View view) {
        searchKeywordTopicRVAdapter = new SearchKeywordTopicRVAdapter(getContext());
        rv_keyword_topic.setAdapter(searchKeywordTopicRVAdapter);
        rv_keyword_topic.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        ));
//        rv_keyword_topic.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.VERTICAL,8, Color.BLACK));
        rv_keyword_topic.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x001);
    }

    @Override
    protected void bindListener() {
        searchKeywordTopicRVAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<SearchTopicBean>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, SearchTopicBean o, int position) {
                Intent intent  = new Intent(getActivity(), SearchKeywordTopicResultActivity.class);
                intent.putExtra("id",o.getId());
                Log.i("tag", "onItemClick: "+o.getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, SearchTopicBean o, int position) {
                return false;
            }
        });
    }

    @Override
    public Object paresJson(String json, int requestCode) {
//        DiskLruCacheUtil.putJsonCache(url,json);
        //解析关键字 文章搜索json
        return ParseJsonUtils.parseSearchTopic(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null){
            List<SearchTopicBean> datas = (List<SearchTopicBean>) object;
            //每次keyword的变化清除之前的数据
            searchKeywordTopicRVAdapter.clearData();
            searchKeywordTopicRVAdapter.addDataAll(datas);
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁eventBus
        EventBus.getDefault().removeStickyEvent(this);
    }
}
