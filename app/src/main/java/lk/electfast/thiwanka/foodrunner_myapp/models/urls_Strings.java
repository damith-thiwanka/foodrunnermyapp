package lk.electfast.thiwanka.foodrunner_myapp.models;

import com.google.gson.annotations.SerializedName;

public class urls_Strings {
    @SerializedName("small")
    String small;

    public urls_Strings(String small) {
        this.small = small;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
