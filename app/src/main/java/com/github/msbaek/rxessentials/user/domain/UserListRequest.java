package com.github.msbaek.rxessentials.user.domain;

import com.github.msbaek.rxessentials.common.mvp.UseCaseRequest;

public class UserListRequest implements UseCaseRequest {
    int pageNo = 1;

    public UserListRequest(int pageNo) {
        this.pageNo = pageNo;
    }
}
