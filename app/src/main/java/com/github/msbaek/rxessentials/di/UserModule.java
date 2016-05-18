package com.github.msbaek.rxessentials.di;

import com.github.msbaek.rxessentials.common.UseCase;
import com.github.msbaek.rxessentials.user.GetUserDetail;
import com.github.msbaek.rxessentials.user.GetUserList;
import com.github.msbaek.rxessentials.user.StackExchangeService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides @PerActivity
    @Named("getUserList")
    UseCase provideGetUserList(StackExchangeService stackExchangeService) {
        return new GetUserList(stackExchangeService);
    }

    @Provides @PerActivity @Named("getUserDetail")
    UseCase provideGetUserDetail(GetUserDetail getUserDetail) {
        return new GetUserDetail();
    }
}
