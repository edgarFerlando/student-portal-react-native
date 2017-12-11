package id.refactory.app.refactoryapps.adapter.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.refactory.app.refactoryapps.fragments.HRFragment;
import id.refactory.app.refactoryapps.fragments.HomeFragment;
import id.refactory.app.refactoryapps.fragments.OSFragment;
import id.refactory.app.refactoryapps.fragments.SOFFragment;
import id.refactory.app.refactoryapps.fragments.WPMFragment;

/**
 * Created by prana on 04/10/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoTabs;

    public PagerAdapter(FragmentManager fm, int NumsOfTabs){

        super(fm);
        this.mNoTabs =NumsOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                HomeFragment hfrag = new HomeFragment();
                return hfrag;
            case 1:
                WPMFragment wpmfrag = new WPMFragment();
                return  wpmfrag;
            case 2:
                HRFragment hrfrag = new HRFragment();
                return hrfrag;
            case 3:
                SOFFragment soffrag = new SOFFragment();
                return soffrag;
            case 4:
                OSFragment osfrag = new OSFragment();
                return  osfrag;
            default:

                return null;
        }
    }
    @Override
    public int getCount() {
        return mNoTabs;
    }
}
