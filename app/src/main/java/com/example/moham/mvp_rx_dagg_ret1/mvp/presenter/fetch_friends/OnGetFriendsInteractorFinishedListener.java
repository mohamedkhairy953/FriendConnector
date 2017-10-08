package com.example.moham.mvp_rx_dagg_ret1.mvp.presenter.fetch_friends;

import com.example.moham.mvp_rx_dagg_ret1.model.Response;
import com.example.moham.mvp_rx_dagg_ret1.model.ResponseFriends;

import java.util.List;



/**
 * Created by moham on 9/28/2017.
 */

interface OnGetFriendsInteractorFinishedListener {
    void onNetworkSucces(List<ResponseFriends> list);
    void onNetworkFailed(String error);
}
