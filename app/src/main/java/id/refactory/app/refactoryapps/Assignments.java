package id.refactory.app.refactoryapps;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.adapter.assignment.AdapterAssignments;
import id.refactory.app.refactoryapps.api.request.RetrofitAssignment;
import id.refactory.app.refactoryapps.models.DataAssignments;
import id.refactory.app.refactoryapps.models.ResultAssignments;
import id.refactory.app.refactoryapps.sessions.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Assignments extends BaseActivity {

    @Inject Retrofit retrofit;

    @BindView(R.id.assignment_numbers) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    private AdapterAssignments mAdapter;
    List<DataAssignments> assignments;
    private RecyclerView.LayoutManager mLayoutManager;
    SessionManager sessionManager;

    @Override
    protected int getLayoutResourceId() {
//        This is change how usually using setContentView() to be more compatible with BaseActivity
        return R.layout.activity_assignments;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        RefactoryApplication.get(this).getApplicationComponent().inject(this);
        //Show Progress Bar
        progressBar.setVisibility(View.VISIBLE);
        initViews();
        //RefactoryApplication.get(this).getApplicationComponent().inject(this);
    }

    private void initViews(){
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadDataAssignments();
    }

    private void loadDataAssignments(){

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getTokenDetails();

        String storeToken = user.get(SessionManager.KEY_NAME);
        String tokenType = "Bearer";

        Log.d("yeah", "loadDataAssignments: "+tokenType);
        String dataToken = tokenType+" "+storeToken;

        RetrofitAssignment apiservice = retrofit.create(RetrofitAssignment.class);
        Call<ResultAssignments> call = apiservice.listData(dataToken);
        call.enqueue(new Callback<ResultAssignments>() {
            @Override
            public void onResponse(Call<ResultAssignments> call, Response<ResultAssignments> response) {

                if(response.isSuccessful()){
                    assignments = new ArrayList<>();
                    ResultAssignments result = response.body();
//                    Log.d("yeah", "onResponse: "+new Gson().toJson(result));
                    assignments = result.getData();

                    //Filter Project
                    ArrayList<DataAssignments> dataResults = new ArrayList<DataAssignments>();
                    for (DataAssignments data: assignments){
                        if(data.getAssignmentType().toUpperCase().equals("PROJECT")){
                            dataResults.add(data);
                        }
                    }

                    //this data loads
                   // mAdapter = new AdapterAssignments(dataResults, null); //assignments

                    //attach to recycleview
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    //Hide Progress Bar
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(mAdapter);


                }


            }

            @Override
            public void onFailure(Call<ResultAssignments> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
