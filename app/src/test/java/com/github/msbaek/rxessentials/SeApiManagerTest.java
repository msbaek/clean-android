package com.github.msbaek.rxessentials;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

@RunWith(RobolectricTestRunner.class)
public class SeApiManagerTest {
    private SeApiManager apiManager;

    @Before
    public void setUp() throws Exception {
        apiManager = new SeApiManager();
        apiManager.mainSchedulerThread = Schedulers.io();
    }

    @Test
    public void getTenMostPopularSOusers() throws Exception {
        List<User> results = new ArrayList<>();

        Observable<List<User>> users = apiManager.getMostPopularSOusers(1);

        users.toBlocking().forEach(results::addAll);

        System.out.printf("result's size=%d\n", results.size());
        for (User result : results) {
            System.out.println(result.getAccountId());
        }
    }
}
