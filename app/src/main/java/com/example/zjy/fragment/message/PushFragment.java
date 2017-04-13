package com.example.zjy.fragment.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.bean.CallBack;
import com.example.zjy.fragment.message.adapter.PushRvAdapter;
import com.example.zjy.fragment.message.bean.PushVo;
import com.example.zjy.fragment.message.vm.PushVM;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.widget.PtrHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zjy on 2017/3/13.
 */

public class PushFragment extends BaseFragment {

    @Bind(R.id.rv_push)
    RecyclerView mRvPush;
    private PushRvAdapter mPushRvAdapter;
    private PushVM mPushVM;

    @Bind(R.id.pull_to_refresh)
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    private PtrHeadView mPtrHeadView;

    private List<PushVo> data = new ArrayList<>();

    @Override
    public int getContentId() {
        return R.layout.fragment_message_push;
    }


    @Override
    protected void init(View view) {
        mPushVM = new PushVM();
        mPtrHeadView = new PtrHeadView(getContext());
        mPtrClassicFrameLayout.setHeaderView(mPtrHeadView);
        mPtrClassicFrameLayout.addPtrUIHandler(mPtrHeadView);
        mPtrHeadView.setLastUpdateTimeRelateObject(this);
        mPtrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                mPushVM.refreshData(new CallBack<List<PushVo>>() {
                    @Override
                    public void callBack(List<PushVo> pushVos) {
                        data = pushVos;
                        mPushRvAdapter.notifyDataSetChanged();
                        frame.refreshComplete();
                    }
                });
            }
        });
        mPtrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrClassicFrameLayout.autoRefresh();
            }
        },100);
        mRvPush.setHasFixedSize(true);
        mRvPush.setLayoutManager(new LinearLayoutManager(getContext()));
        mPushVM.getData(new CallBack<List<PushVo>>() {
            @Override
            public void callBack(List<PushVo> pushVos) {
                data = pushVos;
                mPushRvAdapter = new PushRvAdapter(getContext(),R.layout.item_message_rv_push,data);
                mRvPush.setAdapter(mPushRvAdapter);
            }
        });
    }
}
