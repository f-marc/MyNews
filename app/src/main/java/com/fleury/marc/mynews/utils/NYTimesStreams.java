package com.fleury.marc.mynews.utils;


import com.fleury.marc.mynews.models.GithubUserInfo;
import com.fleury.marc.mynews.models.NYTimesResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTimesStreams {


    public static Observable<NYTimesResponse> streamFetchArticleWorld(String key){
        NYTimesService nyTimesService = NYTimesService.retrofit.create(NYTimesService.class);
        return nyTimesService.getWorld(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /*public static Observable<GithubUserInfo> streamFetchUserInfos(String username){
        GithubService gitHubService = GithubService.retrofit.create(GithubService.class);
        return gitHubService.getUserInfos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }


     public static Observable<GithubUserInfo> streamFetchUserFollowingAndFetchFirstUserInfos(String username){
        return streamFetchUserFollowing(username) // A.
                .map(new Function<List<GithubUser>, GithubUser>() {
                    @Override
                    public GithubUser apply(List<GithubUser> users) throws Exception {
                        return users.get(0); // B.
                    }
                })
                .flatMap(new Function<GithubUser, Observable<GithubUserInfo>>() {
                    @Override
                    public Observable<GithubUserInfo> apply(GithubUser user) throws Exception {
                        // C.
                        return streamFetchUserInfos(user.getLogin());
                    }
                });
    } */

}