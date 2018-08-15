package lk.electfast.thiwanka.foodrunner_myapp.models;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("title")
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
