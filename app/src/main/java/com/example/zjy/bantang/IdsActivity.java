package com.example.zjy.bantang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.bean.IdsBean;
import com.example.zjy.fragment.community.CommunityTopicDetailActivity;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.rvhelper.adapter.CommonAdapter;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerItemDecoration;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class IdsActivity extends BaseActivity implements RetrofitUtil.DownListener {

//    @Bind(R.id.iv_frame_anim)
//    ImageView iv_frame_anim;
    @Bind(R.id.tv_ids_title)
    TextView tv_ids_title;
    @Bind(R.id.rv_ids)
    RecyclerView rv_ids;

    //重用关键字搜索文章的RecyclerView适配器
    private CommonAdapter rv_ids_adpter;
    //拼接url
    private String extend;
    //拼接成功的url
    private String url;

    //标题
    private String title;


    @Override
    public int getContentId() {
        return R.layout.activity_ids;
    }

    @OnClick(R.id.iv_back_arrow)
    public void backClick(ImageView iv){
        finish();
        //退出动画
    }

    public static Intent newInstance(Context context,String extend,String title){
        Intent intent = new Intent(context,IdsActivity.class);
        intent.putExtra("extend",extend);
        intent.putExtra("title",title);
        return intent;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        extend = intent.getStringExtra("extend");
        title = intent.getStringExtra("title");
//        iv_frame_anim.setImageResource(R.drawable.frame_anim);
        tv_ids_title.setText(title);
        rv_ids_adpter = new CommonAdapter<IdsBean>(this,R.layout.item_search_keyword_topic) {
            @Override
            protected void convert(ViewHolder holder, final IdsBean idsBean, int position) {
                holder.setText(R.id.tv_title,idsBean.getTitle())
                        .setText(R.id.tv_views,idsBean.getViews())
                        .setText(R.id.tv_comments,idsBean.getComments())
                        .setText(R.id.tv_username,idsBean.getUser().getNickname())
                        .setText(R.id.tv_create_time,idsBean.getCreate_time_str())
                        .setImageUrl(R.id.iv_search_keyword,idsBean.getPic());
                final TextView tv_views = holder.getView(R.id.tv_views);
                //浏览和评论数如果为null 则赋值0
                tv_views.post(new Runnable() {
                    @Override
                    public void run() {
                        if(TextUtils.isEmpty(idsBean.getViews())){
                            tv_views.setText("0");
                        }
                    }
                });
                final TextView tv_comment = holder.getView(R.id.tv_comments);
                tv_views.post(new Runnable() {
                    @Override
                    public void run() {
                        if(TextUtils.isEmpty(idsBean.getComments())){
                            tv_comment.setText("0");
                        }
                    }
                });
                //给用户圆形imageview加载图片
                CircleImageView circleImageView = holder.getView(R.id.user_icon);
                CirImageViewUtils.loadCirImage(idsBean.getUser().getAvatar(),IdsActivity.this,circleImageView);
            }
        };
        rv_ids.setLayoutManager(new LinearLayoutManager(this));
        rv_ids.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        rv_ids.setAdapter(rv_ids_adpter);
    }

    @Override
    protected void loadDatas() {
        url = String.format(Constants.URL_BANNER_DETAIL,extend);
        new RetrofitUtil(this).setDownListener(this).downJson(url,0x001);
    }
    //点击事件的监听
    @Override
    protected void bindListener() {
        rv_ids_adpter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<IdsBean>() {
            //点击RecyclerView的item跳转
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, IdsBean o, int position) {
//                Intent intent  = new Intent(IdsActivity.this, Comm.class);
//                intent.putExtra("id",o.getId());
//                Log.i("tag", "onItemClick: "+o.getId());
                startActivity(CommunityTopicDetailActivity.newInstance(IdsActivity.this,o.getId()));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, IdsBean o, int position) {
                return false;
            }
        });
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(url,json);
        return ParseJsonUtils.parseIds(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null) {
            List<IdsBean> datas = (List<IdsBean>) object;
            Log.i("tag", "IdsBean: "+datas);
            rv_ids_adpter.addDataAll(datas);
//            iv_frame_anim.clearAnimation();
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
