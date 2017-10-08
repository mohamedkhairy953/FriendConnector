package com.example.moham.mvp_rx_dagg_ret1.model;

import java.util.List;

public class Response {
    private int version;
    private List<ResponseFriends> friends;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<ResponseFriends> getFriends() {
        return this.friends;
    }

    public void setFriends(List<ResponseFriends> friends) {
        this.friends = friends;
    }
}
