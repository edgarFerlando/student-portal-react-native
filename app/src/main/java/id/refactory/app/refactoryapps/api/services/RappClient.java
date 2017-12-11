package id.refactory.app.refactoryapps.api.services;

import java.util.List;

import id.refactory.app.refactoryapps.api.models.RappMod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by prana on 06/10/17.
 */

public interface RappClient {

    // @GET salah satu annotation / anotasi retrofit untuk merequest ke HTTTP



    @GET("/assignments")
    Call<RappMod> listData(@Header("Authorization") String AksesToken);


}
