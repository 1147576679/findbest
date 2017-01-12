package com.example.zjy.bantang;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
//    @Bind(R.id.ll_dynamic_tag_add)
//    LinearLayout ll_dynamic_tag_add;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    private int rb_id = 0;
    //头部的标题
    @Bind(R.id.tv_tab_name)
    TextView tv_tab_name;

    @Bind(R.id.rv_search_product)
    RecyclerView rv_search_product;
    SearchProductRVAdapter searchProductRVAdapter;
    @Override
    public int getContentId() {
        return R.layout.activity_search_product;
    }

    @Override
    protected void init() {
        //搜索界面标签展示内容点击跳转传过来的id
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
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
        new RetrofitUtil(this).setDownListener(this).downJson(subClassUrl,0x001);
        new RetrofitUtil(this).setDownListener(this).downJson(productUrl,0x002);
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        DiskLruCacheUtil.putJsonCache(subClassUrl,json);
        DiskLruCacheUtil.putJsonCache(productUrl,json);
        switch (requestCode){
            case 0x001:
                return ParseJsonUtils.parseSearchSubClass(json);
            case 0x002:
                return ParseJsonUtils.parseSearchProduct(json);
            case 0x003:
                return ParseJsonUtils.parseSearchProduct(json);
        }
        return null;
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        switch (requestCode){
            case 0x001:
                RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(8,0,8,0);
                SearchSubClassBean searchSubClassBean = (SearchSubClassBean) object;
                tv_tab_name.setText(searchSubClassBean.getData().getName());
                final List<SearchSubClassBean.DataBean.ClassListBean> class_list =
                        searchSubClassBean.getData().getClass_list();
                //动态添加子标签
                //TODO 给TextView设置点击变色
//                TextView tv_all = new TextView(this);
//                tv_all.setText("全部");
//                tv_all.setSelected(true);
//                tv_all.setBackgroundResource(R.drawable.search_tag_text_bg);
//                tv_all.setGravity(Gravity.CENTER);
////                tv_all.setTextAppearance(this,R.style.search_product_text);
//                tv_all.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.hottag_btn_size));
//                tv_all.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        new RetrofitUtil(SearchProductActivity.this).setDownListener(SearchProductActivity.this).downJson(productUrl,0x002);
//                    }
//                });
//                Bitmap a = null;
                RadioButton radioButtonAll = new RadioButton(this);
                radioButtonAll.setId(rb_id);
                radioButtonAll.setText("全部");
                radioButtonAll.setChecked(true);
                radioButtonAll.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.hottag_btn_size));
                radioButtonAll.setTextAppearance(this,R.style.search_product_text);
                radioButtonAll.setButtonDrawable(android.R.color.transparent);
                radioButtonAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new RetrofitUtil(SearchProductActivity.this).setDownListener(SearchProductActivity.this).downJson(productUrl,0x002);
                    }
                });
                radioGroup.addView(radioButtonAll,layoutParams);
                for (int i = 0; i < class_list.size(); i++) {
                    RadioButton childRb = new RadioButton(this);
                    childRb.setId(i+1);
                    childRb.setText(class_list.get(i).getName());
                    childRb.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.hottag_btn_size));
                    childRb.setTextAppearance(this,R.style.search_product_text);
                    childRb.setGravity(Gravity.CENTER);
                    childRb.setButtonDrawable(android.R.color.transparent);
                    childRb.setBackgroundResource(R.drawable.search_tag_text_bg);
                    final int tag = i;
                    childRb.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.hottag_btn_size));
                    childRb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //根据子标签来搜索产品
                            id = class_list.get(tag).getId();
                            productUrl = String.format(Constants.URL_LISTSBYUBCLASS,id);
                            new RetrofitUtil(SearchProductActivity.this).setDownListener(SearchProductActivity.this).downJson(productUrl,0x003);
                        }
                    });
                    radioGroup.addView(childRb,layoutParams);
                }
                break;
            case 0x002:
                //根据搜索界面标签下的内容，加载产品列表返回的结果
                SearchProductBean searchProductBean = (SearchProductBean) object;
                List<SearchProductBean.DataBean> data = searchProductBean.getData();
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
