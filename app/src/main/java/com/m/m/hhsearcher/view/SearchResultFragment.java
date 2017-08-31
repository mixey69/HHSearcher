package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.presenter.PresenterInterface;
import com.m.m.hhsearcher.vacancy.Item;

import java.util.List;

/**
 * Created by mac on 29.08.17.
 */

public class SearchResultFragment extends Fragment implements SearchResultViewInterface{
    //@BindView(R.id.vacancy_list) RecyclerView mRecyclerView;
    RecyclerView mRecyclerView;
    VacancyListAdapter mAdapter;
    PresenterInterface mPresenter;
    List<Item> mVacancyList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchresultfragment_layout,container,false);
       // ButterKnife.bind(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.vacancy_list);
        mPresenter = Presenter.getInstance();
        mPresenter.setSearchResultView(this);
        mAdapter = new VacancyListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void showVacancyList(List<Item> vacancyList) {
        mVacancyList = vacancyList;
        mAdapter.notifyDataSetChanged();
    }

    public class VacancyListAdapter extends RecyclerView.Adapter<VacancyListAdapter.VacancyViewHolder>{
        @Override
        public VacancyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacancy_item_layout, parent, false);
            return new VacancyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(VacancyViewHolder holder, int position) {
            Item displayedItem = mVacancyList.get(position);
            holder.mCompanyName.setText(displayedItem.employer.name);
            holder.mVacancyName.setText(displayedItem.name);
            String jobDescription = displayedItem.snippet.toString();
            if (jobDescription.length() > 150){
                jobDescription = jobDescription.substring(0,150) + "...";
            }
            holder.mJobDescription.setText(jobDescription);
        }

        @Override
        public int getItemCount() {
            if (mVacancyList == null){
                return 0;
            }else {
                return mVacancyList.size();
            }
        }

        class VacancyViewHolder extends RecyclerView.ViewHolder{
            TextView mCompanyName;
            TextView mVacancyName;
            TextView mJobDescription;

            private VacancyViewHolder(View itemView) {
                super(itemView);
                mCompanyName = (TextView) itemView.findViewById(R.id.item_company_name);
                mVacancyName = (TextView) itemView.findViewById(R.id.item_vacancy_name);
                mJobDescription = (TextView) itemView.findViewById(R.id.item_job_description);
            }
        }
    }
}
