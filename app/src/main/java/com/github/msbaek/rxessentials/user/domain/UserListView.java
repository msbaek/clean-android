package com.github.msbaek.rxessentials.user.domain;

import java.util.List;

public interface UserListView {
    void showRefresh(boolean show);

    void updateUsers(List<User> users);

    void openProfile(String link);
}
