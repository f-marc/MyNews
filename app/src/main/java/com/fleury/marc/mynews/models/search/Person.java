package com.fleury.marc.mynews.models.search;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("title")
    private Object title;

    public Object getTitle() {
        return title;
    }

}