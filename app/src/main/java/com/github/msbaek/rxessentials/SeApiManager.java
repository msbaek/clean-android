package com.github.msbaek.rxessentials;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Accessors(prefix = "m")
public class SeApiManager {
    private final StackExchangeService mStackExchangeService;

    public SeApiManager() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit builder = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mStackExchangeService = builder.create(StackExchangeService.class);
    }

    public List<User> getMostPopularSOusers(int page) {
        try {
            return new AsyncTask<Integer, Void, List<User>>() {
                @Override
                protected List<User> doInBackground(Integer... params) {
                    Call<UsersResponse> mostPopularSOusers = mStackExchangeService.getMostPopularSOusers(params[0]);
                    UsersResponse body;
                    try {
                        body = mostPopularSOusers.execute().body();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return body.getUsers();
                }
            }.execute(page).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
