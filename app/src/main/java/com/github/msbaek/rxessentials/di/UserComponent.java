package com.github.msbaek.rxessentials.di;

import com.github.msbaek.rxessentials.user.UserDetailFragment;
import com.github.msbaek.rxessentials.user.UserListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {
    void inject(UserListFragment userListFragment);
    void inject(UserDetailFragment userDetailFragment);
}
