package com.example.ravitej.a20180210_rk_nycschools.schoollist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.ravitej.a20180210_rk_nycschools.R;
import com.example.ravitej.a20180210_rk_nycschools.application.NYCApplication;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;
import com.example.ravitej.a20180210_rk_nycschools.schoollist.adapter.SchoolsAdapter;
import com.example.ravitej.a20180210_rk_nycschools.schoollist.dialog.SchoolDetailsDialogFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchoolsListActivity extends AppCompatActivity implements SchoolsContract.View,
        SchoolsAdapter.SchoolDetailsOnClickHandler{

    @BindView(R.id.schools_recycler_iew) RecyclerView mRecyclerView;
    @BindView(R.id.empty_recycler_view) TextView mEmptyTextView;

    @Inject SchoolsPresenter mPresenter;

    private SchoolsAdapter mSchoolsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools_list);
        ButterKnife.bind(this);

        DaggerSchoolsComponent.builder()
                .netComponent(NYCApplication.getNetComponent())
                .schoolsModule(new SchoolsModule(this, getSupportLoaderManager()))
                .build().inject(this);

        initializeRecyclerView();

        mPresenter.initialize();

    }

    private void initializeRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSchoolsAdapter = new SchoolsAdapter(this, mEmptyTextView, this);
        mRecyclerView.setAdapter(mSchoolsAdapter);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mPresenter;
    }

    @Override
    public void notifyDataChanged(List<School> schools) {
        mSchoolsAdapter.swapCursor(schools);
    }

    @Override
    public void onClick(School school) {
        SchoolDetailsDialogFragment dialogFragment = SchoolDetailsDialogFragment.newInstance(school);
        dialogFragment.show(getSupportFragmentManager(), "Schools_details");
    }
}
