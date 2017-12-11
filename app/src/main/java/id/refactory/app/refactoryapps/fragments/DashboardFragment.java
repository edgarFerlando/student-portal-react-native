package id.refactory.app.refactoryapps.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.adapter.dashboard.PagerAdapter;
import id.refactory.app.refactoryapps.sessions.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements HomeFragment.OnFragmentInteractionListener, WPMFragment.OnFragmentInteractionListener, HRFragment.OnFragmentInteractionListener, SOFFragment.OnFragmentInteractionListener, OSFragment.OnFragmentInteractionListener
    {

    SessionManager session;
    private String berer;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;
    private Unbinder unbinder;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dashboard, container, false);
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        getActivity().setTitle("Project");

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_refactory_project));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_refactory_wpm));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_refactory_hackerrank));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_refactory_sof));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_refactory_os));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // tabLayout has been binded using Butter Knife
        final PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        getActivity().setTitle("Project");
                        break;
                    case 1:
                        getActivity().setTitle("WPM");
                        break;
                    case 2:
                        getActivity().setTitle("Hacker Rank");
                        break;
                    case 3:
                        getActivity().setTitle("Stack Overflow");
                        break;
                    case 4:
                        getActivity().setTitle("OpenSource");
                        break;
                    default:
                        getActivity().setTitle("Project");
                        break;
                }

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

    public String GetToken() {
        session = new SessionManager(getActivity().getApplicationContext());

        //Toast.makeText(getApplicationContext(),"User Login Status " + session.loggedIn(), Toast.LENGTH_LONG).show();
        //session.checkLogin();

        HashMap<String, String> tokenDetails = session.getTokenDetails();

        String tokn = tokenDetails.get(SessionManager.KEY_NAME);
        berer = "Bearer " +tokn ;

        Log.e("uu", berer );
        return berer;

    }

}
