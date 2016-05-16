package com.github.msbaek.rxessentials;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {
    void inject(UserListFragment userListFragment);
    void inject(UserDetailFragment userDetailFragment);
}
