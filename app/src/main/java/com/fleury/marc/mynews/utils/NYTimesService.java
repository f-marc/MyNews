package com.fleury.marc.mynews.utils;


import com.fleury.marc.mynews.models.popular.PopularResponse;
import com.fleury.marc.mynews.models.search.SearchResponse;
import com.fleury.marc.mynews.models.stories.StoriesResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NYTimesService {

    @GET("topstories/v2/home.json")
    Observable<StoriesResponse> getStories(@Query("api-key") String key);

    @GET("mostpopular/v2/mostviewed/all-sections/1.json")
    Observable<PopularResponse> getPopular(@Query("api-key") String key);

    @GET("mostpopular/v2/mostviewed/World/1.json")
    Observable<PopularResponse> getWorld(@Query("api-key") String key);

    @GET("mostpopular/v2/mostviewed/{section}/1.json")
    Observable<PopularResponse> getSection(@Path("section") String section, @Query("api-key") String key);

    @GET("search/v2/articlesearch.json")
    Observable<SearchResponse> getSearch(@Query("news_desk") String section, @Query("api-key") String key);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
