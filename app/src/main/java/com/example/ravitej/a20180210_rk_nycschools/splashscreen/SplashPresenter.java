package com.example.ravitej.a20180210_rk_nycschools.splashscreen;

import android.content.Context;
import android.util.Log;

import com.example.ravitej.a20180210_rk_nycschools.common.DatabaseIntentService;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashPresenter implements SplashContract.Presenter {


    private SplashContract.View mView;
    private SchoolDataFetcher mDataFetcher;
    private Context mContext;

    private static final String LOG_TAG = SplashPresenter.class.getSimpleName();

    @Inject
    public SplashPresenter(SplashContract.View view, Context context, SchoolDataFetcher dataFetcher){
        mDataFetcher = dataFetcher;
        mView = view;
        mContext = context;
    }

    @Override
    public void initialize() {
        mDataFetcher.fetchData(new SchoolObserver());
    }

    private void onSchoolsDataReceived(List<School> schools) {
        if (schools != null && !schools.isEmpty()){
            DatabaseIntentService.deleteSchools(mContext);
            DatabaseIntentService.insertSchools(mContext, schools);
        }
        mView.navigateToSchoolsListActivity();
    }


    private class SchoolObserver implements Observer<List<School>> {
        List<School> schools;

        @Override
        public void onSubscribe(Disposable d) {
            Log.e(LOG_TAG, "on subscribed");
        }

        @Override
        public void onNext(List<School> schools) {
            this.schools = schools;
        }

        @Override
        public void onError(Throwable e) {
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }

        @Override
        public void onComplete() {
            Log.e(LOG_TAG, "onComplete");
            onSchoolsDataReceived(schools);
        }
    }

}
