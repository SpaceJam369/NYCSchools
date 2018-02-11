package com.example.ravitej.a20180210_rk_nycschools.schoollist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@PrepareForTest({Context.class})
public class SchoolsPresenterTest {

    @Mock Context mContext;
    @Mock SchoolsContract.View mView;
    @Mock LoaderManager mLoaderManager;

    private SchoolsPresenter mPresenter;

    @Before
    public void setup(){
        initMocks(this);
        mPresenter = new SchoolsPresenter(mContext, mView, mLoaderManager);
    }

    @Test
    public void onInitialize_startsLoader(){
        mPresenter.initialize();
        verify(mLoaderManager, times(1))
                .initLoader(any(Integer.class), any(Bundle.class), any(LoaderManager.LoaderCallbacks.class));
    }

}