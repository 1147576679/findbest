package com.example.zjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.zjy.bantang.AppContext;

/**
 * Created by zjy on 2017/3/17.
 */

public class PFTextView extends TextView {
    public PFTextView(Context context) {
        super(context);
        setTypeface(AppContext.getInstance().getTypeface());
    }

    public PFTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(AppContext.getInstance().getTypeface());

    }

    public PFTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(AppContext.getInstance().getTypeface());
    }
}
