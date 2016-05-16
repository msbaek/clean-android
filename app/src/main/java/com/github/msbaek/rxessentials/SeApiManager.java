package com.github.msbaek.rxessentials;

import java.util.List;

import lombok.experimental.Accessors;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Accessors(prefix = "m")
public class SeApiManager {
    private final StackExchangeService mStackExchangeService;

    Scheduler mainSchedulerThread;

    public SeApiManager(RetrofitServiceFactory retrofitServiceFactory) {
        mStackExchangeService = retrofitServiceFactory.create("https://api.stackexchange.com", StackExchangeService.class);
        mainSchedulerThread = AndroidSchedulers.mainThread();
    }

    public Observable<List<User>> getMostPopularSOusers(int howmany) {
        return mStackExchangeService
                .getMostPopularSOusers(howmany)
                .map(UsersResponse::getUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(mainSchedulerThread);
    }
}
