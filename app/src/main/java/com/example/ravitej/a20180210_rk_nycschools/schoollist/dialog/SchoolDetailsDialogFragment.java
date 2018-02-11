package com.example.ravitej.a20180210_rk_nycschools.schoollist.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ravitej.a20180210_rk_nycschools.R;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;
import com.example.ravitej.a20180210_rk_nycschools.common.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SchoolDetailsDialogFragment extends DialogFragment {

    @BindView(R.id.mathSatScore) TextView mathScore;
    @BindView(R.id.satReadingScore) TextView readingScore;
    @BindView(R.id.satWritingScore) TextView writingScore;

    private Unbinder mUnbinder;

    private static final String SCHOOL_DETAILS = "school details";
    private School mSchool;

    public static SchoolDetailsDialogFragment newInstance(School school){
        SchoolDetailsDialogFragment dialogFragment = new SchoolDetailsDialogFragment();

        Bundle args = new Bundle();
        args.putParcelable(SCHOOL_DETAILS, school);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (args != null){
            mSchool = args.getParcelable(SCHOOL_DETAILS);
        }else{
            dismiss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.school_details_dialog, null);
        View titleView = inflater.inflate(R.layout.dialog_title, null);
        mUnbinder = ButterKnife.bind(this, view);

        if (mSchool != null) {
            if (Utility.convertStringToInt(mSchool.getMathScore()) != null) {
                mathScore.setVisibility(View.VISIBLE);
                mathScore.setText(getActivity().getString(R.string.math_score_text, mSchool.getMathScore()));
            }

            if (Utility.convertStringToInt(mSchool.getReadingScore()) != null) {
                readingScore.setVisibility(View.VISIBLE);
                readingScore.setText(getActivity().getString(R.string.reading_score_text, mSchool.getReadingScore()));
            }
            if (Utility.convertStringToInt(mSchool.getWritingScore()) != null) {
                writingScore.setVisibility(View.VISIBLE);
                writingScore.setText(getActivity().getString(R.string.writing_score_text, mSchool.getWritingScore()));
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setCustomTitle(titleView)
                .setView(view)
                .setPositiveButton("OK", null);

        return builder.create();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
