package com.github.msbaek.rxessentials.di;

import android.app.Activity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    // exposed to sub-graphs
    Activity activity();
}
