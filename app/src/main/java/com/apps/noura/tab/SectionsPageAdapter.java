package com.apps.noura.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noura Alsomaikhi on 12/28/2017.
 */

public class SectionsPageAdapter  extends FragmentPagerAdapter {

    // Fragments Details:
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    //Add Fragment
    public void addFragment (Fragment fragment, String title){

        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);

    }
    // Constructor
    public SectionsPageAdapter (FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return  mFragmentTitleList.get(position);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}


