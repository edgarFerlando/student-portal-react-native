package id.refactory.app.refactoryapps.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.api.request.RetrofitAssignment;
import id.refactory.app.refactoryapps.api.request.RetrofitConnect;
import id.refactory.app.refactoryapps.api.request.services.RequestToken;
import id.refactory.app.refactoryapps.models.UpdateAssignments;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailAssignments extends AppCompatActivity {
    public final static int PICK_IMAGE_REQUEST = 100;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    ImageView img;
    private Uri imageUri;
    private String filePath;
    private Integer idAssign;
    private String valueUpdate;
    private File file;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_assignments);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Upload Data ...");

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Integer idAssign = extras.getInt("ID");
            String statusAssign = extras.getString("status");
            String linkAssign = extras.getString("link");
            String descrptAssign = extras.getString("descryption");
            String authorAssign = extras.getString("author");


            TextView id = (TextView) findViewById(R.id.tv_id_assignment);
            TextView status = (TextView) findViewById(R.id.tv_status_assignment);
            TextView link = ( TextView) findViewById(R.id.tv_link_assignment);
            TextView descryption = ( TextView) findViewById(R.id.tv_descryption_assignment);
            TextView author = ( TextView) findViewById(R.id.tv_author_assignment);

            id.setText("ID Assignment : "+idAssign);
            status.setText("Status : "+statusAssign);
            link.setText("Link : " + linkAssign);
            descryption.setText("Descryption : "+descrptAssign);
            author.setText("Author : "+authorAssign);
            this.idAssign = idAssign;
        }

        img = (ImageView) findViewById(R.id.img_status);

        final Button button_update = ( Button ) findViewById(R.id.bt_update);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
                requestPermission();
            }
        });

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null){
                    getValue();
                }else{
                    Toast.makeText(DetailAssignments.this, "You did not choose  image attachment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getValue(){

        EditText textValue= ( EditText ) findViewById(R.id.editText_value);
        String value = textValue.getText().toString();

        if (value == null){
            this.valueUpdate = "0";
        }else{
            this.valueUpdate = value;
        }

        updateAssignment();
    }

    public void updateAssignment(){
        progressDialog.show();

        RequestToken requestToken = new RequestToken();
        String tokenId  = requestToken.getToken();

        final Integer idAssignment = idAssign;
        final String resultsValue = valueUpdate;

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)), file);
        MultipartBody resultsAssignment = new MultipartBody.Builder()
                .addFormDataPart("result_value",resultsValue)
                .addFormDataPart("result_attachments", file.getName(),requestBody)
                .build();

        RetrofitConnect retrofitConnect = new RetrofitConnect();
        Retrofit retrofitInitial = retrofitConnect.initializeRetrofit();


        RetrofitAssignment apiservice = retrofitInitial.create(RetrofitAssignment.class);
        Call<UpdateAssignments> call = apiservice.updateData("multipart/form-data; boundary=" + resultsAssignment.boundary(),tokenId,idAssignment,resultsAssignment);

        call.enqueue(new Callback<UpdateAssignments>() {
            @Override
            public void onResponse(Call<UpdateAssignments> call, Response<UpdateAssignments> response) {

                showToast(response.body().getMessage());
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UpdateAssignments> call, Throwable t) {
                Log.d("yeah", "onFailure: "+t.getMessage().toString());
            }
        });
    }

    private void showToast(String messages) {
        Toast.makeText(this, messages, Toast.LENGTH_SHORT).show();
    }

    public void openImageChooser(){
        Intent galerry = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galerry, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK && reqCode == PICK_IMAGE_REQUEST) {

            imageUri = data.getData();
            this.filePath = getRealPathFromURIPath(imageUri, this);
            this.file = new File(this.filePath);
            img.setImageURI(imageUri);

        }else {
            Toast.makeText(DetailAssignments.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String getRealPathFromURIPath(Uri imageUri, DetailAssignments detailAssignments) {
        Cursor cursor = getContentResolver().query(imageUri,null,null,null,null);
        if (cursor == null){
            return imageUri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openImageChooser();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            openImageChooser();
        }
    }


}
