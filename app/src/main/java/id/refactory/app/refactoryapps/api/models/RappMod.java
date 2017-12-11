
package id.refactory.app.refactoryapps.api.models;

import java.util.List;

public class RappMod {

    private Boolean success;
    private List<DataAssignment> data = null;
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DataAssignment> getData() {
        return data;
    }

    public void setData(List<DataAssignment> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
