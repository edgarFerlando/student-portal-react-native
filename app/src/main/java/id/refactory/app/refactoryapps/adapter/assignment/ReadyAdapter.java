package id.refactory.app.refactoryapps.adapter.assignment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.DetailAssignments;
import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.fragments.AssignmentFragment;
import id.refactory.app.refactoryapps.models.DataAssignments;

/**
 * Created by dhanialrizky on 11/12/17.
 */

public class ReadyAdapter extends RecyclerView.Adapter<ReadyAdapter.MyViewHolder> {
    private List<DataAssignments> assignments;
    private AssignmentFragment.FragmentListener mListener;

    @BindView(R.id.tv_status) TextView textView;

    public ReadyAdapter(List<DataAssignments> assignments, AssignmentFragment.FragmentListener listener)
    {

        this.assignments = assignments;
        this.mListener = listener;
    }

//    public AdapterAssignments(FragmentActivity activity, ArrayList<DataAssignments> dataResults) {
//    }

    @Override
    public ReadyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_assignments_rows,viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReadyAdapter.MyViewHolder viewHolder, final int position){

        final ReadyAdapter.MyViewHolder holder = viewHolder;

        final String project = assignments.get(position).getAssignmentType().toUpperCase();
        final String assignStatuses = assignments.get(position).getStatus().toUpperCase();
        String projects = "PROJECT";
        String assignStatus = "READY";

        if(project.matches(projects) && assignStatuses.matches(assignStatus)){
            holder.tv_status.setBackgroundResource(R.color.backgroundGreen);
            holder.tv_status.setText(assignments.get(position).getStatus());
            holder.tv_ticket.setText(assignments.get(position).getId().toString());
            holder.tv_link.setText(assignments.get(position).getTitle());
            holder.tv_description.setText(assignments.get(position).getDescription());
        }

        final String assign= assignments.get(position).getStatus();
        final Integer codeAssign = assignments.get(position).getId();
        final String linkAssignments = assignments.get(position).getTitle();
        final String descryptionAssignments = assignments.get(position).getDescription();
        final String authorAssignments = assignments.get(position).getAuthor();


        // Set onclicklistener on view cardview (CardView)
        holder.cardviewLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "Hold Long Press Triggered!", Toast.LENGTH_LONG).show();

                return true;
            }
        });
        holder.cardviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;
                /*
                from Mas Andy
                Pass Data via Fragment ,
                String a = "Cuk";
                Object coba;
                mListener.setDetails(assignments);
                //still on progress by Prana
                */

                TextView textview = (TextView) view.findViewById(R.id.tv_status);
                Intent intent = new Intent(view.getContext(), DetailAssignments.class);

                intent.putExtra("status",assign);
                intent.putExtra("ID", codeAssign);
                intent.putExtra("link", linkAssignments);
                intent.putExtra("descryption", descryptionAssignments);
                intent.putExtra("author", authorAssignments);

                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public  int getItemCount(){
        return assignments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_status) TextView tv_status;
        @BindView(R.id.tv_code_tickets) TextView tv_ticket;
        @BindView(R.id.tv_link_tickets) TextView tv_link;
        @BindView(R.id.tv_description) TextView tv_description;
        @BindView(R.id.cardview_layout) LinearLayout cardviewLayout;


        public MyViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);


            //tvstatus = (TextView)view.findViewById(R.id.tv_status);
        }

    }

}
