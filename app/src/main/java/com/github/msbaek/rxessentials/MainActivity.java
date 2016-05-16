package com.github.msbaek.rxessentials;

import android.os.Bundle;

public class MainActivity extends BaseActivity implements HasComponent<UserComponent> {
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
