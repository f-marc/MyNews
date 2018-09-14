package com.fleury.marc.mynews.views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.models.popular.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTimesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title) TextView textView;
    @BindView(R.id.fragment_main_item_category) TextView textViewCategory;
    @BindView(R.id.fragment_main_item_date) TextView textViewDate;
    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    public NYTimesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithPopular(Result result, RequestManager glide){
        // Update TextView & ImageView
        this.textView.setText(result.getTitle());
        this.textViewCategory.setText(result.getSection());
        this.textViewDate.setText(result.getPublishedDate().replace("-", "/"));
        glide.load(result.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageView);
    }

    public void updateWithStories(com.fleury.marc.mynews.models.stories.Result result, RequestManager glide){
        // Update TextView & ImageView
        this.textView.setText(result.getTitle());
        this.textViewCategory.setText(result.getSection());
        this.textViewDate.setText(result.getPublishedDate().replace("-", "/"));
        //glide.load(result.getMultimedia().get(0).getUrl()).into(imageView);
    }
}
