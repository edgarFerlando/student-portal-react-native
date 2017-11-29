package id.refactory.app.refactoryapps.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.refactory.app.refactoryapps.Dashboard;
import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.RefactoryApplication;
import id.refactory.app.refactoryapps.api.models.DataAssignment;
import id.refactory.app.refactoryapps.api.models.RappMod;
import id.refactory.app.refactoryapps.api.services.RappClient;
import id.refactory.app.refactoryapps.adapter.dashboard.OSAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prana on 04/10/17.
 */

public class OSFragment extends Fragment {
    @BindView(R.id.listOS) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    private Unbinder unbinder;
    //================================================================
    private ArrayList<DataAssignment> mDatalist;
    private OSAdapter mDataAdapter;
    //================================================================
    @Inject RappClient apiService;

    public OSFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //================================================================
        final View view =inflater.inflate(R.layout.fragment_os, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        RefactoryApplication.get(this.getContext()).getApplicationComponent().inject(this);

        //Show Progress Bar
        progressBar.setVisibility(View.VISIBLE);

        String grabToken = ((Dashboard) getActivity()).GetToken();

        //Call<RappMod> rappModCall = apiService.listData("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY1MmRjMDI3YWE3NmU0OTM1YzNmMTBmZmJkYjYwZjkxODc5ZGQ3ZGZlOWNhMzIxYWFjZmEyMWU5YzE2YTkzNGJmZjljYzM0YTJiODFhOTUyIn0.eyJhdWQiOiIzIiwianRpIjoiNjUyZGMwMjdhYTc2ZTQ5MzVjM2YxMGZmYmRiNjBmOTE4NzlkZDdkZmU5Y2EzMjFhYWNmYTIxZTljMTZhOTM0YmZmOWNjMzRhMmI4MWE5NTIiLCJpYXQiOjE1MDYzMTQ0MDIsIm5iZiI6MTUwNjMxNDQwMiwiZXhwIjoxNTM3ODUwNDAyLCJzdWIiOiIxNjIiLCJzY29wZXMiOltdfQ.L4FSVO6EstmMEctxdFEsDJ9Lkjiu9s7TrNXIrn52uTONYqbMb5KYxSlLk3J1bbfxcdZTD_KMei2Nx2tFBCQNQ9PAkYjd8dWN1eLlVeyChYuJLoA6NMWkJECxip2m_HlyWIJXe8yDSMsCkbuSCb1va4gNSJpvl7Kn0rhi9d-qz9u1v7f-uDkool9maNjLvCAnHqDSSYZJhthe8oD0ooH1AcQ1VqkNcuC_Cg1KZiyO020BnpgA_k0fLw3hVLB8BAHx4eW2yWQybSsu9EJIqoC8-Ix3LdLtBBlMSv75pfAxhjrU61IFbhMxwFB7WlK9di569C8EaE-cewJYMW64naMFUqv0osRHvgPpasGFck6G1JkPNgwcKXRMVS_WquRUYbskAsnAR9xVjVknbu91EEC3DtU_-b5lkUHvmw9uiq7oagR4KflPDFGC9Mcc11WWuLI6lHqXk4UJjVD2bltQxlZxAotD0hUU0t47Gtl8PpiYNd4qnktcUyN4eUAHewPK9XIvJXPlsJQeEtZ64r0UTyl-x_FWEnrzaEhFbfdvvAsXbjb4yXynfFq9AizmvM3DKdSAtjdib6Ai6bymyfenr06aeFxRmKLneCImqP25-ED0tpex_rwTTzhv1i2ZrXJ4gWEYlQQBwPc8FPJTk2L1AqkMHcOMIreRaKQw7YgpIIr_qCk");

        Call<RappMod> rappModCall = apiService.listData(grabToken);

        rappModCall.enqueue(new Callback<RappMod>() {
            @Override
            public void onResponse(Call<RappMod> call, Response<RappMod> response) {

                RappMod rappMod = response.body();

                //Log.d(rappMod.toString(), "rappMod ");

                mDatalist = new ArrayList<>(rappMod.getData());
                //Filter langusng Berdasarkan getAssignmetType = "Opensource"
                ArrayList<DataAssignment> result = new ArrayList<DataAssignment>();
                for (DataAssignment data : mDatalist) {
                    if(data.getAssignmentType().equals("Opensource"))
                        result.add(data);
                }
                mDataAdapter= new OSAdapter(getActivity(), result);
                //mDataAdapter= new OSAdapter(getActivity(), mDatalist); // Diclose karena mDatalist diganti result
                if (recyclerView != null) {
                    recyclerView.setAdapter(mDataAdapter);
                    //Hide Progress Bar
                    progressBar.setVisibility(View.GONE);
                }
                //Log.e("****",""+ rappMod.getData());g
            }

            @Override
            public void onFailure(Call<RappMod> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

        //================================================================

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_os, container, false); // -> diganti jadi return View

        //================================================================
        return view;
        //================================================================
    }

    public interface OnFragmentInteractionListener {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // For binding reset in fragment lifecycle
        unbinder.unbind();
    }
}
