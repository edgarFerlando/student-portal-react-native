package id.refactory.app.refactoryapps.adapter.assignment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.refactory.app.refactoryapps.fragments.AssignmentDoingFragment;
import id.refactory.app.refactoryapps.fragments.AssignmentReadyFragment;
import id.refactory.app.refactoryapps.fragments.AssignmentTodoFragment;
import id.refactory.app.refactoryapps.fragments.HRFragment;
import id.refactory.app.refactoryapps.fragments.HomeFragment;
import id.refactory.app.refactoryapps.fragments.OSFragment;
import id.refactory.app.refactoryapps.fragments.SOFFragment;
import id.refactory.app.refactoryapps.fragments.WPMFragment;

/**
 * Created by dhanialrizky on 08/12/17.
 */

public class AssignmentPagerAdapter extends FragmentStatePagerAdapter {

    int mNoTabs;

    public AssignmentPagerAdapter(FragmentManager fm, int NumsOfTabs){
        super(fm);
        this.mNoTabs = NumsOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AssignmentTodoFragment assignmentTodoFragment = new AssignmentTodoFragment();
                return assignmentTodoFragment;
            case 1:
                AssignmentDoingFragment assignmentDoingFragment = new AssignmentDoingFragment();
                return  assignmentDoingFragment;
            case 2:
                AssignmentReadyFragment assignmentReadyFragment = new AssignmentReadyFragment();
                return assignmentReadyFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNoTabs;
    }
}
