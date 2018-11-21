package com.fleury.marc.mynews.models.search;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("docs")
    private List<Doc> docs = null;

    public List<Doc> getDocs() {
        return docs;
    }

}
