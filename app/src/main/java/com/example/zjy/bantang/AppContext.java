package com.example.zjy.bantang;

import android.app.Application;

import com.example.zjy.niklauslibrary.util.DiskLruCacheUtil;

/**
 * Created by zjy on 2017/1/3.
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DiskLruCacheUtil.init(this);
    }
}
