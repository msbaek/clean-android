package com.github.msbaek.rxessentials.di;

import com.github.msbaek.rxessentials.common.mvp.UseCase;
import com.github.msbaek.rxessentials.user.domain.GetUserDetail;
import com.github.msbaek.rxessentials.user.domain.GetUserList;
import com.github.msbaek.rxessentials.user.domain.UserRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class UserModule {
    @Provides
    @PerActivity
    @Named("getUserList")
    GetUserList provideGetUserList(UserRepository userRepository) {
        return new GetUserList(userRepository);
    }

    @Provides
    @PerActivity
    @Named("getUserDetail")
    UseCase<Object> provideGetUserDetail() {
        return new GetUserDetail();
    }
}
