package com.akinseye.ndif_yemmanuel.handout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by NDIF_YEMMANUEL on 7/22/2018.
 */

public class PagerAdapterCovid extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapterCovid(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Maps maps = new Maps();
                return  maps;
            case 1:
                Charts charts = new Charts();
                return charts;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
