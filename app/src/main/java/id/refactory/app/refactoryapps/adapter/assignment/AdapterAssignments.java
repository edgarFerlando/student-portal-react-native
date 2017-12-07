package id.refactory.app.refactoryapps.adapter.assignment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.DetailAssignments;
import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.fragments.AssignmentFragment;
import id.refactory.app.refactoryapps.models.DataAssignments;

/**
 * Created by massam on 20/10/17.
 */

public class AdapterAssignments extends RecyclerView.Adapter<AdapterAssignments.MyViewHolder> {
    private List<DataAssignments> assignments;
    private AssignmentFragment.FragmentListener mListener;

    @BindView(R.id.tv_status) TextView textView;

    public AdapterAssignments(List<DataAssignments> assignments, AssignmentFragment.FragmentListener listener)
    {

        this.assignments = assignments;
        this.mListener = listener;
    }

//    public AdapterAssignments(FragmentActivity activity, ArrayList<DataAssignments> dataResults) {
//    }

    @Override
    public AdapterAssignments.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_assignments_rows,viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterAssignments.MyViewHolder viewHolder, final int position){

        final AdapterAssignments.MyViewHolder holder = viewHolder;

        final String project = assignments.get(position).getAssignmentType().toUpperCase();
        String projects = "PROJECT";

        if(project.matches(projects)){
            holder.tv_project.setText(assignments.get(position).getStatus());
            holder.tv_ticket.setText(assignments.get(position).getId().toString());
            holder.tv_link.setText(assignments.get(position).getTitle());
        }

        final String assign= assignments.get(position).getStatus();
        final Integer codeAssign = assignments.get(position).getId();
        final String linkAssignments = assignments.get(position).getTitle();
        final String descryptionAssignments = assignments.get(position).getDescription();
        final String authorAssignments = assignments.get(position).getAuthor();


        // Set onclicklistener on view cardview (CardView)
        holder.tv_link.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tv_status) TextView tv_project;
        @BindView(R.id.tv_code_tickets) TextView tv_ticket;
        @BindView(R.id.tv_link_tickets) TextView tv_link;

        public MyViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);

            //tvstatus = (TextView)view.findViewById(R.id.tv_status);
        }

    }

}
