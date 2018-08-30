package com.fleury.marc.mynews.views;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.fleury.marc.mynews.R;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<NYTimesViewHolder> {

    // FOR DATA
    private List<com.fleury.marc.mynews.models.stories.Result> newsList;

    // 1 - Declaring a Glide object
    private RequestManager glide;

    // 2 - Updating our constructor adding a Glide Object
    public StoriesAdapter(List<com.fleury.marc.mynews.models.stories.Result> newsList, RequestManager glide) {
        this.newsList = newsList;
        this.glide = glide;
    }

    @Override
    public NYTimesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new NYTimesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH AN ARTICLE
    @Override
    public void onBindViewHolder(NYTimesViewHolder viewHolder, int position) {
        // Passing the Glide object to each ViewHolder
        viewHolder.updateWithStories(this.newsList.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.newsList.size();
    }

    public com.fleury.marc.mynews.models.stories.Result getArticle(int position){
        return this.newsList.get(position);
    }
}
