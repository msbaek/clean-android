package com.github.msbaek.rxessentials.user.domain;

import com.github.msbaek.rxessentials.common.mvp.UseCase;
import rx.Observable;
import rx.functions.Func1;

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
        return userRepository.getMostPopularSOusers(request.pageNo).map(new Func1<UsersResponse, List<User>>() {
            @Override
            public List<User> call(UsersResponse usersResponse) {
                return usersResponse.getUsers();
            }
        });
    }
}