package com.github.msbaek.rxessentials;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides @PerActivity @Named("getUserList")
    UseCase provideGetUserList(GetUserList getUserList) {
        return new GetUserList();
    }

    @Provides @PerActivity @Named("getUserDetail")
    UseCase provideGetUserDetail(GetUserDetail getUserDetail) {
        return new GetUserDetail();
    }
}
