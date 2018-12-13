package com.fleury.marc.mynews.controllers.fragments.others;

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
import com.fleury.marc.mynews.models.search.Doc;
import com.fleury.marc.mynews.models.search.SearchResponse;
import com.fleury.marc.mynews.utils.ItemClickSupport;
import com.fleury.marc.mynews.utils.NYTimesStreams;
import com.fleury.marc.mynews.adapters.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.fleury.marc.mynews.controllers.activities.SearchResultActivity.KEY_BEGIN_DATE_TWO;
import static com.fleury.marc.mynews.controllers.activities.SearchResultActivity.KEY_CATEGORY_LIST_TWO;
import static com.fleury.marc.mynews.controllers.activities.SearchResultActivity.KEY_END_DATE_TWO;
import static com.fleury.marc.mynews.controllers.activities.SearchResultActivity.KEY_KEYWORD_TWO;

public class SearchResultFragment extends Fragment {

    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    private Disposable disposable;
    private List<Doc> docs;
    private SearchAdapter adapter;

    public final static String key = "061416d2a6f642c9b295500c8eadd4e3";
    public final static String KEY_URL = "KEY_URL";

    String mKeyCategory;
    String mKeyKeyword;
    String mKeyBeginDate;
    String mKeyEndDate;

    public static SearchResultFragment newInstance() {
        return new SearchResultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        mKeyCategory = bundle.getString(KEY_CATEGORY_LIST_TWO);
        mKeyKeyword = bundle.getString(KEY_KEYWORD_TWO);

        if(bundle.getString(KEY_BEGIN_DATE_TWO) != null) {
            mKeyBeginDate = bundle.getString(KEY_BEGIN_DATE_TWO);
        }

        if(bundle.getString(KEY_END_DATE_TWO) != null) {
            mKeyEndDate = bundle.getString(KEY_END_DATE_TWO);
        }

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
                        Doc article = adapter.getArticle(position);
                        // Open the WebView in a new Activity
                        Intent webViewActivityIntent = new Intent(getContext(), WebViewActivity.class);
                        webViewActivityIntent.putExtra(KEY_URL, article.getWebUrl());
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
        this.docs = new ArrayList<>();
        // 2 - Create adapter passing the list of users
        this.adapter = new SearchAdapter(this.docs, Glide.with(this));
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
        this.disposable = NYTimesStreams.streamFetchArticleSearch(key, mKeyKeyword, mKeyCategory, mKeyBeginDate, mKeyEndDate).subscribeWith(new DisposableObserver<SearchResponse>() {
            @Override
            public void onNext(SearchResponse response) {
                // Update RecyclerView after getting results from API
                updateUI(response.getResponse().getDocs());
            }

            @Override
            public void onError(Throwable e) {
            }

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

    private void updateUI(List<Doc> articles){
        // Stop refreshing and clear actual list of articles
        swipeRefreshLayout.setRefreshing(false);
        docs.clear();
        docs.addAll(articles);
        adapter.notifyDataSetChanged();
    }
}