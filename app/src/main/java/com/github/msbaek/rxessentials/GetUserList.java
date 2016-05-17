package com.github.msbaek.rxessentials;

import javax.inject.Inject;

import rx.Observable;

public class GetUserList extends UseCase {
    private StackExchangeService stackExchangeService;

    @Inject
    public GetUserList(StackExchangeService stackExchangeService) {
        this.stackExchangeService = stackExchangeService;
    }

    @Override
    protected Observable getObservable() {
        return stackExchangeService.getMostPopularSOusers(1)
                .map(UsersResponse::getUsers);
    }
}
