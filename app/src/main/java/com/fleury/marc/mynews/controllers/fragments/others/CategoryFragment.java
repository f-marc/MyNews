package com.fleury.marc.mynews.controllers.fragments.others;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.activities.WebViewActivity;
import com.fleury.marc.mynews.models.popular.NYTimesResponse;
import com.fleury.marc.mynews.models.popular.Result;
import com.fleury.marc.mynews.utils.ItemClickSupport;
import com.fleury.marc.mynews.utils.NYTimesStreams;
import com.fleury.marc.mynews.views.PopularAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class CategoryFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    // Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    //FOR DATA
    private Disposable disposable;
    private List<Result> nyTimesResponse;
    private PopularAdapter adapter;

    public final static String key = "061416d2a6f642c9b295500c8eadd4e3";
    public final static String KEY_URL = "KEY_URL";
    int mKey;
    String category;

    public static final int FRAGMENT_ARTS = 0, FRAGMENT_BOOKS = 1, FRAGMENT_BUSINESS = 2, FRAGMENT_POLITICS = 3,
            FRAGMENT_SCIENCE = 4, FRAGMENT_SPORTS = 5, FRAGMENT_TECH = 6, FRAGMENT_TRAVEL = 7;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);

        Bundle bundle = getArguments();
        mKey = bundle.getInt("KEY_CATEGORY_TWO");
        Log.i("TEST_KEY", String.valueOf(mKey));

        this.configureRecyclerView(); // Call during UI creation
        this.configureSwipeRefreshLayout(); // Configure the SwipeRefreshLayout
        this.executeHttpRequestWithRetrofit(mKey); // Execute stream after UI creation
        this.configureOnClickRecyclerView();

        return v;
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
    private void configureOnClickRecyclerView() {
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
    private void configureRecyclerView() {
        // 1 - Reset list
        this.nyTimesResponse = new ArrayList<>();
        // 2 - Create adapter passing the list of users
        this.adapter = new PopularAdapter(this.nyTimesResponse, Glide.with(this));
        // 3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // Configure the SwipeRefreshLayout
    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit(mKey);
            }
        });
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofit(int mKey) {

        // Method for displaying the correct components.
        switch (mKey) {
            case FRAGMENT_ARTS:
                category = "art";
                break;
            case FRAGMENT_BOOKS:
                category = "books";
                break;
            case FRAGMENT_BUSINESS:
                category = "business";
                break;
            case FRAGMENT_POLITICS:
                category = "politics";
                break;
            case FRAGMENT_SCIENCE:
                category = "science";
                break;
            case FRAGMENT_SPORTS:
                category = "sports";
                break;
            case FRAGMENT_TECH:
                category = "technology";
                break;
            case FRAGMENT_TRAVEL:
                category = "travel";
                break;
        }

        this.disposable = NYTimesStreams.streamFetchArticleSearch(key, category).subscribeWith(new DisposableObserver<NYTimesResponse>() {

            @Override
            public void onNext(NYTimesResponse response) {
                // Update RecyclerView after getting results from API
                updateUI(response.getResults());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<Result> articles) {
        // Stop refreshing and clear actual list of articles
        swipeRefreshLayout.setRefreshing(false);
        nyTimesResponse.clear();
        nyTimesResponse.addAll(articles);
        adapter.notifyDataSetChanged();
    }
}
