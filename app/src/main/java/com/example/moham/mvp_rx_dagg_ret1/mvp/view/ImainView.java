package com.example.moham.mvp_rx_dagg_ret1.mvp.view;

import android.widget.Button;

import com.example.moham.mvp_rx_dagg_ret1.model.ResponseFriends;

import java.util.List;


/**
 * Created by moham on 9/28/2017.
 */

public interface ImainView {

    void onFriendsLoadSucces(List<ResponseFriends> list);

    void onFriendsLoadFailure(String error);

    void onItemClicked(Button btn, ResponseFriends friend);

    void onCall(ResponseFriends friends);

    void onSms(String number);

    void onEmail(String email);
}
