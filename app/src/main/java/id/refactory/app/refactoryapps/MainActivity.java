package id.refactory.app.refactoryapps;

//import android.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.fragments.FeedbackDialog;
import id.refactory.app.refactoryapps.fragments.OverviewFragment;
import id.refactory.app.refactoryapps.sessions.SessionManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OverviewFragment.OnFragmentInteractionListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        // set session
        SessionManager session = new SessionManager(getApplicationContext());

        //get user data from session
        HashMap<String, String> user = session.getTokenDetails();
        // toast awareness user login
        Toast.makeText(getApplicationContext(),"User Login Status : "+ session.loggedIn()+" ", Toast.LENGTH_SHORT).show();
        session.checkLogin();

        //Set DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();{
                    Intent view = new Intent(getApplication(),MainActivity.class);
                    startActivity(view);
                    finish();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        Fragment fragment = null;
//        Class fragmentClass = null;

        if (id == R.id.nav_overview) {
//            // Handle the fragment action
//            fragmentClass = OverviewFragment.class;
        } else if (id == R.id.nav_dashboard) {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_assignment) {
            Intent i = new Intent(getApplicationContext(), Assignments.class);
            startActivity(i);
            finish();
        }/* else if (id == R.id.nav_login) {
            Intent i = new Intent(getApplicationContext(), GitLogin.class);
            startActivity(i);
            finish();*/
         else if (id == R.id.nav_logOut){
            Intent i = new Intent (getApplicationContext(),GitLogin.class);
            startActivity(i);
            finish();
            deleteAppData();
        }
        else if (id == R.id.nav_feedback) {

            final BottomSheetDialog bottomSheetDialog = new FeedbackDialog(this);
            bottomSheetDialog.show();

        }
        else if (id == R.id.nav_codeofconduct) {
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

            dialog.show();
        }

//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e)
//            e.printStackTrace();
//        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
        }

    // for clearing apps data programmatically
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
