package com.fleury.marc.mynews.models.search;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

}