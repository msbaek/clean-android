package com.github.msbaek.rxessentials;

public class UserListRequest implements UseCaseRequest {
    int pageNo = 1;

    public UserListRequest(int pageNo) {
        this.pageNo = pageNo;
    }
}
