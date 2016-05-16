package com.github.msbaek.rxessentials;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

public abstract class BaseActivity extends Activity {
    @Inject
    SeApiManager mSeApiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((App)getApplication()).getApplicationComponent();
    }
}
