package com.fleury.marc.mynews.utils;


import com.fleury.marc.mynews.models.popular.PopularResponse;
import com.fleury.marc.mynews.models.search.SearchResponse;
import com.fleury.marc.mynews.models.stories.StoriesResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTimesStreams {


    public static Observable<StoriesResponse> streamFetchArticleStories(String key){
        NYTimesService nyTimesService = NYTimesService.retrofit.create(NYTimesService.class);
        return nyTimesService.getStories(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<PopularResponse> streamFetchArticlePopular(String key){
        NYTimesService nyTimesService = NYTimesService.retrofit.create(NYTimesService.class);
        return nyTimesService.getPopular(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<PopularResponse> streamFetchArticleWorld(String key){
        NYTimesService nyTimesService = NYTimesService.retrofit.create(NYTimesService.class);
        return nyTimesService.getWorld(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<PopularResponse> streamFetchArticleSection(String section, String key){
        NYTimesService nyTimesService = NYTimesService.retrofit.create(NYTimesService.class);
        return nyTimesService.getSection(section, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<SearchResponse> streamFetchArticleSearch(String section, String key){
        NYTimesService nyTimesService = NYTimesService.retrofit.create(NYTimesService.class);
        return nyTimesService.getSearch(section, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}