package com.example.zjy.bantang;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zjy.dbhelper.DataBaseManage;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.ShareUtils;
import com.example.zjy.niklauslibrary.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.iv_back_arrow)
    ImageView ivBackArrow;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.user_phone)
    EditText userPhone;
    @Bind(R.id.user_password)
    EditText userPassword;


    @Override
    public int getContentId() {
        return R.layout.activity_login;
    }

    public static Intent newInstane(Context context){
        return new Intent(context,LoginActivity.class);
    }



    @OnClick(R.id.iv_back_arrow)
    public void ivBackClick(ImageView imageView){
        EventBus.getDefault().post("cancel");
        finish();
        //TODO 过场动画
    }

    @OnClick(R.id.btn_login)
    public void btnLogin(Button button){
        String user_phone = userPhone.getText().toString();
        String user_password = userPassword.getText().toString();
        String dbUserPassword = DataBaseManage.getUser(user_phone);
        if(TextUtils.isEmpty(user_phone) || TextUtils.isEmpty(user_password)){
//            Toast.makeText(this, "dadada", Toast.LENGTH_SHORT).show();
            ToastUtils.showToast(this,"手机号或者密码不能为空");
        }else {
            if(TextUtils.isEmpty(dbUserPassword)){
                ToastUtils.showToast(this,"用户不存在");
            }else if(!dbUserPassword.equals(user_password)){
                ToastUtils.showToast(this,"密码错误");
            }else {
                //TODO 根据查询数据库返回的结果执行相应后续步骤
                EventBus.getDefault().post("loginSuccess");
                ShareUtils.setPutBoolean("isLogin",true);
                finish();
            }
        }
        //TODO 过场动画
    }
}
