package com.example.moham.mvp_rx_dagg_ret1;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by moham on 10/1/2017.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
