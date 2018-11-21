package com.fleury.marc.mynews.models.popular;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PopularResponse {

    @SerializedName("results")
    private List<PopularResult> results = null;

    public List<PopularResult> getResults() {
        return results;
    }

}