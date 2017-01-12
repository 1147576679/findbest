package com.example.zjy.bantang;

import android.content.Intent;
import android.widget.ImageView;

import com.example.zjy.niklauslibrary.base.BaseActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {
    @Bind(R.id.splash_image)
    ImageView splash_image;
    @Override
    public int getContentId() {
        return R.layout.activity_splash;
    }
    //欢迎页的跳转
    @Override
    protected void onPostResume() {
        super.onPostResume();
        splash_image.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
