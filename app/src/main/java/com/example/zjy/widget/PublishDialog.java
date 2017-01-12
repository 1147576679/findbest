package com.example.zjy.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.zjy.bantang.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by zjy on 2017/1/5.
 */

public class PublishDialog extends Dialog implements View.OnClickListener {
    private android.widget.ImageView ivarticle;
    private android.widget.ImageView ivpost;
    private android.widget.ImageView ivpublish;


    public PublishDialog(Context context) {
        super(context, R.style.dialog_style);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        EventBus.getDefault().register(this);
        this.ivpublish = (ImageView) findViewById(R.id.iv_publish);
        this.ivpost = (ImageView) findViewById(R.id.iv_post);
        this.ivarticle = (ImageView) findViewById(R.id.iv_article);
        ivpublish.setOnClickListener(this);
        //旋转动画
        rotateAnimation(ivpublish);
//        ivpost.animate()
//                .scaleXBy(0)
//                .scaleX(1.2f)
//                .scaleYBy(0)
//                .scaleY(1)
//                .translationYBy(200)
//                .translationY(0)
//                .setDuration(500)
//                .start();
//        ivarticle.animate()
//                .scaleXBy(0)
//                .scaleX(1)
//                .scaleYBy(0)
//                .scaleY(1)
//                .translationYBy(300)
//                .translationY(200)
//                .setDuration(500)
//                .start();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_publish:
                ivpublish.animate()
                        .rotation(-45)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                PublishDialog.this.dismiss();
                            }
                        }).start();

                ivpost.animate()
                        .scaleXBy(1)
                        .scaleX(0)
                        .scaleYBy(1)
                        .scaleY(0)
                        .translationYBy(0)
                        .translationY(100)
                        .setDuration(500)
                        .start();
                ivarticle.animate()
                        .scaleXBy(1)
                        .scaleX(0)
                        .scaleYBy(1)
                        .scaleY(0)
                        .translationYBy(0)
                        .translationY(100)
                        .setDuration(500)
                        .start();
                break;
        }
//        this.dismiss();
    }
    @Subscribe
    public void receiver(String str){

    }
    //发布文章的按钮旋转动画
    public void rotateAnimation(View view){
        RotateAnimation rotateAnimation =  new RotateAnimation(0,45, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        view.startAnimation(rotateAnimation);
    }

    //发布文章的按钮结束旋转动画
    public void reseverRotate(View view){
//        RotateAnimation reverseRotate = new RotateAnimation(45,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        reverseRotate.setInterpolator(new LinearInterpolator());
//        reverseRotate.setDuration(5000);
//        reverseRotate.setFillAfter(true);
//        view.startAnimation(reverseRotate);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().removeStickyEvent(this);
    }

}
