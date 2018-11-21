package com.fleury.marc.mynews.models.popular;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Medium {

    @SerializedName("media-metadata")
    private List<MediaMetadatum> mediaMetadata = null;

    public List<MediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }

}
