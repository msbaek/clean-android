package com.github.msbaek.rxessentials;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides @PerActivity
    GetUserListUseCase provideGetUserListUseCase(GetUserListUseCaseImpl getUserListUseCase) {
        return getUserListUseCase;
    }

    @Provides @PerActivity
    GetUserDetailUseCase provideGetUserDetailUseCase(GetUserDetailUseCaseImpl getUserDetailUseCase) {
        return getUserDetailUseCase;
    }
}
