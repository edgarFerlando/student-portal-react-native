package id.refactory.app.refactoryapps.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.refactory.app.refactoryapps.R;
import id.refactory.app.refactoryapps.api.request.RetrofitAssignment;
import id.refactory.app.refactoryapps.models.UpdateAssignments;
import id.refactory.app.refactoryapps.sessions.SessionManager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailAssignmentFragment extends Fragment {

    public final static int PICK_IMAGE_REQUEST = 100;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    private Uri imageUri;
    private String filePath;
    private Integer idAssign;
    private String valueUpdate;
    private File file;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    @Inject
    Retrofit retrofit;

    @BindView(R.id.tv_id_assignment) TextView id;
    @BindView(R.id.tv_status_assignment) TextView status;
    @BindView(R.id.tv_link_assignment) TextView link;
    @BindView(R.id.tv_descryption_assignment) TextView descryption;
    @BindView(R.id.tv_author_assignment) TextView author;
    @BindView(R.id.img_status) ImageView img;
    @BindView(R.id.bt_update) Button button_update;
    @BindView(R.id.editText_value) EditText textValue;

    private Unbinder unbinder;

    public DetailAssignmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail_assignment, container, false);
        final View view = inflater.inflate(R.layout.fragment_detail_assignment, container, false);
        unbinder = ButterKnife.bind(this, view);
//        RefactoryApplication.get(this).getApplicationComponent().inject(this);

        //progressDialog = new ProgressDialog(this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Upload Data ...");

//      Bundle extras = getIntent().getExtras();
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null){
            Integer idAssign = extras.getInt("ID");
            String statusAssign = extras.getString("status");
            String linkAssign = extras.getString("link");
            String descrptAssign = extras.getString("descryption");
            String authorAssign = extras.getString("author");

            id.setText("ID Assignment : "+idAssign);
            status.setText("Status : "+statusAssign);
            link.setText("Link : " + linkAssign);
            descryption.setText("Descryption : "+descrptAssign);
            author.setText("Author : "+authorAssign);
            this.idAssign = idAssign;
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null){
                    getValue();
                }else{
//                    Toast.makeText(this, "You did not choose  image attachment", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "You did not choose  image attachment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void getValue(){

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

//        sessionManager = new SessionManager(getApplicationContext());
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getTokenDetails();

        String tokenId = "Bearer " + user.get(SessionManager.KEY_NAME);

        final Integer idAssignment = idAssign;
        final String resultsValue = valueUpdate;

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(imageUri)), file);
        MultipartBody resultsAssignment = new MultipartBody.Builder()
                .addFormDataPart("result_value",resultsValue)
                .addFormDataPart("result_attachments", file.getName(),requestBody)
                .build();

        RetrofitAssignment apiservice = retrofit.create(RetrofitAssignment.class);
        Call<UpdateAssignments> call = apiservice.updateData("multipart/form-data; boundary=" + resultsAssignment.boundary(),tokenId,idAssignment,resultsAssignment);

        call.enqueue(new Callback<UpdateAssignments>() {
            @Override
            public void onResponse(Call<UpdateAssignments> call, Response<UpdateAssignments> response) {
                Log.d("Response", "onResponse: "+ response);
                if(response.isSuccessful()){
                    showToast(response.body().getMessage());
                }else{
                    showToast("Upload data error !");
                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UpdateAssignments> call, Throwable t) {
                Log.d("yeah", "onFailure: "+t.getMessage().toString());
            }
        });
    }

    private void showToast(String messages) {
//        Toast.makeText(this, messages, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), messages, Toast.LENGTH_SHORT).show();
    }

    public void openImageChooser(){
        Intent galerry = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galerry, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK && reqCode == PICK_IMAGE_REQUEST) {

            imageUri = data.getData();
//            this.filePath = getRealPathFromURIPath(imageUri, this);
            this.filePath = getRealPathFromURIPath(imageUri, getActivity());
            this.file = new File(this.filePath);
            this.img.setImageURI(imageUri);

        }else {
           // Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String getRealPathFromURIPath(Uri imageUri, FragmentActivity detailAssignments) {
        Cursor cursor = getActivity().getContentResolver().query(imageUri,null,null,null,null);
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

    /*
    public void onBackPressed(){
        Intent view = new Intent(getApplication(),Assignments.class);
        startActivity(view);
        finish();
    }
*/

}
