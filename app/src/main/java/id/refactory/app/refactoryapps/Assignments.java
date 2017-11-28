package id.refactory.app.refactoryapps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.api.request.RetrofitAssignment;
import id.refactory.app.refactoryapps.adapter.assignment.AdapterAssignments;
import id.refactory.app.refactoryapps.models.DataAssignments;
import id.refactory.app.refactoryapps.models.ResultAssignments;
import id.refactory.app.refactoryapps.sessions.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Assignments extends AppCompatActivity{

    @Inject Retrofit retrofit;

    @BindView(R.id.assignment_numbers) RecyclerView recyclerView;
    private AdapterAssignments mAdapter;
    List<DataAssignments> assignments;
    private RecyclerView.LayoutManager mLayoutManager;
    SessionManager sessionManager;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        ButterKnife.bind(this);
        RefactoryApplication.get(this).getApplicationComponent().inject(this);
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
                    mAdapter = new AdapterAssignments(dataResults); //assignments

                    //attach to recycleview
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);

                }

            }

            @Override
            public void onFailure(Call<ResultAssignments> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    public void onBackPressed(){
        Intent view = new Intent(getApplication(),MainActivity.class);
        startActivity(view);
        finish();
    }


}
