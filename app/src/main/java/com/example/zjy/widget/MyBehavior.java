package com.example.zjy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.example.zjy.bantang.R;

/**
 * Created by zjy on 2016/11/24.
 */
public class MyBehavior extends CoordinatorLayout.Behavior {
    private int viewid;
    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.belowbottom);
        viewid = typedArray.getResourceId(R.styleable.belowbottom_viewid,-1);
        typedArray.recycle();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == viewid ;

    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY() + dependency.getHeight());
        return true;
    }
}
