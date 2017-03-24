package com.example.zjy.bantang;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zjy.dbhelper.DataBaseManage;
import com.example.zjy.dbhelper.User;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.ShareUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册界面
 * 注册成功直接显示用户资料界面和消息界面
 * 有帐号则跳转到登录界面
 */
public class RegisterActivity extends BaseActivity {
    @Bind(R.id.img_btn_close)
    ImageButton imgBtnClose;
    @Bind(R.id.user_phone)
    EditText userPhone;
    @Bind(R.id.user_password)
    EditText userPassword;
    @Bind(R.id.to_login)
    TextView toLogin;
    @Bind(R.id.btn_register)
    Button btnRegister;


    @Override
    public int getContentId() {
        return R.layout.activity_register;
    }


    @OnClick(R.id.img_btn_close)
    public void imgClick(ImageButton imageButton){
        EventBus.getDefault().post("cancel");
        finish();
//        overridePendingTransition();
    }

    @OnClick(R.id.btn_register)
    public void btnRegister(Button button){
        String user_phone =  userPhone.getText().toString();
        String user_password = userPassword.getText().toString();
        if(TextUtils.isEmpty(user_phone) || TextUtils.isEmpty(user_password)){
            Toast.makeText(this, "手机号或者密码不能为空", Toast.LENGTH_SHORT).show();
        }else if(!TextUtils.isEmpty(DataBaseManage.getUser(user_phone))){
            Toast.makeText(this, "用户已注册", Toast.LENGTH_SHORT).show();
        }else {
            User user = new User(user_phone,user_password);
            DataBaseManage.savaUser(user);
            ShareUtils.setPutBoolean("isLogin",true);
            EventBus.getDefault().post(true);
            finish();
        }
    }

    @OnClick(R.id.to_login)
    public void tvClick(TextView textView){
        startActivity(LoginActivity.newInstane(this));
        finish();
    }

    public static Intent newInstance(Context context){
        return new Intent(context,RegisterActivity.class);
}

}
