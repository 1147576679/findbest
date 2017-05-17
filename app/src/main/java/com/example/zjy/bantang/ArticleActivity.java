package com.example.zjy.bantang;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zjy.niklauslibrary.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class ArticleActivity extends BaseActivity {
    @Bind(R.id.et_title)
    EditText mEtTitle;
    @Bind(R.id.et_content)
    EditText mEtContent;

    @Override
    public int getContentId() {
        return R.layout.activity_article;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_push})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_push:
                if (TextUtils.isEmpty(mEtTitle.getText()) || TextUtils.isEmpty(mEtContent.getText())) {
                    if (TextUtils.isEmpty(mEtTitle.getText())) {
                        Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "正文不能为空", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ArticleActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    }, 2000);
                }
                break;
        }
    }
}
