package com.example.ravitej.a20180210_rk_nycschools.splashscreen;

import com.example.ravitej.a20180210_rk_nycschools.common.model.School;
import com.example.ravitej.a20180210_rk_nycschools.common.network.endpoint.EndPointInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchoolDataFetcher {

    private EndPointInterface mEndPointInterface;

    @Inject
    public SchoolDataFetcher(EndPointInterface endPointInterface){
        mEndPointInterface = endPointInterface;
    }

    void fetchData(Observer<List<School>> observer){
        mEndPointInterface.getSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
}
