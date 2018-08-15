package com.fleury.marc.mynews.views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.models.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTimesWorldViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title) TextView textView;
    @BindView(R.id.fragment_main_item_category) TextView textViewCategory;
    @BindView(R.id.fragment_main_item_date) TextView textViewDate;
    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    public NYTimesWorldViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNYTimesWorld(Result result, RequestManager glide){
        // Update TextView & ImageView
        this.textView.setText(result.getTitle());
        this.textViewCategory.setText(result.getSection());
        this.textViewDate.setText(result.getPublishedDate().replace("-", "/"));
        //glide.load(media.getUrl()).into(imageView);
    }
}
