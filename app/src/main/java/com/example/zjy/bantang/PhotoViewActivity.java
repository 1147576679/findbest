package com.example.zjy.bantang;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.Bind;

public class PhotoViewActivity extends BaseActivity {
    @Bind(R.id.photo_view)
    PhotoView mPhotoView;

    private static final String URL = "url";
    @Override
    public int getContentId() {
        return R.layout.activity_photo_view;
    }
    public static Intent newInstance(Context context,String url){
        Intent intent = new Intent(context,PhotoViewActivity.class);
        intent.putExtra(URL,url);
        return intent;
    }
    @Override
    protected void init() {
        Intent intent = getIntent();
        String ivUrl = intent.getStringExtra(URL);
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mPhotoView.getLayoutParams();
        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
        Glide.with(this)
                .load(ivUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .placeholder(R.drawable.place_holder)
                .into(mPhotoView);
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        int width = resource.getWidth();
//                        float height = resource.getHeight();
//                        params.width = screenWidth;
//                        params.height = (int) (height / width * screenWidth);
//                        mPhotoView.setLayoutParams(params);
//                        mPhotoView.setImageBitmap(resource);
//                    }
//                });
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
    }
}
