package com.github.msbaek.rxessentials.user;

import com.github.msbaek.rxessentials.common.RetrofitServiceFactory;
import com.github.msbaek.rxessentials.user.StackExchangeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

@RunWith(RobolectricTestRunner.class)
public class StackExchangeServiceTest {
    private final StackExchangeService mStackExchangeService;

    public StackExchangeServiceTest() {
        mStackExchangeService = new RetrofitServiceFactory().create("https://api.stackexchange.com", StackExchangeService.class);
    }

    @Test
    public void getTenMostPopularSOusers() throws Exception {
        final List<User> results = new ArrayList<>();

        Observable<List<User>> users = mStackExchangeService.getMostPopularSOusers(1) //
                .map(UsersResponse::getUsers);

        users.toBlocking().forEach(results::addAll);

        System.out.printf("result's size=%d\n", results.size());
        for (User result : results) {
            System.out.println(result.getAccountId());
        }
    }
}
