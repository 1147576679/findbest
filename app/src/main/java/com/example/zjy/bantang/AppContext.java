package com.example.zjy.bantang;

import android.app.Application;
import android.graphics.Typeface;

import com.example.zjy.dbhelper.DataBaseManage;
import com.example.zjy.niklauslibrary.util.ShareUtils;
import com.squareup.leakcanary.LeakCanary;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by zjy on 2017/1/3.
 */

public class AppContext extends Application {
    private Typeface typeface;
    private static AppContext instance;
    @Override
    public void onCreate() {
        super.onCreate();
//        DiskLruCacheUtil.init(this);

        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);


        //初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
        //初始化第三方分享
        ShareSDK.initSDK(getApplicationContext());
        instance = (AppContext) getApplicationContext();
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/PingFangRegular.ttf");

        //初始化数据库
        DataBaseManage.newInstance(getApplicationContext());

        //初始化SharedPreferences
        ShareUtils.init(getApplicationContext());
    }

    public static AppContext getInstance(){
        return instance;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}
