package com.example.zjy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.UserBean;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerItemDecoration;
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
 * 关键字搜索用户fragment
 */

public class SearchKeywordUserFragment extends BaseFragment implements RetrofitUtil.DownListener {
    //拼接Constants.URL_KEYWORD_SEARCH_SINGLE_TOPIC_USER_POST 的字段
    private static final String user = "user";
    private String url;
    //展示用户的RecyclerView
    @Bind(R.id.rv_keyword_user)
    RecyclerView rv_user;
    //rv_user 适配器
    private CommonAdapter<UserBean> commonAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_search_keyword_user;
    }
    //通过粘性eventBus接收搜索界面发过的用户输入的关键字
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getKeyword(String keyword){
        Log.i("tag", "SearchKeywordUserFragment收到: "+keyword);
        url = String.format(Constants.URL_KEYWORD_SEARCH_SINGLE_TOPIC_USER_POST,user,keyword);
        loadDatas();
//        Toast.makeText(getContext(), "SearchKeywordUserFragment收到"+keyword, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(url,0x00);
    }

    @Override
    protected void init(View view) {
        commonAdapter = new CommonAdapter<UserBean>(getContext(),R.layout.item_search_keyword_user) {
            @Override
            protected void convert(ViewHolder holder, UserBean userBean, int position) {
                CircleImageView user_icon = holder.getView(R.id.user_icon);
                CirImageViewUtils.loadCirImage(
                        userBean.getAvatar(),
                        getContext(),user_icon);
                holder.setText(R.id.tv_nickname,userBean.getNickname());
            }
        };
        rv_user.setAdapter(commonAdapter);
        rv_user.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        rv_user.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseSearchUser(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null){
            List<UserBean> datas = (List<UserBean>) object;
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
