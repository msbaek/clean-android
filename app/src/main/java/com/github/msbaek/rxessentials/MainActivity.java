package com.github.msbaek.rxessentials;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HasComponent<UserComponent> {
    private UserComponent userComponent;
    @Inject
    RxBus rxBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInjector();

        rxBus.toObserverable().subscribe(
                o -> {
                    Toast toast = Toast.makeText(this, "MainActivity: event received [" + o + "]", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.LEFT|Gravity.TOP, 0, 0);
                    toast.show();
                }
        );

        super.changeFragment(R.id.user_list, new UserListFragment());
    }

    private void initializeInjector() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        rxBus = getApplicationComponent().rxBus();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
