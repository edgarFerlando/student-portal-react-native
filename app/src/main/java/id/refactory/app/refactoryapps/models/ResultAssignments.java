package id.refactory.app.refactoryapps.models;


import java.util.ArrayList;

/**
 * Created by massam on 25/10/17.
 */

public class ResultAssignments {
    private String success;

    private ArrayList<DataAssignments> data;

    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<DataAssignments> getData() {
        return data;
    }

    public void setData(ArrayList<DataAssignments> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
