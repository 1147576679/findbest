package com.example.zjy.bantang;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.ShareUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {
    @Bind(R.id.tv_logout)
    TextView tvLogout;
    @Override
    public int getContentId() {
        return R.layout.activity_settings;
    }

    public static Intent newInstance(Context context){
        return new Intent(context,SettingsActivity.class);
    }

    @OnClick({R.id.tv_logout,R.id.ll_clear_cache})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_logout:
                ShareUtils.setPutBoolean("isLogin",false);
                EventBus.getDefault().post("logoutSuccess");
                finish();
                break;
            case R.id.ll_clear_cache:
                break;
        }
    }

}
