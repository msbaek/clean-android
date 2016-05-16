package com.github.msbaek.rxessentials;

import android.content.Context;

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
    SeApiManager provideSeApiManager(RetrofitServiceFactory retrofitServiceFactory) {
       return new SeApiManager(retrofitServiceFactory);
    }

    @Provides
    @Singleton
    RetrofitServiceFactory provideRetrofitServiceFactory() {
        return new RetrofitServiceFactory();
    }
}
