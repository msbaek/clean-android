package com.github.msbaek.rxessentials.user.view;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.github.msbaek.rxessentials.R;
import com.github.msbaek.rxessentials.common.BaseActivity;
import com.github.msbaek.rxessentials.common.RxBus;
import com.github.msbaek.rxessentials.di.DaggerUserComponent;
import com.github.msbaek.rxessentials.di.HasComponent;
import com.github.msbaek.rxessentials.di.UserComponent;

import javax.inject.Inject;

public class UserActivity extends BaseActivity implements HasComponent<UserComponent> {
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
                    Toast toast = Toast.makeText(this, "UserActivity: event received [" + o + "]", Toast.LENGTH_SHORT);
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
