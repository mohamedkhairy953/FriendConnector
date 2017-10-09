package com.example.moham.mvp_rx_dagg_ret1.mvp.presenter.fetch_friends;

import android.widget.Button;

import com.example.moham.mvp_rx_dagg_ret1.mvp.adapter.RcycleAdapter;
import com.example.moham.mvp_rx_dagg_ret1.mvp.view.I_FriendsListView;

/**
 * Created by moham on 9/28/2017.
 */

interface I_MainActivityPresenter {

    void loadFriends();

    void onBindView(I_FriendsListView holder, int Position);

    void onItemClicked(Button btn, int Position);

    int listSize();

    void onCallClicked();

    void onSmsClicked();

    void onEmailClicked();
}
