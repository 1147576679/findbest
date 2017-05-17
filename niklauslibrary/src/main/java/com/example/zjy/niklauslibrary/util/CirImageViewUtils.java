package com.example.zjy.niklauslibrary.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.zjy.niklauslibrary.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjy on 2016/12/27.
 */

public class CirImageViewUtils {
    public static void loadCirImage(String url,Context context,CircleImageView imageView){
        Glide.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.drawable.bg_personal_wish_item_drawable_day)
                .into(imageView);
    }
}
