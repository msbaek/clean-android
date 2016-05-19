package com.github.msbaek.rxessentials.di;

import android.content.Context;

import com.github.msbaek.rxessentials.App;
import com.github.msbaek.rxessentials.common.net.RetrofitServiceFactory;
import com.github.msbaek.rxessentials.common.rx.RxBus;
import com.github.msbaek.rxessentials.user.domain.UserRepository;

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
    UserRepository provideStackExchangeService(RetrofitServiceFactory retrofitServiceFactory) {
        return retrofitServiceFactory.create("https://api.stackexchange.com", UserRepository.class);
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return RxBus.getInstance();
    }
}
