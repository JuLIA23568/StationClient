package by.bsuir.station.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBean implements Serializable {
    protected List<Integer> editableIds = new ArrayList<Integer>();
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
