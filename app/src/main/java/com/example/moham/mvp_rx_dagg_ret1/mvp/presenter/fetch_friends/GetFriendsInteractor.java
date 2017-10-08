package com.example.moham.mvp_rx_dagg_ret1.mvp.presenter.fetch_friends;


import android.util.Log;

import com.example.moham.mvp_rx_dagg_ret1.retrofit.ApiService;
import com.example.moham.mvp_rx_dagg_ret1.retrofit.RetrofitUtil;
import com.example.moham.mvp_rx_dagg_ret1.model.Response;
import com.example.moham.mvp_rx_dagg_ret1.model.ResponseFriends;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by moham on 9/28/2017.
 */

class GetFriendsInteractor {
    private OnGetFriendsInteractorFinishedListener listener;
    private List<ResponseFriends> friendsList;

    public GetFriendsInteractor(OnGetFriendsInteractorFinishedListener listener) {
        this.listener = listener;

    }

    void fetchFriends() {
        ApiService apiService = RetrofitUtil.getAPIService();
        apiService.listRepos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onNetworkFailed(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Response model) {
                        listener.onNetworkSucces(model.getFriends());
                    }
                });
    }


}
