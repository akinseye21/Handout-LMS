package com.akinseye.ndif_yemmanuel.handout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterCovidWorldAfrica extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapterCovidWorldAfrica(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                World world = new World();
                return  world;
            case 1:
                Africa africa = new Africa();
                return africa;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
