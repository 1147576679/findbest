package com.example.zjy.bantang;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zjy.fragment.HomeFragment;
import com.example.zjy.fragment.community.CommunityFragment;
import com.example.zjy.fragment.me.MeFragment;
import com.example.zjy.fragment.message.MessageFragment;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.ShareUtils;
import com.example.zjy.widget.PublishDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.frame_layout)
    FrameLayout frame_layout;
    @Bind(R.id.home_radio_group)
    RadioGroup home_radio_group;
    //记录退出应用当前时间
    private long end;
    //点击发表文章imageView的标志为
    private boolean flag;

    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private CommunityFragment communityFragment;
    private MeFragment meFragment;

    private int tag = 0;

    @Bind(R.id.frame_anim)
    ImageView frameAnim;
    private AnimationDrawable animationDrawable;


    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        homeFragment = new HomeFragment();
        messageFragment = new MessageFragment();
        communityFragment = new CommunityFragment();
        meFragment = new MeFragment();
        animationDrawable = (AnimationDrawable) frameAnim.getDrawable();
        animationDrawable.start();
    }

    @Override
    protected void bindListener() {
        //主页单选按钮的监听
        home_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        showFragment(R.id.frame_layout, homeFragment);
                        break;
                    case R.id.rb_navagitor:
                        showFragment(R.id.frame_layout, communityFragment);
                        break;
                    case R.id.rb_message:
                        if (!ShareUtils.getPutBoolean("isLogin")) {
                            tag = R.id.rb_message;
                            startActivity(RegisterActivity.newInstance(MainActivity.this));
                            overridePendingTransition(R.anim.activity_in_bottom, R.anim.activity_no_anim);
                        } else {
                            showFragment(R.id.frame_layout, messageFragment);
                        }
//                        showFragment(R.id.frame_layout, messageFragment);
                        break;
                    case R.id.rb_personal:
                        if (!ShareUtils.getPutBoolean("isLogin")) {
                            tag = R.id.rb_personal;
                            startActivity(RegisterActivity.newInstance(MainActivity.this));
                            overridePendingTransition(R.anim.activity_in_bottom, R.anim.activity_no_anim);
                        } else {
                            showFragment(R.id.frame_layout, meFragment);
                        }
                        break;
                }
            }
        });
        //默认选中第一个按钮显示主页
        home_radio_group.getChildAt(0).performClick();

    }

    //TODO
    //点击主页中的图标跳转到发表文章的界面
    @OnClick(R.id.iv_publish)
    public void onClick(ImageView imageView) {
//        Toast.makeText(this, "...", Toast.LENGTH_SHORT).show();
        PublishDialog publishDialog = new PublishDialog(this);
        publishDialog.show();
    }

    //监听退出应用事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - end < 2000) {
                finish();
                System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                end = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //关闭沉浸式状态栏
    @Override
    protected boolean isOpenTranslucent() {
        return false;
    }


    //接收用户注册成功信息执行相应的操作
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void registerSuccess(Boolean bool) {
//        Log.i("tag", "registerSuccess: 收到消息");
        if (bool) {
            if(tag == R.id.rb_message){
                showFragment(R.id.frame_layout,messageFragment);
            }else if (tag == R.id.rb_personal){
                showFragment(R.id.frame_layout,meFragment);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiver(String info) {
        if ("loginSuccess".equals(info)) {
            if(tag == R.id.rb_message){
                showFragment(R.id.frame_layout,messageFragment);
            }else if (tag == R.id.rb_personal){
                showFragment(R.id.frame_layout,meFragment);
            }
        } else if ("logoutSuccess".equals(info)) {
            home_radio_group.getChildAt(0).performClick();
        } else if ("cancel".equals(info)) {
            home_radio_group.getChildAt(0).performClick();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void frameAnimStop(Integer integer) {
        animationDrawable.stop();
        frameAnim.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        Log.i("tag", "onDestroy: 执行了");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
