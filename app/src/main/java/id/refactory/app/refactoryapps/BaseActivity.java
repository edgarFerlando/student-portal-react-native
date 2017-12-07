package id.refactory.app.refactoryapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.fragments.FeedbackDialog;

/**
 * Created by dhanialrizky on 07/12/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //Set DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    protected abstract int getLayoutResourceId();

}
