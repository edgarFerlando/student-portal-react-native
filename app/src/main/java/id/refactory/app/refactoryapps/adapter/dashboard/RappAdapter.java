package id.refactory.app.refactoryapps.adapter.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.api.models.Datum;
import id.refactory.app.refactoryapps.api.models.RappMod;

/**
 * Created by prana on 06/10/17.
 */

public class RappAdapter extends RecyclerView.Adapter<RappAdapter.ViewHolder> {

    public Context context;
    public ArrayList<Datum> rappModArrayList;
    public ArrayList<RappMod> dataAssignments;


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_status,tv_assignment, tv_link;
        private Button bt_report;

        public ViewHolder(View view) {
            super(view);
            tv_status = (TextView)view.findViewById(R.id.status);
            tv_assignment = (TextView)view.findViewById(R.id.assignment);
            tv_link = (TextView)view.findViewById(R.id.link);
            bt_report = (Button) view.findViewById(R.id.bt_report);
        }
    }


    public RappAdapter(Context context, ArrayList<Datum> rappModArrayList) {
        this.context = context;
        this.rappModArrayList = rappModArrayList;
    }

    //Ref : http://www.glamvian.com/Lebih-dalam-tentang-RecyclerView/
    //onCreateViewHolder yang dipanggil ketika RecyclerView menginstanisasi intance ViewHolder

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.assignment_listview, viewGroup, false);
        return new ViewHolder(view);
    }

    //onBindViewHolder method ini dipanggil ketika RecyclerView ingin mengisi view dengan data

    int a ;
    @Override
    public void onBindViewHolder(ViewHolder viewHolder4, int i ) {

                if (rappModArrayList.get(i).getAssignmentType().toUpperCase().equals("PROJECT"))
             //   a = rappModArrayList.size();
                {
                    Log.d("CUk", "onBindViewHolder: " + rappModArrayList.get(i).getUrl());
                    viewHolder4.tv_status.setText(rappModArrayList.get(i).getStatus());
                    viewHolder4.tv_assignment.setText(rappModArrayList.get(i).getAssignmentType() + " :");
                    viewHolder4.tv_link.setText("" + rappModArrayList.get(i).getUrl());
                }
        //Log.e("", "onBindViewHolder: "+ a );

        //12 dan 20 urutan array untuk "project"
        //seharusnya menampilakan berdasarkan filter id_assignment/type_assignment dan array loop bedasarkan id_assignment/type_assignment

        // viewHolder.tv_status.setText(rappModArrayList.get(0).getStatus());
      //  viewHolder.tv_link.setText(rappModArrayList.get(0).getUrl())

    }


// Contoh code seblumnya
//    public void onBindViewHolder3(ViewHolder viewHolder, int i) {
//        viewHolder.tv_status.setText(rappModArrayList.get(i).getStatus());
//        viewHolder.tv_link.setText(""+ rappModArrayList.get(i).getTitle());
//    }


    //getItemCount yang return jumlah dari item ke dalam data source
    @Override
    public int getItemCount() {
//        ArrayList<Datum> filteredRappMod = new ArrayList<>();
        return this.rappModArrayList.size();
    }
}
