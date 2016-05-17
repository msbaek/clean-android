package com.github.msbaek.rxessentials;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase {
    Subscription subscription = Subscriptions.empty();
    private Scheduler mainThread;

    protected abstract Observable getObservable();

    public void execute(Subscriber UseCaseSubscriber) {
        mainThread = AndroidSchedulers.mainThread();
        this.subscription = this.getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread)
                .subscribe(UseCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
