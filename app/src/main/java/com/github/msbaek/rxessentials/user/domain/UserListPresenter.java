package com.github.msbaek.rxessentials.user.domain;

import com.github.msbaek.rxessentials.App;
import com.github.msbaek.rxessentials.common.mvp.Presenter;
import com.github.msbaek.rxessentials.common.mvp.UseCase;
import com.github.msbaek.rxessentials.common.rx.DefaultSubscriber;
import com.github.msbaek.rxessentials.di.PerActivity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@PerActivity
public class UserListPresenter implements Presenter {
    private UserListView view;

    @Inject
    @Named("getUserList")
    UseCase<UserListRequest, List<User>> useCase;

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
        useCase.execute(new UserListRequest(pageNo), new GetUserListSubscriber());
    }

    public void openProfile(User user) {
        String url = user.getWebsiteUrl();
        if (url != null && !url.equals("") && !url.contains("search")) {
            view.openProfile(url);
        } else {
            view.openProfile(user.getLink());
        }
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
