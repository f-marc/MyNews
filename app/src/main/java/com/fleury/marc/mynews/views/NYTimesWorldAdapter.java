package com.fleury.marc.mynews.views;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.models.Result;

import java.util.List;

public class NYTimesWorldAdapter extends RecyclerView.Adapter<NYTimesWorldViewHolder> {

    // FOR DATA
    private List<Result> newsList;

    // 1 - Declaring a Glide object
    private RequestManager glide;

    // 2 - Updating our constructor adding a Glide Object
    public NYTimesWorldAdapter(List<Result> nyTimesWorld, RequestManager glide) {
        this.newsList = nyTimesWorld;
        this.glide = glide;
    }

    @Override
    public NYTimesWorldViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new NYTimesWorldViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH AN ARTICLE
    @Override
    public void onBindViewHolder(NYTimesWorldViewHolder viewHolder, int position) {
        // - 3 Passing the Glide object to each ViewHolder
        viewHolder.updateWithNYTimesWorld(this.newsList.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.newsList.size();
    }

    public Result getArticle(int position){
        return this.newsList.get(position);
    }
}
