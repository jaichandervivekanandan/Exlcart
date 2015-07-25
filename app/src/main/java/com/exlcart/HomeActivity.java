package com.exlcart;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.exlcart.adapter.CustomPagerAdapter;
import com.exlcart.fragment.CartFragment;
import com.exlcart.fragment.HomeFragment;
import com.exlcart.fragment.ProfileFragment;
import com.exlcart.fragment.SearchFragment;
import com.exlcart.fragment.StoreFragment;

import java.util.ArrayList;

/**
 * Created by Saravanan on 7/4/2015.
 */
public class HomeActivity extends FragmentActivity implements  ViewPager.OnPageChangeListener{

    TextView txtTitle;
    ViewPager viewPager;
    FrameLayout containerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        txtTitle = (TextView) findViewById(R.id.txtHeading);
        txtTitle.setText(getString(R.string.strBasement));
        viewPager = (ViewPager) findViewById(R.id.pager);

        LoadFragments();

    }

    private void LoadFragments() {
        ArrayList<Fragment> listOfFragments = new ArrayList<Fragment>();
        listOfFragments.add(new HomeFragment());
        listOfFragments.add(new SearchFragment());
        listOfFragments.add(new StoreFragment());
        listOfFragments.add(new CartFragment());
        listOfFragments.add(new ProfileFragment());

        ArrayList<Integer> listIcon = new ArrayList<Integer>();
        listIcon.add(R.drawable.header_menu);
        listIcon.add(R.drawable.header_menu);
        listIcon.add(R.drawable.header_menu);
        listIcon.add(R.drawable.header_menu);
        listIcon.add(R.drawable.header_menu);



        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(
                getSupportFragmentManager(), listIcon,listOfFragments);
        viewPager.setAdapter(customPagerAdapter);

        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setShouldExpand(true);

        tabsStrip.setViewPager(viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:


                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
