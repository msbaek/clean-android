package com.github.msbaek.rxessentials.user;

import com.github.msbaek.rxessentials.common.net.RetrofitServiceFactory;
import com.github.msbaek.rxessentials.user.domain.User;
import com.github.msbaek.rxessentials.user.domain.UserRepository;
import com.github.msbaek.rxessentials.user.domain.UsersResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

@RunWith(RobolectricTestRunner.class)
public class UserRepositoryTest {
    private final UserRepository mUserRepository;

    public UserRepositoryTest() {
        mUserRepository = new RetrofitServiceFactory().create("https://api.stackexchange.com", UserRepository.class);
    }

    @Test
    public void getTenMostPopularSOusers() throws Exception {
        final List<User> results = new ArrayList<>();

        Observable<List<User>> users = mUserRepository.getMostPopularSOusers(1) //
        .map(new Func1<UsersResponse, List<User>>() {
            @Override
            public List<User> call(UsersResponse usersResponse) {
                return usersResponse.getUsers();
            }
        });

        users.toBlocking().forEach(new Action1<List<User>>() {
            @Override
            public void call(List<User> users) {
                results.addAll(users);
            }
        });

        System.out.printf("result's size=%d\n", results.size());
        for (User result : results) {
            System.out.println(result.getAccountId());
        }
    }
}
