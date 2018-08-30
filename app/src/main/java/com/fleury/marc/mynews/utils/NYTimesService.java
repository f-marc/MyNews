package com.fleury.marc.mynews.utils;


import com.fleury.marc.mynews.models.popular.NYTimesResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NYTimesService {

    @GET("topstories/v2/home.json")
    Observable<com.fleury.marc.mynews.models.stories.NYTimesResponse> getStories(@Query("api-key") String key);

    @GET("mostpopular/v2/mostviewed/all-sections/1.json")
    Observable<NYTimesResponse> getPopular(@Query("api-key") String key);

    @GET("mostpopular/v2/mostviewed/World/1.json")
    Observable<NYTimesResponse> getWorld(@Query("api-key") String key);

    @GET("search/v2/articlesearch.json")
    Observable<NYTimesResponse> getSearch(@Query("api-key") String key, @Query("facet_field") String category);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
