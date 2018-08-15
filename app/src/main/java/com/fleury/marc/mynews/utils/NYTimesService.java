package com.fleury.marc.mynews.utils;


import com.fleury.marc.mynews.models.NYTimesResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NYTimesService {

    @GET("mostpopular/v2/mostviewed/World/1.json")
    Observable<NYTimesResponse> getWorld(@Query("api-key") String key);

    //@GET("/users/{username}")
    //Observable<GithubUserInfo> getUserInfos(@Path("username") String username);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
