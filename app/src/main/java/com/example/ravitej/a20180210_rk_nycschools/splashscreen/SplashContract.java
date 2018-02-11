package com.example.ravitej.a20180210_rk_nycschools.splashscreen;

public interface SplashContract {

    interface View{

        void navigateToSchoolsListActivity();
    }

    interface Presenter{

        void initialize();
    }
}
