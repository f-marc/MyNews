package com.fleury.marc.mynews.models.stories;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class StoriesResult {

    @SerializedName("section")
    private String section;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("published_date")
    private String publishedDate;
    @SerializedName("multimedia")
    private List<Multimedium> multimedia = null;

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

}
