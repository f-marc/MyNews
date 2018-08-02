package com.fleury.marc.mynews.views;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.models.GithubUser;

import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserViewHolder> {

    // FOR DATA
    private List<GithubUser> githubUsers;

    // 1 - Declaring a Glide object
    private RequestManager glide;

    // 2 - Updating our constructor adding a Glide Object
    public GithubUserAdapter(List<GithubUser> githubUsers, RequestManager glide) {
        this.githubUsers = githubUsers;
        this.glide = glide;
    }

    @Override
    public GithubUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new GithubUserViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(GithubUserViewHolder viewHolder, int position) {
        // - 3 Passing the Glide object to each ViewHolder
        viewHolder.updateWithGithubUser(this.githubUsers.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.githubUsers.size();
    }

    public GithubUser getUser(int position){
        return this.githubUsers.get(position);
    }
}
