package com.example.zjy.fragment;

import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zjy.bantang.R;
import com.example.zjy.bean.event.HotTagEvent;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.niklauslibrary.widget.FlowLayout;
import com.example.zjy.util.Constants;
import com.example.zjy.util.ParseJsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zjy on 2017/4/19.
 */

public class HotTagFragment extends BaseFragment implements RetrofitUtil.DownListener {
    @Bind(R.id.flow_layout)
    FlowLayout mFlowLayout;
    @Override
    public int getContentId() {
        return R.layout.popupwindow_flowlayout;
    }

    @Override
    protected void init(View view) {
        loadDatas();
    }

    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(Constants.URL_HOTTAG,0x001);
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        return ParseJsonUtils.parseHotTag(json);
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        List<String> data = (List<String>) object;
        initHotTag(data);
    }

    @Override
    public void fail(Throwable throwable) {

    }

    //往流式布局添加热门标签
    private void initHotTag(final List<String> data) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 8, 8, 8);
        for (int i = 0; i < data.size(); i++) {
            final TextView textView = new TextView(getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView.setBackgroundResource(R.drawable.flag_01);
                textView.setText(data.get(i));
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        editText.setText(textView.getText());
                        HotTagEvent hotTagEvent = new HotTagEvent();
                        hotTagEvent.str = data.get(finalI);
                        EventBus.getDefault().post(hotTagEvent);
                    }
                });
                textView.setTextAppearance(getContext(), R.style.text_flag_01);
                textView.setTextColor(getResources().getColor(R.color.unselect_search));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(R.dimen.hottag_btn_size));
                mFlowLayout.addView(textView, params);
            }
        }
    }

    //将dimen资源文件中dp 转换为px
    public float getSize(int id) {
        return getResources().getDimensionPixelSize(id);
    }
}
