package com.example.moham.mvp_rx_dagg_ret1.mvp.presenter.fetch_friends;

import android.widget.Button;

import com.example.moham.mvp_rx_dagg_ret1.model.ResponseFriends;
import com.example.moham.mvp_rx_dagg_ret1.mvp.view.I_FriendsListView;
import com.example.moham.mvp_rx_dagg_ret1.mvp.view.ImainView;

import java.util.List;


/**
 * Created by moham on 9/28/2017.
 */

public class MainActivityPresenter implements I_MainActivityPresenter, OnGetFriendsInteractorFinishedListener {
    private GetFriendsInteractor interactor;
    private ImainView view;
    private List<ResponseFriends> friendsList;
    private Button previousButton;


    public List<ResponseFriends> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<ResponseFriends> friendsList) {
        this.friendsList = friendsList;
    }


    public MainActivityPresenter(ImainView view) {
        interactor = new GetFriendsInteractor(this);
        this.view = view;
    }

    @Override
    public void loadFriends() {
        interactor.fetchFriends();
    }

    @Override
    public void onBindView(I_FriendsListView holder, int Position) {
        holder.setItemText(friendsList.get(Position).getFirst_name());

    }

    @Override
    public void onItemClicked(Button btn, int Position) {
        view.onItemClicked(btn, friendsList.get(Position));
        if (previousButton != null) {
            previousButton.setSelected(false);
        }
        previousButton = btn;
        btn.setSelected(true);
    }

    @Override
    public int listSize() {
        return friendsList.size();
    }


    @Override
    public void onNetworkSucces(List<ResponseFriends> list) {
        view.onFriendsLoadSucces(list);
    }

    @Override
    public void onNetworkFailed(String error) {
        view.onFriendsLoadFailure(error);
    }
}
