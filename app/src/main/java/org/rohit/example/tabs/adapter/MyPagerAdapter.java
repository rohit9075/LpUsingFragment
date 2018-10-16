package org.rohit.example.tabs.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.rohit.example.tabs.Utils.Constants;


/**
 * PagerAdapter for supplying the ViewPager with the pages (fragments) to display.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

  public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return Constants.PAGES[position];
    }

    @Override
    public int getCount() {
        return Constants.PAGES.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.PAGE_TITLES[position];
    }
}
