package com.github.msbaek.rxessentials.user.domain;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface UserRepository {
    @GET("/2.2/users?order=desc&pagesize=10&sort=reputation&site=stackoverflow")
    Observable<UsersResponse> getMostPopularSOusers(@Query("page") int page);
}
