package com.example.namilaradith.comi_beta;

/**
 * Created by Namila Radith on 2016-03-11.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabActivityFragment tab1 = new TabActivityFragment();
                return tab1;
            case 1:
                TabEventsFragment tab2 = new TabEventsFragment();
                return tab2;
            case 2:
                TabGroupsFragment tab3 = new TabGroupsFragment();
                return tab3;
            case 3:
                TabMessagesFragment tab4 = new TabMessagesFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.mNumOfTabs;
    }
}
