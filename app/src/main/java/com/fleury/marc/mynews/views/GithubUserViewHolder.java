package com.fleury.marc.mynews.views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.models.GithubUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title) TextView textView;
    // 1 - Adding a TextView and an ImageView
    @BindView(R.id.fragment_main_item_website) TextView texViewWebsite;
    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    public GithubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithGithubUser(GithubUser githubUser, RequestManager glide){
        this.textView.setText(githubUser.getLogin());
        // 2 - Update TextView & ImageView
        this.texViewWebsite.setText(githubUser.getHtmlUrl());
        glide.load(githubUser.getAvatarUrl()).into(imageView);
    }
}
