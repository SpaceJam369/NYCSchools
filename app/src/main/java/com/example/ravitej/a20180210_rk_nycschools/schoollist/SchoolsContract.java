package com.example.ravitej.a20180210_rk_nycschools.schoollist;

import com.example.ravitej.a20180210_rk_nycschools.common.model.School;

import java.util.List;

public interface SchoolsContract {

    interface View{

        void notifyDataChanged(List<School> schools);
    }

    interface Presenter{

        void initialize();
    }
}
