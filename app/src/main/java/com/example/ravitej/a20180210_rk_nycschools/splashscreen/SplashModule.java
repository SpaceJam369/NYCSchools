package com.example.ravitej.a20180210_rk_nycschools.splashscreen;

import com.example.ravitej.a20180210_rk_nycschools.common.utils.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    private SplashContract.View mView;

    public SplashModule(SplashContract.View view){
        mView = view;
    }

    @Provides
    @ActivityScope
    SplashContract.View provideView(){
        return mView;
    }

}
