package com.example.zjy.niklauslibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zjy on 2017/3/24.
 */

public class ToastUtils {
    private static Toast toast;
    public static void showToast(Context context, String content){
        if(toast == null){
            toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }
}
