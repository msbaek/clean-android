package com.github.msbaek.rxessentials.di;

import android.content.Context;

import com.github.msbaek.rxessentials.App;
import com.github.msbaek.rxessentials.common.RetrofitServiceFactory;
import com.github.msbaek.rxessentials.common.RxBus;
import com.github.msbaek.rxessentials.user.StackExchangeService;

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

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return new RxBus();
    }
}
