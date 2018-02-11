package com.example.ravitej.a20180210_rk_nycschools.common.network;

import android.app.Application;
import android.content.Context;

import com.example.ravitej.a20180210_rk_nycschools.application.AppComponent;
import com.example.ravitej.a20180210_rk_nycschools.common.network.endpoint.EndPointInterface;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

//NetComponent provides dependencies for the networking capabilities that requires them..
@Singleton
@Component(dependencies = AppComponent.class, modules = NetModule.class)
public interface NetComponent {

    OkHttpClient okHttpClient();
    Retrofit retrofit();
    HttpLoggingInterceptor httpLoggingInterceptor();
    EndPointInterface endpointInterface();
    Gson gson();

    Context context();
    Application application();
}
