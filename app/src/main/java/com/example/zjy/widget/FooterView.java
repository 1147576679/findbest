package com.example.zjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.zjy.adapter.FooterLvAdapter;
import com.example.zjy.bantang.R;
import com.example.zjy.bean.UserCommentBean;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

/**
 * Created by zjy on 2016/12/27.
 */

public class FooterView extends RelativeLayout implements RetrofitUtil.DownListener {
    private String id;
    private ListView listView;
    private FooterLvAdapter footerLvAdapter;
    private Context context;
    public FooterView(Context context) {
        this(context, null);
    }

    public FooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        this.context = context;
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.footer_view_home_detail,this,true);
        listView = (ListView) findViewById(R.id.list_view);
        footerLvAdapter = new FooterLvAdapter(context);
        listView.setAdapter(footerLvAdapter);
    }
    public void setID(String id){
        this.id = id;
        loadData();
    }
    private void loadData() {
        String url = String.format(Constants.URL_ITEM_COMMENT,id);
        new RetrofitUtil(context).setDownListener(this).downJson(url,0x001);
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        return ParseJsonUtils.parseUserComment(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        UserCommentBean uerCommentBean = (UserCommentBean) object;
//        Log.i("tag", "userCommentbean: "+uerCommentBean.getData().size());
        footerLvAdapter.setDatas(uerCommentBean.getData());
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
