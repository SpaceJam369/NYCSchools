package com.example.ravitej.a20180210_rk_nycschools.application;

import android.app.Application;

import com.example.ravitej.a20180210_rk_nycschools.common.network.DaggerNetComponent;
import com.example.ravitej.a20180210_rk_nycschools.common.network.NetComponent;
import com.example.ravitej.a20180210_rk_nycschools.common.network.NetModule;
import com.facebook.stetho.Stetho;

public class NYCApplication extends Application {

    private static AppComponent mAppComponent;
    private static NetComponent mNetComponent;

    private static final String BASE_URL = "https://data.cityofnewyork.us/";

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        mNetComponent = DaggerNetComponent.builder()
                .appComponent(mAppComponent)
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent(){
        return mNetComponent;
    }
}
