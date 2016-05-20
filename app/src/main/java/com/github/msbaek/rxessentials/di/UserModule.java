package com.github.msbaek.rxessentials.di;

import com.github.msbaek.rxessentials.common.mvp.UseCase;
import com.github.msbaek.rxessentials.user.domain.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import java.util.List;

@Module
public class UserModule {
    @Provides
    @PerActivity
    @Named("getUserList")
    UseCase<UserListRequest, List<User>> provideGetUserList(UserRepository userRepository) {
        return new GetUserList(userRepository);
    }

    @Provides
    @PerActivity
    @Named("getUserDetail")
    UseCase<UserDetailRequest, Object> provideGetUserDetail() {
        return new GetUserDetail();
    }
}
