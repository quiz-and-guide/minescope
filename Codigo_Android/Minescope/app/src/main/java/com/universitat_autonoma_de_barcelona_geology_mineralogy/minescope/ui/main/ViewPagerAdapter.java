package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new TransparentsFragment();
        }
        else if (position == 1)
        {
            fragment = new OpaquesFragment();
        }
        else if (position == 2)
        {
            fragment = new FilterFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        /*if (position==0) {
            title = "TRANSPARENTS";
        } else if (position==1) {
            title = "OPAQUES";
        } else if (position==2) {
            title = "FILTER";
        }*/
        return title;
    }
}