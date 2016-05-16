package com.github.msbaek.rxessentials;

import java.util.List;

public interface UserListView {
    void showRefresh(boolean show);

    void updateUsers(List<User> users);
}
