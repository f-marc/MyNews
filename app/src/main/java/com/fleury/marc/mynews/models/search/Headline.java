package com.fleury.marc.mynews.models.search;

import com.google.gson.annotations.SerializedName;

public class Headline {

    @SerializedName("main")
    private String main;

    public String getMain() {
        return main;
    }

}
