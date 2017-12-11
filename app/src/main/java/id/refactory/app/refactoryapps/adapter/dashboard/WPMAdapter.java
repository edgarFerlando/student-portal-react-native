package id.refactory.app.refactoryapps.adapter.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.api.models.DataAssignment;

/**
 * Created by prana on 16/10/17.
 */

public class WPMAdapter extends RecyclerView.Adapter<WPMAdapter.ViewHolder> {

    public Context konteks; // -> context, bisa di ganti inisialisasinya eq: konteks
    public ArrayList<DataAssignment> rappModAL; // -> rappModArrayList, bisa di ganti inisialisasinya eq: rappModAL

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.status) TextView tv_status;
        @BindView(R.id.assignment) TextView tv_assignment;
        @BindView(R.id.link) TextView tv_link;
        @BindView(R.id.bt_report) Button bt_report;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    // /membuat konstruktor HRAdapter
    public WPMAdapter(Context konteks, ArrayList<DataAssignment> rappModAL) {
        this.konteks = konteks;
        this.rappModAL = rappModAL;
    }


    public WPMAdapter.ViewHolder onCreateViewHolder(ViewGroup v, int i) {
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.assignment_listview, v, false);
        return new WPMAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WPMAdapter.ViewHolder v0, int i) {
        if(rappModAL.get(i).getAssignmentType().equals("WPM")) {
            v0.tv_status.setText(rappModAL.get(i).getStatus());
            v0.tv_assignment.setText(rappModAL.get(i).getAssignmentType() + " :");
            v0.tv_link.setText("" + rappModAL.get(i).getUrl());
        }
    }

    @Override
    public int getItemCount() {
        return rappModAL.size();
    }
}
