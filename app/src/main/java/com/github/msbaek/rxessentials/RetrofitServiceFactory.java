package com.github.msbaek.rxessentials;

import retrofit.RestAdapter;

class RetrofitServiceFactory {
    public <T> T create(String endpoint, Class<T> serviceClass) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build();
        return restAdapter.create(serviceClass);
    }
}
