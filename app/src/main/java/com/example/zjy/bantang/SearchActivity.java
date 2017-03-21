package com.example.zjy.bantang;

import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.zjy.fragment.SearchCategoryFragment;
import com.example.zjy.fragment.SearchKeyWordFragment;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.niklauslibrary.widget.FlowLayout;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements RetrofitUtil.DownListener {
    //搜索框
    @Bind(R.id.edit_text)
    EditText editText;
    /**
     * 点击搜索框弹出热门标签的弹出框
     */
    private PopupWindow popupWindow;
    private FlowLayout flowLayout;

    //占位用的
    @Bind(R.id.frame_layout)
    FrameLayout frame_layout;


    @Override
    public int getContentId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        //一开始就加载有分类的搜索fragment
        showFragment(R.id.frame_layout,new SearchCategoryFragment());
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        View contentView= LayoutInflater.from(this).inflate(R.layout.popupwindow_flowlayout,null);
        flowLayout = (FlowLayout) contentView.findViewById(R.id.flow_layout);
        popupWindow.setContentView(contentView);
    }

    @Override
    protected void loadDatas() {
        new RetrofitUtil(this).setDownListener(this).downJson(Constants.URL_HOTTAG,0x001);
    }

    //返回按钮
    @OnClick(R.id.iv_back_arrow)
    public void click(ImageView imageView){
        finish();
        //动画
//        overridePendingTransition();
    }
    @OnClick(R.id.edit_text)
    public void editClick(EditText editText){
        showFragment(R.id.frame_layout,new SearchKeyWordFragment());
    }

    @Override
    protected void bindListener() {
        //关键字搜索监听加载数据
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                //输入框监听到文本有变化将弹出框隐藏
//                popupWindow.dismiss();
                /**
                 * 发布一个粘性事件，给搜索关键字的fragment把 keyword传过去
                 * 发布粘性的原因：因为关键字搜索fragment还没有创建
                 */
                EventBus.getDefault().postSticky(s.toString());
                String url = String.format(Constants.URL_KEYWORD_SEARCH, s.toString());
                new RetrofitUtil(SearchActivity.this).setDownListener(SearchActivity.this).downJson(url,0x002);
                if(TextUtils.isEmpty(s.toString())){
                    showFragment(R.id.frame_layout,new SearchCategoryFragment());
                }
            }
        });
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        switch (requestCode){
            case 0x001:
                return ParseJsonUtils.parseHotTag(json);
            case 0x002:
                break;
        }
       return null;
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        switch (requestCode){
            case 0x001:
                if(object != null){
                    List<String> data = (List<String>) object;
                    initHotTag(data);
                }
                break;
            case 0x002:
                Log.i("tag", "keyword: "+editText.getText().toString());
                break;
        }

    }
    //往流式布局添加热门标签
    private void initHotTag(List<String> data) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8,8,8,8);
        for (int i = 0; i < data.size(); i++) {
            final TextView textView = new TextView(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView.setBackgroundResource(R.drawable.flag_01);
                textView.setText(data.get(i));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editText.setText(textView.getText());
                    }
                });
                textView.setTextAppearance(this,R.style.text_flag_01);
                textView.setTextColor(getResources().getColor(R.color.unselect_search));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.hottag_btn_size));
                flowLayout.addView(textView,params);
            }
        }
    }

    @Override
    public void fail(Throwable throwable) {

    }
    //将dimen资源文件中dp 转换为px
    public float getSize(int id){
        return getResources().getDimensionPixelSize(id);
    }
}
