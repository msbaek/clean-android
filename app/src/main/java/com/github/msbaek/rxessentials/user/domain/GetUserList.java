package com.github.msbaek.rxessentials.user.domain;

import com.github.msbaek.rxessentials.common.UseCase;

import javax.inject.Inject;

import rx.Observable;

public class GetUserList extends UseCase<UserListRequest> {
    private UserRepository userRepository;

    @Inject
    public GetUserList(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected Observable getObservable(UserListRequest request) {
        return userRepository.getMostPopularSOusers(request.pageNo)
                .map(UsersResponse::getUsers);
    }
}
