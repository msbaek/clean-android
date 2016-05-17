package com.github.msbaek.rxessentials;

import javax.inject.Inject;

import rx.Observable;

public class GetUserList extends UseCase {
    @Inject
    public GetUserList() {
    }

    @Override
    protected Observable getObservable() {
        StackExchangeService mStackExchangeService = new RetrofitServiceFactory().create("https://api.stackexchange.com", StackExchangeService.class);
        return mStackExchangeService.getMostPopularSOusers(1)
                .map(UsersResponse::getUsers);
    }
}
