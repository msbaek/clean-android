package com.github.msbaek.rxessentials.user;

import com.github.msbaek.rxessentials.common.UseCase;

import javax.inject.Inject;

import rx.Observable;

public class GetUserList extends UseCase<UserListRequest> {
    private StackExchangeService stackExchangeService;

    @Inject
    public GetUserList(StackExchangeService stackExchangeService) {
        this.stackExchangeService = stackExchangeService;
    }

    @Override
    protected Observable getObservable(UserListRequest request) {
        return stackExchangeService.getMostPopularSOusers(request.pageNo)
                .map(UsersResponse::getUsers);
    }
}
