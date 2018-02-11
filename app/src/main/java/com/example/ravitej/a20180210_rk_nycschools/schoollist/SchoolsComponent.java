package com.example.ravitej.a20180210_rk_nycschools.schoollist;

import com.example.ravitej.a20180210_rk_nycschools.common.network.NetComponent;
import com.example.ravitej.a20180210_rk_nycschools.common.utils.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = NetComponent.class, modules = SchoolsModule.class)
public interface SchoolsComponent {
    void inject(SchoolsListActivity activity);
}
