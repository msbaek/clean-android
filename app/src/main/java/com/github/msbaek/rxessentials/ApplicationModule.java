package com.github.msbaek.rxessentials;

import android.content.Context;

import java.util.Stack;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final App app;

    // inject ???
    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.app;
    }

    @Provides
    @Singleton
    RetrofitServiceFactory provideRetrofitServiceFactory() {
        return new RetrofitServiceFactory();
    }

    @Provides
    @Singleton
    StackExchangeService provideStackExchangeService(RetrofitServiceFactory retrofitServiceFactory) {
        return retrofitServiceFactory.create("https://api.stackexchange.com", StackExchangeService.class);
    }
}
