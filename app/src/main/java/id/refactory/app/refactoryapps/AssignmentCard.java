package id.refactory.app.refactoryapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by reza on 12/7/17.
 */

public class AssignmentCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.assignment_listview);

        TextView detailsButton = (TextView) findViewById(R.id.bt_detail);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(AssignmentCard.this, DetailAssignments.class);
                startActivity(detailsIntent);
            }
        });
    }
}
