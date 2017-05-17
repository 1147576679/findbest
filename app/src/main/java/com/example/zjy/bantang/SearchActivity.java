package com.example.zjy.bantang;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.bean.event.HotTagEvent;
import com.example.zjy.bean.event.KeywordEvent;
import com.example.zjy.fragment.HotTagFragment;
import com.example.zjy.fragment.SearchCategoryFragment;
import com.example.zjy.fragment.SearchKeyWordFragment;
import com.example.zjy.niklauslibrary.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    //搜索框
    @Bind(R.id.edit_text)
    EditText editText;

    //占位用的
    @Bind(R.id.frame_layout)
    FrameLayout frame_layout;

    @Bind(R.id.iv_clear)
    ImageView mIvClear;

    private SearchCategoryFragment mSearchCategoryFragment;
    private SearchKeyWordFragment mSearchKeyWordFragment;
    private HotTagFragment mHotTagFragment;

    @Override
    public int getContentId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        //一开始就加载有分类的搜索fragment
        addSearchCategoryFragment();
        mSearchKeyWordFragment = new SearchKeyWordFragment();
        mHotTagFragment = new HotTagFragment();
        //预加载
        preInit();
    }

    private void preInit() {
        getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame_layout,
                                mSearchKeyWordFragment,
                                mSearchKeyWordFragment.getClass().getName())
                        .commit();
        getSupportFragmentManager().beginTransaction()
                .hide(mSearchKeyWordFragment)
                .commit();
    }

    //返回按钮
    @OnClick({R.id.iv_back_arrow,R.id.iv_clear})
    public void click(ImageView imageView) {
        switch (imageView.getId()){
            case R.id.iv_back_arrow:
                finish();
                break;
            case R.id.iv_clear:
                editText.setText("");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HotTagEvent hotTagEvent){
        Log.i("tag", "onEvent: 接收到消息");
        editText.setText(hotTagEvent.str);
        editText.setSelection(editText.getText().length());
        goSearch(hotTagEvent.str);
    }


    @Override
    protected void bindListener() {
        //关键字搜索监听加载数据
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    getSupportFragmentManager().beginTransaction()
//                            .hide(mSearchCategoryFragment)
//                            .add(R.id.frame_layout,
//                                    mHotTagFragment,
//                                    mHotTagFragment.getClass().getName())
//                            .addToBackStack(mHotTagFragment.getClass().getName())
//                            .commit();
                    showFragment(R.id.frame_layout,mHotTagFragment);
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(TextUtils.isEmpty(s)){
                    mIvClear.setVisibility(View.INVISIBLE);
                }else {
                    mIvClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /**
                 * 发布一个粘性事件，给搜索关键字的fragment把 keyword传过去
                 * 发布粘性的原因：因为关键字搜索fragment还没有创建
                 */

                EventBus.getDefault().postSticky(new KeywordEvent(s.toString()));
                if (TextUtils.isEmpty(s.toString())) {
//                    getSupportFragmentManager().beginTransaction()
//                            .hide(mSearchKeyWordFragment)
//                            .show(mHotTagFragment)
//                            .commit();
                    showFragment(R.id.frame_layout,mHotTagFragment);
                    mIvClear.setVisibility(View.INVISIBLE);
                }else {
                    mIvClear.setVisibility(View.VISIBLE);
                }
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String query = editText.getText().toString();
                    goSearch(query);
                    return true;
                }
                return false;
            }
        });
    }

    private void addSearchCategoryFragment() {
        mSearchCategoryFragment = new SearchCategoryFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.frame_layout,
//                        mSearchCategoryFragment,
//                        mSearchCategoryFragment.getClass().getName())
//                .show(mSearchCategoryFragment).commit();
        showFragment(R.id.frame_layout,mSearchCategoryFragment);
    }

    private void goSearch(String query) {
        if (!TextUtils.isEmpty(query)) {
//            if(getSupportFragmentManager().findFragmentByTag(mSearchKeyWordFragment.getClass().getName())== null) {
//                EventBus.getDefault().postSticky(query.toString());
//                getSupportFragmentManager().beginTransaction()
//                        .hide(mHotTagFragment)
//                        .add(R.id.frame_layout,
//                                mSearchKeyWordFragment,
//                                mSearchKeyWordFragment.getClass().getName())
//                        .addToBackStack(mSearchKeyWordFragment.getClass().getName())
//                        .commit();
//            }
            showFragment(R.id.frame_layout,mSearchKeyWordFragment);
            EventBus.getDefault().postSticky(new KeywordEvent(query));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
