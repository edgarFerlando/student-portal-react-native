package id.refactory.app.refactoryapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import id.refactory.app.refactoryapps.api.models.DataAssignment;
import id.refactory.app.refactoryapps.fragments.AssignmentFragment;
import id.refactory.app.refactoryapps.fragments.DashboardFragment;
import id.refactory.app.refactoryapps.fragments.FeedbackDialog;
import id.refactory.app.refactoryapps.fragments.OverviewFragment;
import id.refactory.app.refactoryapps.fragments.UploadDocsFragment;
import id.refactory.app.refactoryapps.sessions.SessionManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AssignmentFragment.FragmentListener {

    SessionManager session;
    private String berer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    // set session
    SessionManager session = new SessionManager(getApplicationContext());

    //get user data from session
    HashMap<String, String> user = session.getTokenDetails();

    // toast awareness user login
        Toast.makeText(getApplicationContext(),"User Login Status : "+session.loggedIn()+" ",Toast.LENGTH_SHORT).show();

        session.checkLogin();

    //Set DrawerLayout
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    //add this line to display menu1 when the activity is loaded
    displaySelectedScreen(R.id.nav_overview);
}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();{
                {
                    //fix issue
//                    Intent view = new Intent(getApplication(),MainActivity.class);
//                    startActivity(view);
                    finish();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void displaySelectedScreen(int ItemId) {
        //Creating fragment object
        Intent i;
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (ItemId) {
            case R.id.nav_overview:
                fragment = new OverviewFragment();
                break;
            case R.id.nav_dashboard:
//                i = new Intent(getApplicationContext(),Dashboard.class);
//                startActivity(i);
//                finish();
                fragment = new DashboardFragment();
                break;
            case R.id.nav_assignment:
//                i = new Intent(getApplicationContext(), Assignments.class);
//                startActivity(i);
//                finish();
//                break;
                fragment = new AssignmentFragment();
                // fragment = new DetailAssignmentFragment(); //--> Todo : Still Working on Passing Data Fragment to Fragment through Activity. #Prana, Des-06-2017.
                break;
            case R.id.nav_feedback:
                final BottomSheetDialog bottomSheetDialog = new FeedbackDialog(this);
                bottomSheetDialog.show();
                break;
            case R.id.nav_codeofconduct:
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;

                final BottomSheetDialog dialog = new BottomSheetDialog(this);
                View view = getLayoutInflater().inflate(R.layout.fragment_code_of_conduct, null);

                TextView titleBar = view.findViewById(R.id.titleBar);
                titleBar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(((View) view.getParent()));
                bottomSheetBehavior.setHideable(false);
                bottomSheetBehavior.setPeekHeight(height);

                dialog.show();;
                break;
            case R.id.nav_logOut:
                i = new Intent(getApplicationContext(), GitLogin.class);
                startActivity(i);
                finish();
                deleteAppData();
                break;

            case R.id.nav_upload_docs:
                fragment = new UploadDocsFragment();
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    //delete data APP from android data
    private void deleteAppData() {
        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    Todo :
    From Mr.Andy
    Method Untuk Pass Data via Fragment,
    Buka File AdapaterAssignment.java
    uncomment "public void onClick(View view)"
    Still on Progress By Prana.
     */

    @Override
    public void setDetails(Object data) {

        List<DataAssignment> a = (List<DataAssignment>)data;
//        pass data to DetailAssignmentFragment
        Log.d("BERHASIL", "setDetails: Data Berhasil Di PASS dari fragement ke Activity " + data);
    }

    //Method For Passing Data Token From Activity to Fragment
    public String GetToken() {
        session = new SessionManager(getApplicationContext());

        //Test Passing data.
        //Toast.makeText(getApplicationContext(),"User Login Status " + session.loggedIn(), Toast.LENGTH_LONG).show();
        //session.checkLogin();

        HashMap<String, String> tokenDetails = session.getTokenDetails();

        String tokn = tokenDetails.get(SessionManager.KEY_NAME);
        berer = "Bearer " +tokn ;

        Log.e("uu", berer );
        return berer;

    }
}