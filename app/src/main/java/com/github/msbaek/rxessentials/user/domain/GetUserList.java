package com.github.msbaek.rxessentials.user.domain;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.github.msbaek.rxessentials.common.mvp.UseCase;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import javax.inject.Inject;
import java.util.List;

public class GetUserList extends UseCase<List<User>> {
    private UserRepository userRepository;
    private PublishSubject<Observable<List<User>>> publishSubject = PublishSubject.create();
    private int page = 1;

    @Inject
    public GetUserList(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @RxLogObservable
    protected Observable<List<User>> buildUseCaseObservable() {
        return Observable.switchOnNext(publishSubject);
    }
    @RxLogObservable
    private Observable<List<User>> nextPage(Integer pageNo) {
        return userRepository.getMostPopularSOusers(pageNo).map(UsersResponse::getUsers).subscribeOn(Schedulers.io());
    }

    public void next() {
        publishSubject.onNext(nextPage(page++));
    }
}