package id.refactory.app.refactoryapps;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.api.services.ApiClient;
import id.refactory.app.refactoryapps.api.services.AuthRequest;
import id.refactory.app.refactoryapps.api.services.RegAPI;
import id.refactory.app.refactoryapps.sessions.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GitLogin extends AppCompatActivity {

    private Dialog MyDialog;
    @BindView(R.id.btn_login) Button webDialog;
    private WebView loginView;

    // RegAPI directly from Dagger
    @Inject RegAPI api;

    LoadListener loadlistener = new LoadListener();

    ApiClient apiClient = new ApiClient();
    String url = apiClient.getUri();

    SessionManager session;

    Calendar currentTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getTokenDetails();

        String token = user.get(SessionManager.KEY_NAME);
        String expired = user.get(SessionManager.EXPIRED);
        String times = user.get(SessionManager.TIME_NOW);

        //getTime
        SimpleDateFormat dfHours = new SimpleDateFormat("HH");
        String hours = dfHours.format(currentTime.getTime());

        SimpleDateFormat dfMinute = new SimpleDateFormat("mm");
        String minute = dfMinute.format(currentTime.getTime());

        SimpleDateFormat dfSecond = new SimpleDateFormat("ss");
        String second = dfSecond.format(currentTime.getTime());

        // convert to Integer
        Integer cHours = Integer.parseInt(hours);
        Integer cMinutes = Integer.parseInt(minute);
        Integer cSeconds = Integer.parseInt(second);
        Integer tokenExpired = Integer.parseInt(expired);
        Integer timesExpired = Integer.parseInt(times);

        Integer currentMilliseconds = (cHours*1000*60*60) + (cMinutes * 1000 * 60 ) + (cSeconds * 60);
        Integer lastMilliseconds = timesExpired + tokenExpired;

        // Check token
        if (token != null) {

            if (lastMilliseconds >= currentMilliseconds) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            } else {

                webDialog.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialogWebview();
                    }
                });

            }
        }else {
            webDialog.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    dialogWebview();
                }
            });
        }

        RefactoryApplication.get(this).getApplicationComponent().inject(this);
    }

    public void dialogWebview(){
        loginView = new WebView(this);
        loginView.getSettings().setJavaScriptEnabled(true);
        loginView.getSettings().setDomStorageEnabled(true);
        loginView.getSettings().setDatabaseEnabled(true);
        loginView.getSettings().setAppCacheEnabled(true);
        loginView.addJavascriptInterface(this.loadlistener,"HTMLOUT");

        loginView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String Url) {

                if(Url.contains("https://app.refactory.id/callback?code=")) {
                    gitCode(Url);
                } else {
                    Log.d("False not yet find", "");
                }

                return false;
            }

            @Override
            public void onPageStarted(WebView view, String Url, Bitmap favicon){

            }

            public void onPageFinished(WebView view, String Url){
                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");

            }

        });

        loginView.loadUrl(url);

        String getURL = loginView.getUrl();
        this.MyDialog = new Dialog(this);
        this.MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE );
        this.MyDialog.setContentView(loginView);
        this.MyDialog.setTitle("Login With Github");
        this.MyDialog.show();

    }

    public void gitCode(String code){

        String currentString = code;
        String[] codeSplit = currentString.split("=");
        String Code = codeSplit[1];

        getToken(Code);
    }

    public Integer timeNow(){
        //get time for save to sharedPreference
        SimpleDateFormat dfHours = new SimpleDateFormat("HH");
        String hours = dfHours.format(currentTime.getTime());

        SimpleDateFormat dfMinute = new SimpleDateFormat("mm");
        String minute = dfMinute.format(currentTime.getTime());

        SimpleDateFormat dfSecond = new SimpleDateFormat("ss");
        String second = dfSecond.format(currentTime.getTime());

        // convert to Integer
        Integer cHours = Integer.parseInt(hours);
        Integer cMinutes = Integer.parseInt(minute);
        Integer cSeconds = Integer.parseInt(second);

        Integer timeMilliseconds = (cHours*1000*60*60) + (cMinutes * 1000 * 60 ) + (cSeconds * 60);

        return timeMilliseconds;
    }

    public void getToken(String codeGet){

        String code = codeGet;

//        ApiClient apiClient = new ApiClient();
//        String URL = apiClient.getURL();
        String grant_type = apiClient.getGrantType();
        String client_id = apiClient.getClient_id();
        String client_secret = apiClient.getClientSecret();
        final String redirect_uri = apiClient.redirectUri();

        final AuthRequest auth = new AuthRequest(code, grant_type, client_id, client_secret, redirect_uri);

    // generic type bisa bebas diisi denngan nama apa saja Call <AuthRequest> dll
        Call<AuthRequest> call = api.setCode(auth);

        call.enqueue(new Callback<AuthRequest>() {

            @Override
            public void onResponse(Call<AuthRequest> call, Response<AuthRequest> authRequest) {
                String accessToken = authRequest.body().getAccessToken();
                Integer expiresToken = authRequest.body().getExpiresIn();
                String refreshToken = authRequest.body().getRefreshToken();
                String tokenType = authRequest.body().getTokenType();

                session.createLogin(accessToken,expiresToken,refreshToken,tokenType,timeNow());
                MyDialog.dismiss();

                // Intent Untuk masuk ke dashboard setalah login via github
                //Intent i = new Intent(getApplicationContext(), Dashboard.class);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<AuthRequest> call, Throwable t) {
                Log.d("Message", "onFailure: " + t.getMessage());
            }

        });

    }

    // dipakai dimana ?
    public void onBackPressPed() {
        if (loginView.canGoBack()){
            loginView.goBack();
        }else {
            super.onBackPressed();
        }
    }

}

