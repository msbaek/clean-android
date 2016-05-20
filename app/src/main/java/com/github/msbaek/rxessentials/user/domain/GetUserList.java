package com.github.msbaek.rxessentials.user.domain;

import com.github.msbaek.rxessentials.common.mvp.UseCase;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

public class GetUserList extends UseCase<UserListRequest, List<User>> {
    private UserRepository userRepository;

    @Inject
    public GetUserList(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<List<User>> getObservable(UserListRequest request) {
        return userRepository.getMostPopularSOusers(request.pageNo).map(UsersResponse::getUsers);
    }
}