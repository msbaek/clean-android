package com.github.msbaek.rxessentials.user.view;

import android.os.Bundle;

import com.github.msbaek.rxessentials.R;
import com.github.msbaek.rxessentials.common.view.BaseActivity;
import com.github.msbaek.rxessentials.di.DaggerUserComponent;
import com.github.msbaek.rxessentials.di.HasComponent;
import com.github.msbaek.rxessentials.di.UserComponent;

public class UserActivity extends BaseActivity implements HasComponent<UserComponent> {
    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInjector();

        super.changeFragment(R.id.user_list, new UserListFragment());
    }

    private void initializeInjector() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
