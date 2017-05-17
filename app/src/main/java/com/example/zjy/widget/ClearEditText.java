package com.example.zjy.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zjy.bantang.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zjy on 2017/4/19.
 */

public class ClearEditText extends LinearLayout {
    @Bind(R.id.et_input)
    EditText etInput;
    @Bind(R.id.iv_clear)
    ImageView ivClear;
    @Bind(R.id.rl_clear)
    RelativeLayout rlClear;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initEvent();
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_clear_edit_text, this, true);
        ButterKnife.bind(this);
    }

    private void initEvent() {
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (TextUtils.isEmpty(s)) {
                    ivClear.setVisibility(GONE);
                } else {
                    ivClear.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    ivClear.setVisibility(GONE);
                } else {
                    ivClear.setVisibility(VISIBLE);
                }
            }
        });
    }

    @OnClick(R.id.rl_clear)
    public void onClick() {
        etInput.setText("");
    }

    public EditText getEditTextView(){
        return etInput;
    }

    public String getText(){
        return etInput.getText().toString();
    }

    public void setText(String s){
        etInput.setText(s);
    }
}
