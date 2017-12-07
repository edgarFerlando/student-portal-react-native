package id.refactory.app.refactoryapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;

import id.refactory.app.refactoryapps.fragments.OverviewFragment;
import id.refactory.app.refactoryapps.sessions.SessionManager;

public class MainActivity extends BaseActivity implements OverviewFragment.OnFragmentInteractionListener {

    @Override
    protected int getLayoutResourceId() {
//        This is change how usually using setContentView() to be more compatible with BaseActivity
        return R.layout.activity_main;
    }

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        // set session
        SessionManager session = new SessionManager(getApplicationContext());

        //get user data from session
        HashMap<String, String> user = session.getTokenDetails();
        // toast awareness user login
        Toast.makeText(getApplicationContext(),"User Login Status : "+ session.loggedIn()+" ", Toast.LENGTH_SHORT).show();
        session.checkLogin();
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
    
    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
