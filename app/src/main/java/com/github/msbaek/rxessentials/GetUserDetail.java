package com.github.msbaek.rxessentials;

import rx.Observable;

public class GetUserDetail extends UseCase<UserDetailRequest> {
    @Override
    protected Observable getObservable(UserDetailRequest request) {
        return Observable.empty();
    }
}
