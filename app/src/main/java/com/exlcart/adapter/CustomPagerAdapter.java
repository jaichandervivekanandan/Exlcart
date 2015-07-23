package com.exlcart.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.exlcart.PageFragment;
import com.exlcart.fragment.HomeFragment;
import com.exlcart.fragment.SearchFragment;

import java.util.ArrayList;

/**
 * Created by Saravanan on 7/4/2015.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    ArrayList<Integer> mMenuIconList;
    private ArrayList<Fragment> mFragmentArrayList;

    public CustomPagerAdapter(FragmentManager fragmentManager, ArrayList<Integer> iconList,ArrayList<Fragment> list) {
        super(fragmentManager);
        this.mMenuIconList = iconList;
        this.mFragmentArrayList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentArrayList.size();
    }

    @Override
    public int getPageIconResId(int i) {
        return mMenuIconList.get(i);
    }
}
