package com.fleury.marc.mynews.views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.models.popular.Medium;
import com.fleury.marc.mynews.models.popular.PopularResult;
import com.fleury.marc.mynews.models.search.Doc;
import com.fleury.marc.mynews.models.stories.StoriesResult;

import java.util.List;

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

    public void updateWithPopular(PopularResult result, RequestManager glide){
        // Update TextView & ImageView
        this.textView.setText(result.getTitle());
        this.textViewCategory.setText(result.getSection());
        this.textViewDate.setText(result.getPublishedDate().replace("-", "/"));
        /*if(result.getMedia() instanceof String) {
        } else {
            List<Medium> mediums = (List<Medium>) result.getMedia();
            glide.load(mediums.get(0).getMediaMetadata().get(0).getUrl()).into(imageView);
        }*/
        if(result.getMedia().get(0).getMediaMetadata() != null && result.getMedia().get(0).getMediaMetadata().size() > 0) {
            glide.load(result.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageView);
        }
    }

    public void updateWithStories(StoriesResult result, RequestManager glide){
        // Update TextView & ImageView
        this.textView.setText(result.getTitle());
        this.textViewCategory.setText(result.getSection());
        String mDate = result.getPublishedDate();
        String subDate = mDate.substring(0, mDate.indexOf("T"));
        this.textViewDate.setText(subDate.replace("-", "/"));
        if(result.getMultimedia() != null && result.getMultimedia().size() > 0) {
            glide.load(result.getMultimedia().get(0).getUrl()).into(imageView);
        }
    }

    public void updateWithSearch(Doc result, RequestManager glide){
        // Update TextView & ImageView
        this.textView.setText(result.getHeadline().getMain());
        //this.textViewCategory.setText(result.getSection());
        String mDate = result.getPubDate();
        String subDate = mDate.substring(0, mDate.indexOf("T"));
        this.textViewDate.setText(subDate.replace("-", "/"));
        if(result.getMultimedia() != null && result.getMultimedia().size() > 0) {
            glide.load(result.getMultimedia().get(0).getUrl()).into(imageView);
        }
    }
}
