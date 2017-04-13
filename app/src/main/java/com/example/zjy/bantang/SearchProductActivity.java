package com.example.zjy.bantang;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zjy.adapter.SearchProductRVAdapter;
import com.example.zjy.bean.SearchProductBean;
import com.example.zjy.bean.SearchSubClassBean;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 搜索结果的展示
 */
public class SearchProductActivity extends BaseActivity implements RetrofitUtil.DownListener {
    //得到SearchSingleFragment中展示标签下内容的点击跳转传过来的id，拼接url加载数据
    private String id;
    private String subClassUrl;
    private String productUrl;
    @Bind(R.id.ll_dynamic_tag_add)
    LinearLayout ll_dynamic_tag_add;
//    @Bind(R.id.radio_group)
//    RadioGroup radioGroup;
    private int tv_id = 100;
    //头部的标题
    @Bind(R.id.tv_tab_name)
    TextView tv_tab_name;

    @Bind(R.id.rv_search_product)
    RecyclerView rv_search_product;
    SearchProductRVAdapter searchProductRVAdapter;
    //保存最开始的id,方便点击全部可以加载正确的数据
    private String startId;

    @Override
    public int getContentId() {
        return R.layout.activity_search_product;
    }

    @Override
    protected void init() {
        //搜索界面标签展示内容点击跳转传过来的id
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        startId = id;
        searchProductRVAdapter = new SearchProductRVAdapter(this);
        rv_search_product.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        rv_search_product.setAdapter(searchProductRVAdapter);
//        rv_search_product.addItemDecoration(new DividerGridItemDecoration(this));
    }

    @OnClick(R.id.iv_back_arrow)
    public void click(ImageView imageView){
        finish();
    }

    @Override
    protected void loadDatas() {
        //头部的子标签的url
        subClassUrl = String.format(Constants.URL_SUBCLASSINFO,id);
        //产品的展示url
        productUrl = String.format(Constants.URL_SEARCH_PRODUCT,id);

        // TODO: 2017/4/10  备选方案
//        String cache = DiskLruCacheUtil.getJsonCache(subClassUrl);
//        String cache = DiskLruCacheUtil.getJsonCache(productUrl);
        new RetrofitUtil(this).setDownListener(this).downJson(subClassUrl,0x001);
        new RetrofitUtil(this).setDownListener(this).downJson(productUrl,0x002);
    }

    @Override
    public Object paresJson(String json, int requestCode) {

        switch (requestCode){
            case 0x001:
                DiskLruCacheUtil.putJsonCache(subClassUrl,json);
                return ParseJsonUtils.parseSearchSubClass(json);
            case 0x002:
                DiskLruCacheUtil.putJsonCache(productUrl,json);
                return ParseJsonUtils.parseSearchProduct(json);
            case 0x003:
                DiskLruCacheUtil.putJsonCache(productUrl,json);
                return ParseJsonUtils.parseSearchProduct(json);
        }
        return null;
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        switch (requestCode){
            case 0x001:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(8,0,8,0);
                SearchSubClassBean searchSubClassBean = (SearchSubClassBean) object;
                tv_tab_name.setText(searchSubClassBean.getData().getName());
                final List<SearchSubClassBean.DataBean.ClassListBean> class_list =
                        searchSubClassBean.getData().getClass_list();
                //动态添加子标签
                //TODO 给TextView设置点击变色
                final TextView tv_all = new TextView(this);
                tv_all.setText("全部");
                tv_all.setBackgroundResource(R.color.tab_color);
                tv_all.setTextColor(Color.WHITE);
                tv_all.setGravity(Gravity.CENTER);
                tv_all.setPadding(14,6,14,6);
                tv_all.setTag(tv_id);
//                tv_all.setBackgroundResource(R.drawable.search_tag_text_bg);
//                tv_all.setTextAppearance(this,R.style.search_product_text);
                tv_all.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.hottag_btn_size));
                tv_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView1 = (TextView) ll_dynamic_tag_add.findViewWithTag(tv_id);
                        textView1.setTextColor(Color.parseColor("#9B9B9B"));
                        textView1.setBackgroundResource(R.color.home_search_background);
                        textView1.setTag(null);
                        tv_all.setBackgroundResource(R.color.tab_color);
                        tv_all.setTextColor(Color.WHITE);
                        tv_all.setTag(tv_id);
                        String format = String.format(Constants.URL_SEARCH_PRODUCT, startId);
                        new RetrofitUtil(SearchProductActivity.this).setDownListener(SearchProductActivity.this).downJson(format,0x002);
                    }
                });
                ll_dynamic_tag_add.addView(tv_all,layoutParams);
                for (int i = 0; i < class_list.size(); i++) {
                    final TextView textView = new TextView(this);
                    textView.setText(class_list.get(i).getName());
                    textView.setPadding(14,6,14,6);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.hottag_btn_size));
                    textView.setTextColor(Color.parseColor("#9B9B9B"));
                    textView.setBackgroundResource(R.color.home_search_background);
                    final int tag = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView textView1 = (TextView) ll_dynamic_tag_add.findViewWithTag(tv_id);
                            textView1.setTextColor(Color.parseColor("#9B9B9B"));
                            textView1.setBackgroundResource(R.color.home_search_background);
                            textView1.setTag(null);
                            textView.setBackgroundResource(R.color.tab_color);
                            textView.setTextColor(Color.WHITE);
                            textView.setTag(tv_id);
                            id = class_list.get(tag).getId();
                            productUrl = String.format(Constants.URL_LISTSBYUBCLASS,id);
                            // TODO: 2017/4/10 备选方案
//                            String cache = DiskLruCacheUtil.getJsonCache(productUrl);
                            new RetrofitUtil(SearchProductActivity.this).setDownListener(SearchProductActivity.this).downJson(productUrl,0x003);
                        }
                    });
                    ll_dynamic_tag_add.addView(textView,layoutParams);
                }
                break;
            case 0x002:
                //根据搜索界面标签下的内容，加载产品列表返回的结果
                SearchProductBean searchProductBean = (SearchProductBean) object;
                List<SearchProductBean.DataBean> data = searchProductBean.getData();
                Log.i("tag", "downSucc: 执行了"+data);
                searchProductRVAdapter.clearData();
                searchProductRVAdapter.addDataAll(data);
                break;
            case 0x003:
                //子标签加载产品列表返回的结果
                searchProductRVAdapter.clearData();
                SearchProductBean searchProductBean1 = (SearchProductBean) object;
                List<SearchProductBean.DataBean> data1 = searchProductBean1.getData();
                searchProductRVAdapter.addDataAll(data1);
                break;
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
