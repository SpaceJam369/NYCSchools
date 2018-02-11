package com.example.ravitej.a20180210_rk_nycschools.common.network.endpoint;

import com.example.ravitej.a20180210_rk_nycschools.common.model.School;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface EndPointInterface {

    @GET("resource/734v-jeq5.json")
    Observable<List<School>> getSchools();

}
