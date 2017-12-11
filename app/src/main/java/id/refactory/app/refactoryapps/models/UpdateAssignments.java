package id.refactory.app.refactoryapps.models;

import android.provider.ContactsContract;


/**
 * Created by massam on 25/10/17.
 */

public class UpdateAssignments {
    private String success;

    private ContactsContract.Data data;

    private String message;


    private String contentValue;

    private String resultAttachments;

    public UpdateAssignments(String value, String name){
        this.contentValue = value;
        this.resultAttachments = name;

    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ContactsContract.Data getData() {
        return data;
    }

    public void setData(ContactsContract.Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
