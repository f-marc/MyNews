package com.fleury.marc.mynews.controllers.fragments.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.models.GithubUser;
import com.fleury.marc.mynews.utils.GithubStreams;
import com.fleury.marc.mynews.utils.ItemClickSupport;
import com.fleury.marc.mynews.views.GithubUserAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class MainFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView; // 1 - Declare RecyclerView
    // 1 - Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    //FOR DATA
    private Disposable disposable;
    // 2 - Declare list of users (GithubUser) & Adapter
    private List<GithubUser> githubUsers;
    private GithubUserAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView(); // Call during UI creation
        this.configureSwipeRefreshLayout(); // Configure the SwipeRefreshLayout
        this.executeHttpRequestWithRetrofit(); // Execute stream after UI creation
        this.configureOnClickRecyclerView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTION
    // -----------------

    // Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_main_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // Get user from adapter
                        GithubUser user = adapter.getUser(position);
                        // Show result in a Toast
                        Toast.makeText(getContext(), "You clicked on user : "+user.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 1 - Reset list
        this.githubUsers = new ArrayList<>();
        // 2 - Create adapter passing the list of users
        this.adapter = new GithubUserAdapter(this.githubUsers, Glide.with(this));
        // 3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // Configure the SwipeRefreshLayout
    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofit(){
        this.disposable = GithubStreams.streamFetchUserFollowing("JakeWharton").subscribeWith(new DisposableObserver<List<GithubUser>>() {
            @Override
            public void onNext(List<GithubUser> users) {
                // Update RecyclerView after getting results from Github API
                updateUI(users);
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        });
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<GithubUser> users){
        // Stop refreshing and clear actual list of users
        swipeRefreshLayout.setRefreshing(false);
        githubUsers.clear();
        githubUsers.addAll(users);
        adapter.notifyDataSetChanged();
    }
}
