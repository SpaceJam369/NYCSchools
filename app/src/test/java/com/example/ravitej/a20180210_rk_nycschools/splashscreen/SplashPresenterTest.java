package com.example.ravitej.a20180210_rk_nycschools.splashscreen;

import android.content.Context;
import android.util.Log;

import com.example.ravitej.a20180210_rk_nycschools.common.DatabaseIntentService;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@PrepareForTest({DatabaseIntentService.class, Context.class, Log.class})
@RunWith(PowerMockRunner.class)
public class SplashPresenterTest {

    @Mock Context mContext;
    @Mock SplashContract.View mView;
    @Mock SchoolDataFetcher mDataFetcher;

    private SplashPresenter mPresenter;

    @Before
    public void setup(){
        initMocks(this);
        mockStatic(DatabaseIntentService.class);
        mockStatic(Log.class);
        mPresenter = new SplashPresenter(mView, mContext, mDataFetcher);
    }

    @Test
    public void onDataFetchCompleted_navigatesToSchoolsListActivity(){
        emitData();
        mPresenter.initialize();
        verify(mView).navigateToSchoolsListActivity();
    }

    @Test
    public void onDataFectchCompleted_verifyInsertSchoolsMethosIsCalled(){
        emitData();
        mPresenter.initialize();
        verifyStatic();
        DatabaseIntentService.insertSchools(any(Context.class), anyList());
    }

    @SuppressWarnings("unchecked")
    private void emitData(){
        final List<School> schools = new ArrayList<>();
        schools.add(new School());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return Observable.just(schools)
                        .subscribeWith((Observer)invocation.getArguments()[0]);
            }
        }).when(mDataFetcher).fetchData(any(Observer.class));
    }

}