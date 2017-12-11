package id.refactory.app.refactoryapps.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.RefactoryApplication;
import id.refactory.app.refactoryapps.adapter.assignment.AdapterAssignments;
import id.refactory.app.refactoryapps.adapter.assignment.AssignmentPagerAdapter;
import id.refactory.app.refactoryapps.api.request.RetrofitAssignment;
import id.refactory.app.refactoryapps.models.DataAssignments;
import id.refactory.app.refactoryapps.models.ResultAssignments;
import id.refactory.app.refactoryapps.sessions.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentFragment extends Fragment {

    private FragmentListener mFragmentListener;
//    EventBus -> pelajari untuk pass data via fragment

    @BindView(R.id.assignmentTabLayout) TabLayout tabLayout;
    @BindView(R.id.assignmentPager) ViewPager viewPager;
    protected Unbinder unbinder;

    public AssignmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        // return inflater.inflate(R.layout.fragment_assignment, container, false); // --> Not use because now use Final View v
        final View view = inflater.inflate(R.layout.fragment_assignment,container, false);
        unbinder = ButterKnife.bind(this, view);

       // RefactoryApplication.get(this).getApplicationComponent().inject(this); //--> getting error please fix
        RefactoryApplication.get(getActivity()).getApplicationComponent().inject(this); //-->  fix by add "void inject (AssignmentFragment assignmentsFragment);" in dager.AplicationComponet

        getActivity().setTitle("Assignments");

        tabLayout.addTab(tabLayout.newTab().setText("Todo"));
        tabLayout.addTab(tabLayout.newTab().setText("Doing"));
        tabLayout.addTab(tabLayout.newTab().setText("Ready"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final AssignmentPagerAdapter assignmentPagerAdapter = new AssignmentPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(assignmentPagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentListener) {
            mFragmentListener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListener");
        }
    }

    public interface FragmentListener {
        void setDetails(Object data);
    }
/*
// in Fragment there is no onBackPressed method, so need to find out another way to code.
    public void onBackPressed(){
        Intent view = new Intent(getActivity(),MainActivity.class);
        startActivity(view);
        //finish();
    }
*/
}
