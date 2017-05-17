package com.example.zjy.bantang;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.rvhelper.divider.DividerGridItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class PostActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST = 100;
    @Bind(R.id.et_share)
    EditText mEtShare;
    @Bind(R.id.post_rv)
    RecyclerView mRecyclerView;
    private PostAdapter mPostAdapter;
    private List<Uri> datas;
    private static final int FLAG = 1;
    @Override
    public int getContentId() {
        return R.layout.activity_post;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_push})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_push:
                if (TextUtils.isEmpty(mEtShare.getText())) {
                    Toast.makeText(this, "使用心得不能为空哦~", Toast.LENGTH_SHORT).show();
                } else {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(PostActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    }, 2000);
                }
                break;
        }
    }



    @Override
    protected void init() {
        datas = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mPostAdapter = new PostAdapter(this,R.layout.item_post_rv,datas);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setAdapter(mPostAdapter);
        datas.add(Uri.parse("2123"));
        mPostAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(position == datas.size() - 1){
                    choosePhoto();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, final int position) {
                new AlertDialog.Builder(PostActivity.this)
                        .setTitle("确定删除图片吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                datas.remove(position);
                                mPostAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                return true;
            }
        });
    }

    public void photoPermission(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED){
                choosePhoto();
            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST);
            }
        }
    }

    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, FLAG);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if(requestCode == MY_PERMISSIONS_REQUEST){
           if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
               choosePhoto();
           }
       }
    }

    public class PostAdapter extends CommonAdapter<Uri>{


        public PostAdapter(Context context, int layoutId, List<Uri> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Uri uri, int position) {
            ImageView imageView = holder.getView(R.id.iv_post);
            if(position == getItemCount() - 1){
                imageView.setImageResource(R.drawable.add);
            }else {
                imageView.setImageURI(uri);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == FLAG){
            try {
                Uri uri = data.getData();
                Collections.reverse(datas);
                datas.add(uri);
                Collections.reverse(datas);
                mPostAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
