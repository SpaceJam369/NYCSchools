package com.example.ravitej.a20180210_rk_nycschools.schoollist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ravitej.a20180210_rk_nycschools.R;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.SchoolsAdapterViewHolder>{

    private List<School> mSchoolList;
    private Context mContext;
    private View mEmptyView;

    public SchoolsAdapter(Context context, View view){
        mContext = context;
        mEmptyView = view;
    }

    @Override
    public SchoolsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent instanceof RecyclerView){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school, parent, false);
            view.setFocusable(true);
            return new SchoolsAdapterViewHolder(view);
        }else{
            throw new RuntimeException("Not bound to recycler view");
        }
    }

    @Override
    public void onBindViewHolder(SchoolsAdapterViewHolder holder, int position) {

        School school = mSchoolList.get(position);

        //set school name
        holder.schoolName.setText(school.getSchoolName());

        //set sat details
        holder.mathScore.setText(mContext.getString(R.string.math_score_text, school.getMathScore()));
        holder.readingScore.setText(mContext.getString(R.string.reading_score_text, school.getReadingScore()));
        holder.writingScore.setText(mContext.getString(R.string.writing_score_text, school.getWritingScore()));
    }

    @Override
    public int getItemCount() {
        return mSchoolList != null ? mSchoolList.size() : 0;
    }

    public void swapCursor(List<School> schools){
        mSchoolList = schools;
        mEmptyView.setVisibility(getItemCount() == 0? View.VISIBLE : View.GONE);
        notifyDataSetChanged();
    }

    public class SchoolsAdapterViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.schoolName) TextView schoolName;
        @BindView(R.id.satScores_details_layout) LinearLayout detailsLayout;
        @BindView(R.id.mathSatScore) TextView mathScore;
        @BindView(R.id.satReadingScore) TextView readingScore;
        @BindView(R.id.satWritingScore) TextView writingScore;

        public SchoolsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            detailsLayout.setVisibility(View.VISIBLE);
        }
    }
}
