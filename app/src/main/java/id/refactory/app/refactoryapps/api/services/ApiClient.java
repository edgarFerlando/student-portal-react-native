package id.refactory.app.refactoryapps.api.services;

import android.util.Log;

/**
 * Created by massam on 14/10/17.
 */

public class ApiClient {
    private String code;
    private String URL = "https://api.refactory.id/";
    private String grant_type = "authorization_code";
    private String client_id = "4";
    private String client_secret = "3fd37387ea110499059222a6ed1fa235";
    private final String redirect_uri = "https://app.refactory.id/callback";
    private String uri = "https://app.refactory.id/oauth/authorize?client_id=4&response_type=code&redirect_uri=https://app.refactory.id/callback&scope=";

    public void setCode(String newCode){
        code = newCode;
    }
    public String getCode(){
        return code;
    }

    public String getURL(){
        return URL;
    }

    public String getGrantType(){
        return grant_type;
    }

    public String getClient_id(){
        return client_id;
    }

    public String getClientSecret(){
        return client_secret;
    }

    public String redirectUri(){
        return redirect_uri;
    }

    public String getUri(){
        return uri;
    }

}

