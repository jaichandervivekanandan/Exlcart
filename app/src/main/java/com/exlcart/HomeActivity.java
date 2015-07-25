package com.exlcart;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
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
public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    TextView txtTitle;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    FrameLayout containerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle DrawerButton;
    ListView drawerlist;
    /*at long last commented succefully .. hello ragu !! ;-) */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.layoutHeader);
        viewPager = (ViewPager) findViewById(R.id.pager);
        drawerlist = (ListView) findViewById(R.id.leftdrawerlayout_list);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.strBasement));
        LoadFragments();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerButton = new ActionBarDrawerToggle(this, drawerLayout, R.string.strBasement, R.string.strBasement) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(DrawerButton);

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
                getSupportFragmentManager(), listIcon, listOfFragments);
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
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;

        }
        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.pager, fragment).commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerlist);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        DrawerButton.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        DrawerButton.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (DrawerButton.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
