package com.github.msbaek.rxessentials.common.mvp;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<T extends UseCaseRequest> {
    Subscription subscription = Subscriptions.empty();
    private Scheduler mainThread;

    protected abstract Observable getObservable(T request);

    public void execute(T request, Subscriber UseCaseSubscriber) {
        mainThread = AndroidSchedulers.mainThread();
        this.subscription = this.getObservable(request)
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
