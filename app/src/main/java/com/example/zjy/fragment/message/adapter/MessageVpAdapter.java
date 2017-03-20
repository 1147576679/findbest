package com.example.zjy.fragment.message.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zjy.fragment.message.NotificationFragment;

import static com.example.zjy.util.Constants.MESSAGETAB;

/**
 * Created by zjy on 2017/3/13.
 */

public class MessageVpAdapter extends FragmentPagerAdapter {
    public MessageVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NotificationFragment.newInstance();
    }

    @Override
    public int getCount() {
        return MESSAGETAB.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MESSAGETAB[position];
    }
}
