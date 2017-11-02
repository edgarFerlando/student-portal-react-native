package id.refactory.app.refactoryapps.adapter.assignment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.models.DataAssignments;
import id.refactory.app.refactoryapps.DetailAssignments;

/**
 * Created by massam on 20/10/17.
 */

public class AdapterAssignments extends RecyclerView.Adapter<AdapterAssignments.MyViewHolder> {
    private List<DataAssignments> assignments;



    public AdapterAssignments(List<DataAssignments> assignments){
        this.assignments = assignments;
    }

    @Override
    public AdapterAssignments.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_assignments_rows,viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterAssignments.MyViewHolder viewHolder, final int position){

        final AdapterAssignments.MyViewHolder holder = viewHolder;

        final String project = assignments.get(position).getAssignment_type().toUpperCase();
        String projects = "PROJECT";

        if(project.matches(projects)){
            holder.tv_project.setText(assignments.get(position).getStatus());
            holder.tv_ticket.setText(assignments.get(position).getId().toString());
            holder.tv_link.setText(assignments.get(position).getTitle());
        }

        final String assign= assignments.get(position).getStatus();
        final Integer codeAssign = assignments.get(position).getId();
        final String linkAssignments = assignments.get(position).getTitle();
        final String descryptionAssignments = assignments.get(position).getDescryption();
        final String authorAssignments = assignments.get(position).getAuthor();


        // Set onclicklistener on view cardview (CardView)
        holder.tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;
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
        private TextView tv_project, tv_ticket, tv_link, tvstatus;

        public MyViewHolder(View view){
            super(view);

            tv_project  = ( TextView ) view.findViewById(R.id.tv_status);
            tv_ticket   = ( TextView ) view.findViewById(R.id.tv_code_tickets);
            tv_link     = ( TextView ) view.findViewById(R.id.tv_link_tickets);

            tvstatus = (TextView)view.findViewById(R.id.tv_status);

        }

    }

}