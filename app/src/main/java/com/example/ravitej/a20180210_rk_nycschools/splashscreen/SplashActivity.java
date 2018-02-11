package com.example.ravitej.a20180210_rk_nycschools.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPresenter;
import android.util.Log;

import com.example.ravitej.a20180210_rk_nycschools.R;
import com.example.ravitej.a20180210_rk_nycschools.application.NYCApplication;
import com.example.ravitej.a20180210_rk_nycschools.schoollist.SchoolsListActivity;

import javax.inject.Inject;

//Created this activity to fetch the initial data ...
// as we don't have explicit data fetching.. 
public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    @Inject SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DaggerSplashComponent.builder()
                .netComponent(NYCApplication.getNetComponent())
                .splashModule(new SplashModule(this))
                .build().inject(this);

        mPresenter.initialize();
    }

    @Override
    public void navigateToSchoolsListActivity() {
        Intent intent = new Intent(this, SchoolsListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
