package com.github.msbaek.rxessentials;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackExchangeService {
	@GET("/2.2/users?order=desc&pagesize=10&sort=reputation&site=stackoverflow")
	Call<UsersResponse> getMostPopularSOusers(@Query("page") int page);
}
