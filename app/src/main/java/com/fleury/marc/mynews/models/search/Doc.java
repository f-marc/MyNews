package com.fleury.marc.mynews.models.search;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Doc {

    @SerializedName("web_url")
    private String webUrl;
    @SerializedName("multimedia")
    private List<Multimedium> multimedia = null;
    @SerializedName("headline")
    private Headline headline;
    @SerializedName("pub_date")
    private String pubDate;
    @SerializedName("section_name")
    private String sectionName;

    public String getWebUrl() {
        return webUrl;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSectionName() {
        return sectionName;
    }

}