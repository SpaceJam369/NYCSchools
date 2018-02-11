package com.example.ravitej.a20180210_rk_nycschools.splashscreen;

import com.example.ravitej.a20180210_rk_nycschools.common.network.NetComponent;
import com.example.ravitej.a20180210_rk_nycschools.common.utils.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = NetComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
