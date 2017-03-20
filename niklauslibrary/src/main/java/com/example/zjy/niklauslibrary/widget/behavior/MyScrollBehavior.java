package com.example.zjy.niklauslibrary.widget.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zjy on 2016/11/24.
 */
public class MyScrollBehavior extends CoordinatorLayout.Behavior {
    public MyScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 该方法返回true才能接受到滚动事件
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    /**
     * 滚动事件监听
     * @param coordinatorLayout
     * @param child 从动控件对象
     * @param target 主动控件的对象  这里是recyclerView
     * @param dxConsumed
     * @param dyConsumed  有效滚动偏移量，当改值为正，表示当前主动控件对象向上滑动（即用户在执行上滑的动作），负值反之，为0，说明对象滑动到顶部或者底部
     * @param dxUnconsumed
     * @param dyUnconsumed 无效的滚动偏移量，改值为正，表面对象滑动到底部仍然继续滑动，如果为负值反之，其余情况为0
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("tag", dyConsumed + " :onNestedScroll: "+dyUnconsumed);
        if(dyConsumed > 0){
            float y = child.getY();
            y = y -dyConsumed;
            if(Math.abs(y) >= child.getHeight()){
                y = - child.getHeight();
            }
            child.setY(y);
        }else if(dyConsumed == 0 && dyUnconsumed < 0){
            onInterceptTouchEvent(coordinatorLayout,child,null);
            float y = child.getY();
            y = y + Math.abs(dyUnconsumed);
            if(y >= 0){
                y =0;
            }
            child.setY(y);
        }
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.i("tag", "onNestedFling: "+velocityY+","+consumed);
        return true;
    }


}
