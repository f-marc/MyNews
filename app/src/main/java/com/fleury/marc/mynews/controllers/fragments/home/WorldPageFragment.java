package com.fleury.marc.mynews.controllers.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.activities.WebViewActivity;
import com.fleury.marc.mynews.models.NYTimesResponse;
import com.fleury.marc.mynews.models.Result;
import com.fleury.marc.mynews.utils.ItemClickSupport;
import com.fleury.marc.mynews.utils.NYTimesStreams;
import com.fleury.marc.mynews.views.NYTimesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class WorldPageFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    // Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    //FOR DATA
    private Disposable disposable;
    private List<Result> nyTimesResponse;
    private NYTimesAdapter adapter;

    public final static String key = "061416d2a6f642c9b295500c8eadd4e3";
    public final static String KEY_URL = "KEY_URL";


    public static WorldPageFragment newInstance() {
        return new WorldPageFragment();
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
                        // Get article from adapter
                        Result article = adapter.getArticle(position);
                        // Open the WebView in a new Activity
                        Intent webViewActivityIntent = new Intent(getContext(), WebViewActivity.class);
                        webViewActivityIntent.putExtra(KEY_URL, article.getUrl());
                        startActivity(webViewActivityIntent);
                    }
                });
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 1 - Reset list
        this.nyTimesResponse = new ArrayList<>();
        // 2 - Create adapter passing the list of users
        this.adapter = new NYTimesAdapter(this.nyTimesResponse, Glide.with(this));
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
        this.disposable = NYTimesStreams.streamFetchArticleWorld(key).subscribeWith(new DisposableObserver<NYTimesResponse>() {
            @Override
            public void onNext(NYTimesResponse response) {
                // Update RecyclerView after getting results from API
                updateUI(response.getResults());
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

    private void updateUI(List<Result> articles){
        // Stop refreshing and clear actual list of articles
        swipeRefreshLayout.setRefreshing(false);
        nyTimesResponse.clear();
        nyTimesResponse.addAll(articles);
        adapter.notifyDataSetChanged();
    }
}