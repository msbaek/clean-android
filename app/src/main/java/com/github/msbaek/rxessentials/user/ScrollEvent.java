package com.github.msbaek.rxessentials.user;

public class ScrollEvent {
    public int pageNo;

    public ScrollEvent(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "ScrollEvent{" +
                "pageNo=" + pageNo +
                '}';
    }
}
