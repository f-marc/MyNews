package com.fleury.marc.mynews.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fleury.marc.mynews.controllers.fragments.home.MainFragment;
import com.fleury.marc.mynews.controllers.fragments.home.StoriesPageFragment;
import com.fleury.marc.mynews.controllers.fragments.home.WorldPageFragment;

public class PageAdapter extends FragmentPagerAdapter {


    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return StoriesPageFragment.newInstance();
            case 1:
                return MainFragment.newInstance();
            case 2:
                return WorldPageFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "WORLD";
            default:
                return null;
        }
    }
}
