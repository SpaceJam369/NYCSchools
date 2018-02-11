package com.example.ravitej.a20180210_rk_nycschools.application;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(NYCApplication application) {
        mApplication = application;
    }

    @AppScoped
    @Provides
    public Context provideContext(){
        return mApplication.getApplicationContext();
    }

    @AppScoped
    @Provides
    public Application provideApplication(){
        return mApplication;
    }
}
