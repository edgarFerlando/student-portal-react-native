package id.refactory.app.refactoryapps.api.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by massam on 14/10/17.
 */

public interface RegAPI {
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/oauth/token")
    Call<AuthRequest> setCode(@Body AuthRequest auth);


}

