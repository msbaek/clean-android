package com.github.msbaek.rxessentials.user.domain.event;

import com.github.msbaek.rxessentials.user.domain.User;

public class OpenProfileEvent {
    public User user;

    public OpenProfileEvent(User user) {
        this.user = user;
    }
}
