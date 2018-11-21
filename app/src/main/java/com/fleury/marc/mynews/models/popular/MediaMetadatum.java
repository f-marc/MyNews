package com.fleury.marc.mynews.models.popular;

import com.google.gson.annotations.SerializedName;

public class MediaMetadatum {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

}
