package lk.electfast.thiwanka.foodrunner_myapp.models;

import com.google.gson.annotations.SerializedName;

public class CardModel {
    @SerializedName("views")
    String views;
    @SerializedName("downloads")
    String downloads;
    @SerializedName("location")
    String location;
    @SerializedName("urls")
    String urls;
    @SerializedName("urls_m")
    String urls_m;

    public CardModel(String views, String downloads, String location, String urls, String urls_m) {
        this.views = views;
        this.downloads = downloads;
        this.location = location;
        this.urls = urls;
        this.urls_m = urls_m;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getUrls_m() {
        return urls_m;
    }

    public void setUrls_m(String urls_m) {
        this.urls_m = urls_m;
    }
}
