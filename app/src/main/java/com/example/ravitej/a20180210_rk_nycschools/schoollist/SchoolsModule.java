package com.example.ravitej.a20180210_rk_nycschools.schoollist;

import android.support.v4.app.LoaderManager;

import com.example.ravitej.a20180210_rk_nycschools.common.utils.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SchoolsModule {

    private SchoolsContract.View mView;
    private LoaderManager mLoaderManager;

    public SchoolsModule(SchoolsContract.View view, LoaderManager loaderManager){
        mView = view;
        mLoaderManager = loaderManager;
    }

    @Provides
    @ActivityScope
    SchoolsContract.View provideView(){
        return mView;
    }

    @Provides
    @ActivityScope
    LoaderManager provideLoaderManager(){
        return mLoaderManager;
    }
}
