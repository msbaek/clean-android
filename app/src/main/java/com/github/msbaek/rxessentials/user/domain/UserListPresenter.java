package com.github.msbaek.rxessentials.user.domain;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.github.msbaek.rxessentials.App;
import com.github.msbaek.rxessentials.common.mvp.Presenter;
import com.github.msbaek.rxessentials.common.rx.DefaultSubscriber;
import com.github.msbaek.rxessentials.di.PerActivity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@PerActivity
public class UserListPresenter implements Presenter {
    public UserListView view;

    @Inject
    @Named("getUserList")
    GetUserList useCase;

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

    public void viewCreated(UserListView view) {
        this.view = view;
        useCase.execute(new GetUserListSubscriber());
    }

    public void initialize() {
        useCase.initialize();
    }

    public void loadUserList() {
        useCase.next();
    }

    public void openProfile(User user) {
        String url = user.getWebsiteUrl();
        if (url != null && !url.equals("") && !url.contains("search")) {
            view.openProfile(url);
        } else {
            view.openProfile(user.getLink());
        }
    }

    @RxLogSubscriber
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
