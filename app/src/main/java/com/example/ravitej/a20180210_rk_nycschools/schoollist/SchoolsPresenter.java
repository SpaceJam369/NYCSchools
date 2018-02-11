package com.example.ravitej.a20180210_rk_nycschools.schoollist;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.example.ravitej.a20180210_rk_nycschools.common.CursorConvertor;
import com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SchoolsPresenter implements SchoolsContract.Presenter,  LoaderManager.LoaderCallbacks<Cursor> {

    private SchoolsContract.View mView;
    private Context mContext;
    private LoaderManager mLoader;

    private static final int SCHOOL_LOADER = 200;
    private static final String LOG_TAG = SchoolsPresenter.class.getSimpleName();

    @Inject
    public SchoolsPresenter(Context context, SchoolsContract.View view, LoaderManager loaderManager){
        mView = view;
        mContext = context;
        mLoader = loaderManager;
    }

    @Override
    public void initialize() {
        mLoader.initLoader(SCHOOL_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case SCHOOL_LOADER:
                return new CursorLoader(mContext,
                        NYCSchoolsContract.SchoolEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                Log.e(LOG_TAG, "UnKnown loader requested with id: "+ id);
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(loader == null) return;

        switch (loader.getId()){
            case SCHOOL_LOADER:
                Single.just(CursorConvertor.convertSchoolsFromCursor(data))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribeWith(new SingleObserver<List<School>>() {
                            @Override
                            public void onSubscribe(Disposable d) {}

                            @Override
                            public void onSuccess(List<School> schools) {
                                mView.notifyDataChanged(schools);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(LOG_TAG, "Failed to convert schools list cursor", e);
                            }
                        });
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mView.notifyDataChanged(null);
    }

}
