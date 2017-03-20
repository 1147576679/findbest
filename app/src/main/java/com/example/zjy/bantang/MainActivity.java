package com.example.zjy.bantang;

import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zjy.fragment.HomeFragment;
import com.example.zjy.fragment.community.CommunityFragment;
import com.example.zjy.fragment.me.MeFragment;
import com.example.zjy.fragment.message.MessageFragment;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.widget.PublishDialog;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{

    @Bind(R.id.frame_layout)
    FrameLayout frame_layout;
    @Bind(R.id.home_radio_group)
    RadioGroup home_radio_group;
    //记录退出应用当前时间
    private long end;
    //点击发表文章imageView的标志为
    private boolean flag;



    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void bindListener() {
        //主页单选按钮的监听
        home_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        showFragment(R.id.frame_layout,new HomeFragment());
                        break;
                    case R.id.rb_message:
                        showFragment(R.id.frame_layout,new MessageFragment());
                        break;
                    case R.id.rb_navagitor:
                        showFragment(R.id.frame_layout,new CommunityFragment());
                        break;
                    case R.id.rb_personal:
                        showFragment(R.id.frame_layout,new MeFragment());
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
    public void onClick(ImageView imageView){
//        Toast.makeText(this, "...", Toast.LENGTH_SHORT).show();
        PublishDialog publishDialog = new PublishDialog(this);
        publishDialog.show();
    }


    //监听退出应用事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - end < 2000){
                finish();
                System.exit(0);
            }else{
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

}
