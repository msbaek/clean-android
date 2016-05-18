package com.github.msbaek.rxessentials.di;

import com.github.msbaek.rxessentials.user.view.UserDetailFragment;
import com.github.msbaek.rxessentials.user.view.UserListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {
    void inject(UserListFragment userListFragment);

    void inject(UserDetailFragment userDetailFragment);
}
