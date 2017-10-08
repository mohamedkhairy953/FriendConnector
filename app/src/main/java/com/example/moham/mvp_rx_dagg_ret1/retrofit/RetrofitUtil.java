package com.example.moham.mvp_rx_dagg_ret1.retrofit;

import com.example.moham.mvp_rx_dagg_ret1.retrofit.ApiService;
import com.example.moham.mvp_rx_dagg_ret1.retrofit.ProvideRetrofit;

/**
 * Created by moham on 9/28/2017.
 */

 public class RetrofitUtil {
    public static final String BASE_URL = "https://www.digi-worx.com/";
    public static ApiService getAPIService() {

        return ProvideRetrofit.getClient().create(ApiService.class);
    }
}
