package com.github.msbaek.rxessentials.di;

import android.content.Context;

import com.github.msbaek.rxessentials.common.BaseActivity;
import com.github.msbaek.rxessentials.common.RetrofitServiceFactory;
import com.github.msbaek.rxessentials.common.RxBus;
import com.github.msbaek.rxessentials.user.StackExchangeService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // contraints this components to one-per-application or unscoped bindings
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    // exposed to sub-graphs
    Context context();
    RetrofitServiceFactory retrofitServiceFactory();
    StackExchangeService stackExchangeService();
    RxBus rxBus();
}
