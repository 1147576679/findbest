package com.example.zjy.bantang;

import android.widget.Button;
import android.widget.ImageView;

import com.example.zjy.niklauslibrary.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.iv_back_arrow)
    ImageView ivBackArrow;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Override
    public int getContentId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.iv_back_arrow)
    public void ivBackClick(ImageView imageView){
        finish();
        //TODO 过场动画
    }

    @OnClick(R.id.btn_login)
    public void btnLogin(Button button){
        //TODO 根据查询数据库返回的结果执行相应后续步骤
        finish();
        //TODO 过场动画
    }
}
