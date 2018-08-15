package lk.electfast.thiwanka.foodrunner_myapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardItem {

    @SerializedName("views")
    String views;
    @SerializedName("downloads")
    String downloads;
    @SerializedName("location")
    Location location;
    @SerializedName("urls")
    urls_Strings urls;

    public CardItem(String views, String downloads, Location location, urls_Strings urls) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public urls_Strings getUrls() {
        return urls;
    }

    public void setUrls(urls_Strings urls) {
        this.urls = urls;
    }
}
