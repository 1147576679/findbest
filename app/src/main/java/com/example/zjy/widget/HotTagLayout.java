//package com.example.zjy.widget;
//
//import android.content.Context;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.widget.Button;
//
//import com.example.zjy.bantang.R;
//import com.example.zjy.niklauslibrary.util.RetrofitUtil;
//import com.example.zjy.niklauslibrary.widget.FlowLayout;
//import com.example.zjy.util.Constants;
//import com.example.zjy.util.ParseJsonUtils;
//
//import java.util.List;
//
///**
// * Created by zjy on 2016/12/29.
// */
//
//public class HotTagLayout extends FlowLayout implements RetrofitUtil.DownListener {
//    public HotTagLayout(Context context) {
//        this(context, null);
//    }
//
//    public HotTagLayout(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public HotTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        loadData();
//    }
//    //初始化热门标签
//    private void init(List<String> data) {
//        for (int i = 0; i < data.size(); i++) {
//            Button button = new Button(getContext());
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                button.setBackground(getResources().getDrawable(R.drawable.btn_hottag_bg));
//                button.setText(data.get(i));
//            }
//        }
//    }
//
//    public void loadData(){
//        new RetrofitUtil(getContext()).setDownListener(this).downJson(Constants.URL_HOTTAG,0x001);
//    }
//
//    @Override
//    public Object paresJson(String json, int requestCode) {
//        return ParseJsonUtils.parseHotTag(json);
//    }
//
//    @Override
//    public void downSucc(Object object, int requestCode) {
//        if(object != null){
//            List<String> data = (List<String>) object;
//            init(data);
//        }
//    }
//
//    @Override
//    public void fail(Throwable throwable) {
//
//    }
//}
