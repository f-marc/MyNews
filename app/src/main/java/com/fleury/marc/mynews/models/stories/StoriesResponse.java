package com.fleury.marc.mynews.models.stories;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class StoriesResponse {

    @SerializedName("results")
    private List<StoriesResult> results = null;

    public List<StoriesResult> getResults() {
        return results;
    }

}
