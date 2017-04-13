package com.example.zjy.fragment.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.bantang.R;
import com.example.zjy.fragment.message.adapter.NotificationAdapter;
import com.example.zjy.fragment.message.bean.dto.NotificationVo;
import com.example.zjy.fragment.message.controller.NotificationController;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.rxhelper.RxCallBack;
import com.example.zjy.util.Constants;
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

public class NotificationFragment extends BaseFragment {

    @Bind(R.id.pull_to_refresh)
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    private PtrHeadView mPtrHeadView;

    @Bind(R.id.rv_notification)
    RecyclerView mRvNotification;
    private NotificationAdapter mNotificationAdapter;

    private NotificationController mNotificationController;

    private List<NotificationVo> mList = new ArrayList<>();

    private boolean isPullToRefresh;
    @Override
    public int getContentId() {
        return R.layout.fragment_message_notification;
    }

    @Override
    protected void init(View view) {
        mPtrHeadView = new PtrHeadView(getContext());
        mPtrClassicFrameLayout.setHeaderView(mPtrHeadView);
        mPtrClassicFrameLayout.addPtrUIHandler(mPtrHeadView);
        mPtrHeadView.setLastUpdateTimeRelateObject(this);
        mPtrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                isPullToRefresh = true;
                getData();
            }
        });
        mPtrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
             mPtrClassicFrameLayout.autoRefresh();
            }
        },100);
        mRvNotification.setHasFixedSize(true);
        mRvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        mNotificationController = new NotificationController();
        getData();
    }

    private void getData() {
        mNotificationController.notification(Constants.URL_NOTIFICAITON, new RxCallBack<List<NotificationVo>>() {
            @Override
            public void onNext(List<NotificationVo> notificationVos) {
                Log.i("tag", "onNext: "+notificationVos);
                if(!isPullToRefresh) {
                    mList = notificationVos;
                    mNotificationAdapter = new NotificationAdapter(getContext(), R.layout.item_message_rv_notification, mList);
                    mRvNotification.setAdapter(mNotificationAdapter);
                }else {
                    mList = notificationVos;
                    mNotificationAdapter = new NotificationAdapter(getContext(), R.layout.item_message_rv_notification, mList);
                    mRvNotification.setAdapter(mNotificationAdapter);
                    mPtrClassicFrameLayout.refreshComplete();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag", "onDestroy: ");
    }
}
