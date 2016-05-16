package com.github.msbaek.rxessentials;

import javax.inject.Inject;

@PerActivity
public class UserListPresenter implements Presenter {
    private UserListView view;

    @Inject
    SeApiManager mSeApiManager;

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
    }

    public void setView(UserListView view) {
        this.view = view;
    }

    public void initialize() {
        this.loadUserList(1);
    }

    public void loadUserList(int pageNo) {
        view.showRefresh(true);

        // todo: introduce interactor
        mSeApiManager.getMostPopularSOusers(pageNo) //
                .subscribe(
                        users -> {
                            view.showRefresh(false);
                            view.updateUsers(users);
                        },
                        error -> {
                            App.L.error(error.toString());
                            view.showRefresh(false);
                        }
                );
    }
}
