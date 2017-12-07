package id.refactory.app.refactoryapps.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

   @Inject
    Retrofit retrofit;

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.assignment_numbers) RecyclerView recyclerView;
    private Unbinder unbinder;

    private AdapterAssignments mAdapter;
    List<DataAssignments> assignments;

    private RecyclerView.LayoutManager mLayoutManager;
    SessionManager sessionManager;

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

       // Call Method initViews-> to set & Load the recyclerview data
        initViews();
        getActivity().setTitle("Assignment");
        //Show Progress Bar
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

   private void initViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadDataAssignments();

        /*
        //Use this code below here if you want to remove the initViews() method
        getActivity().setTitle("Assignment");
        return view;
        */
    }

    private void loadDataAssignments() {

        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getTokenDetails();

        String storeToken = user.get(SessionManager.KEY_NAME);
        String tokenType = "Bearer";

        Log.d("yeah", "loadDataAssignments: " + tokenType);
        String dataToken = tokenType + " " + storeToken;

        //this code is not working, because retrofit (dagger) get error Nullpointer
        RetrofitAssignment apiservice = retrofit.create(RetrofitAssignment.class); // --> has fixed after add "void inject (AssignmentFragment assignmentsFragment);" in dager.AplicationComponet

        //This code is work but not smoth, because it using direct call with getClient().create(RetrofitAssigment.class)
        //RetrofitAssignment apiservice = RetrofitConnect.getClient().create(RetrofitAssignment.class);

        Call<ResultAssignments> call = apiservice.listData(dataToken);
        call.enqueue(new Callback<ResultAssignments>() {
            @Override
            public void onResponse(Call<ResultAssignments> call, Response<ResultAssignments> response) {

                if (response.isSuccessful()) {
                    assignments = new ArrayList<>();
                    ResultAssignments result = response.body();
//                   Log.d("yeah", "onResponse: "+new Gson().toJson(result));
                    assignments = result.getData();

                    //Filter Project
                    ArrayList<DataAssignments> dataResults = new ArrayList<DataAssignments>();
                    for (DataAssignments data : assignments) {
                        if (data.getAssignmentType().toUpperCase().equals("PROJECT")) {
                            dataResults.add(data);
                        }
                    }

                    //this data loads
                    mAdapter = new AdapterAssignments(dataResults, mFragmentListener); //assignments

                    //attach to recycleview
                    mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                    //Hide Progress Bar
                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<ResultAssignments> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
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
