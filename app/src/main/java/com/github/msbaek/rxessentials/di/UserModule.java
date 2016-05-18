package com.github.msbaek.rxessentials.di;

import com.github.msbaek.rxessentials.common.UseCase;
import com.github.msbaek.rxessentials.user.domain.GetUserDetail;
import com.github.msbaek.rxessentials.user.domain.GetUserList;
import com.github.msbaek.rxessentials.user.domain.UserRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides @PerActivity
    @Named("getUserList")
    UseCase provideGetUserList(UserRepository userRepository) {
        return new GetUserList(userRepository);
    }

    @Provides @PerActivity @Named("getUserDetail")
    UseCase provideGetUserDetail(GetUserDetail getUserDetail) {
        return new GetUserDetail();
    }
}
