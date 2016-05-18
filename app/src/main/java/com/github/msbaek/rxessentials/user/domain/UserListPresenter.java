package com.github.msbaek.rxessentials.user.domain;

import com.github.msbaek.rxessentials.App;
import com.github.msbaek.rxessentials.common.DefaultSubscriber;
import com.github.msbaek.rxessentials.common.Presenter;
import com.github.msbaek.rxessentials.common.RxBus;
import com.github.msbaek.rxessentials.common.UseCase;
import com.github.msbaek.rxessentials.di.PerActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class UserListPresenter implements Presenter {
    private UserListView view;

    @Inject
    @Named("getUserList")
    UseCase useCase;

    @Inject
    RxBus rxBus;

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
        rxBus.send(new ScrollEvent(pageNo));
    }

    private class GetUserListSubscriber extends DefaultSubscriber<List<User>> {
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
