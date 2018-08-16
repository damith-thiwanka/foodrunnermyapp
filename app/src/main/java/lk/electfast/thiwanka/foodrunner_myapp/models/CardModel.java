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

    public CardModel(String views, String downloads, String location, String urls) {
        this.views = views;
        this.downloads = downloads;
        this.location = location;
        this.urls = urls;
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
}
