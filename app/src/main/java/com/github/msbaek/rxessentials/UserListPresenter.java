package com.github.msbaek.rxessentials;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

@PerActivity
public class UserListPresenter implements Presenter {
    private UserListView view;

    @Inject @Named("getUserList")
    UseCase useCase;

    @Inject
    public UserListPresenter() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        useCase.unsubscribe();
        view = null;
    }

    public void setView(UserListView view) {
        this.view = view;
    }

    public void initialize() {
        this.loadUserList(1);
    }

    public void loadUserList(int pageNo) {
        view.showRefresh(true);
        useCase.execute(new UserListRequest(pageNo),
                new GetUserListSubscriber());
    }

    private class GetUserListSubscriber extends Subscriber<List<User>> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            App.L.error(e.toString());
            view.showRefresh(false);
        }

        @Override
        public void onNext(List<User> users) {
            view.showRefresh(false);
            view.updateUsers(users);
        }
    }
}
