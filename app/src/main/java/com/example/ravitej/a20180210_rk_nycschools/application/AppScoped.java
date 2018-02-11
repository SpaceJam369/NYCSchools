package com.example.ravitej.a20180210_rk_nycschools.application;


//As dagger doesn't allow different components to have the same scope..
//We created this to retain the instance created using this scope through out the application context..
public @interface AppScoped {}
