package com.github.msbaek.rxessentials.user.domain;

import com.github.msbaek.rxessentials.common.mvp.UseCase;
import rx.Observable;

public class GetUserDetail extends UseCase<UserDetailRequest, Object> {

    @Override
    protected Observable<Object> getObservable(UserDetailRequest request) {
        return Observable.empty();
    }
}
