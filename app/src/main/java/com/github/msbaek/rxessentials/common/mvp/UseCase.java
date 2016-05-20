package com.github.msbaek.rxessentials.common.mvp;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<T extends UseCaseRequest, R> {
    private Subscription subscription = Subscriptions.empty();

    protected abstract Observable<R> getObservable(T request);

    public void execute(T request, Subscriber<R> useCaseSubscriber) {
        this.subscription = this.getObservable(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
