package com.github.msbaek.rxessentials.common.rx;

import rx.Subscriber;

public class DefaultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T t) {
    }
}
