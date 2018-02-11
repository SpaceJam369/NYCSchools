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
import com.example.ravitej.a20180210_rk_nycschools.common.utils.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.SchoolsAdapterViewHolder>{

    private List<School> mSchoolList;
    private View mEmptyView;
    private SchoolDetailsOnClickHandler mHandler;

    //Interface to open the dialog on click of an item...
    public interface SchoolDetailsOnClickHandler{
        void onClick(School school);
    }

    public SchoolsAdapter(Context context, View view, SchoolDetailsOnClickHandler handler){
        mEmptyView = view;
        mHandler = handler;
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

        public SchoolsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mHandler.onClick(mSchoolList.get(getAdapterPosition()));
        }
    }

}
