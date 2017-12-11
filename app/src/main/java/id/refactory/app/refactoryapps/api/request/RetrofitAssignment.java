package id.refactory.app.refactoryapps.api.request;

import java.util.List;

import id.refactory.app.refactoryapps.models.ResultAssignments;
import id.refactory.app.refactoryapps.models.UpdateAssignments;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by massam on 19/10/17.
 */

public interface RetrofitAssignment {

    @GET("/assignments")
    Call<ResultAssignments> listData (@Header("authorization") String token);

    @POST("/assignments/{id}")
    Call<UpdateAssignments> updateData (@Header("Content-Type") String contentType, @Header("authorization") String token, @Path("id") Integer id, @Body MultipartBody body);

}

