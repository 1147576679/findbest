package com.example.zjy.niklauslibrary.util;

import android.util.Log;
import android.view.View;

/**
 * Created by wangont on 17/1/19.
 */

public class CommonUtils {
    private static String TAG = "CommonUtils";

    private static long lastClickTime;
    private static int lastClickViewId;
    private static final int KEY_PREVENT_TS = -20000;

    /**
     * 判断是否异常的双击（300毫秒内不能点击不同控件，500毫秒内不能点击相同控件）
     *
     * @return
     */
    public static boolean isFastDoubleClick(View v) {
        long now = System.currentTimeMillis();
        //检查是否被阻止点击
        if (v.getTag(KEY_PREVENT_TS) != null && v.getTag(KEY_PREVENT_TS) instanceof Long) {
            if ((Long) v.getTag(KEY_PREVENT_TS) > now) {
                Log.d(TAG, "Click prevented before " + v.getTag(KEY_PREVENT_TS));
                return true;
            }
        }
        long interval = now - lastClickTime;
        if (lastClickViewId == v.getId() && interval < 500) {
            Log.d(TAG, "isFastDoubleClick:same view");
            return true;
        } else if (interval < 300) {
            Log.d(TAG, "isFastDoubleClick:not same view");
            return true;
        }
        lastClickViewId = v.getId();
        lastClickTime = now;
        return false;
    }
}
