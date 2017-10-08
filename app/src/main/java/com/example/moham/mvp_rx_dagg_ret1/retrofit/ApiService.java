package com.example.moham.mvp_rx_dagg_ret1.retrofit;

import com.example.moham.mvp_rx_dagg_ret1.model.Response;


import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by moham on 9/28/2017.
 */

public interface ApiService {
    @GET("/friends.txt")
    Observable<Response> listRepos();
}
