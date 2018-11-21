package com.fleury.marc.mynews.models.popular;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PopularResult {

    @SerializedName("url")
    private String url;
    @SerializedName("section")
    private String section;
    @SerializedName("title")
    private String title;
    @SerializedName("published_date")
    private String publishedDate;
    @SerializedName("media")
    private List<Medium> media = null;

    public String getUrl() {
        return url;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Medium> getMedia() {
        return media;
    }

}
