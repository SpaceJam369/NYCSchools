package com.example.ravitej.a20180210_rk_nycschools.application;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Component;

@AppScoped
@Component(modules = AppModule.class)
public interface AppComponent  {

    Context provideContext();
    Application provideApplication();
}
