package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.model.vacancy_item.Item;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac on 29.08.17.
 */

public class SearchResultFragment extends ViewFragment implements SearchResultViewInterface {
    @BindView(R.id.vacancy_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    VacancyListAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    FragmentManagerInterface mFragmentManager;
    List<Item> mVacancyList;
    Disposable mSubscription;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mAdapter = new VacancyListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentManager = (MainActivity) getActivity();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = mLayoutManager.getChildCount();//смотрим сколько элементов на экране
                int totalItemCount = mLayoutManager.getItemCount();//сколько всего элементов
                int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();//позиция первого элемента
                if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                    mPresenter.onAskedForMoreData();
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem == 0) {
                    if (mSubscription == null) {
                        mSubscription = Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe((Long l) -> {
                                    if (mLayoutManager.findFirstVisibleItemPosition() != 0) {
                                        mSubscription.dispose();
                                        mSubscription = null;
                                    } else {
                                        mPresenter.updateSearchResultData();
                                        mProgressBar.setVisibility(View.VISIBLE);
                                    }
                                });
                    }
                }
            }
        });
        mVacancyList = mPresenter.getVacancyList();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mVacancyList = mPresenter.getVacancyList();
        mPresenter.setSearchResultView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSubscription != null) {
            mSubscription.dispose();
            mSubscription = null;
        }
        mVacancyList = null;
        mPresenter.clearViewLink();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.searchresultfragment_layout;
    }

    @Override
    public void showVacancyList(List<Item> vacancyList) {
        mVacancyList = mPresenter.getVacancyList();
        mAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateVacancyList(List<Item> newVacancyList) {
        mVacancyList = mPresenter.getVacancyList();
        mAdapter.notifyItemRangeInserted(0, newVacancyList.size());
        mProgressBar.setVisibility(View.GONE);
    }

    class VacancyListAdapter extends RecyclerView.Adapter<VacancyListAdapter.VacancyViewHolder> {
        @Override
        public VacancyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacancy_item_layout, parent, false);
            return new VacancyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(VacancyViewHolder holder, int position) {
            final Item displayedItem = mVacancyList.get(position);
            holder.mCompanyName.setText("at " + displayedItem.employer.name);
            holder.mVacancyName.setText(displayedItem.name);
            String jobDescription = displayedItem.snippet.toString();
            if (jobDescription.length() > 150) {
                jobDescription = jobDescription.substring(0, 150) + "...";
            }
            holder.mJobDescription.setText(jobDescription);
            holder.itemView.setOnClickListener((View v) -> {
                mPresenter.getFullVacancyDescription(displayedItem.id);
            });
        }

        @Override
        public int getItemCount() {
            return mVacancyList == null ? 0 : mVacancyList.size();
        }

        class VacancyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.item_company_name)
            TextView mCompanyName;
            @BindView(R.id.item_vacancy_name)
            TextView mVacancyName;
            @BindView(R.id.item_job_description)
            TextView mJobDescription;
            SearchResultViewInterface mParentInterface;

            private VacancyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                mParentInterface = (SearchResultFragment) getParentFragment();
            }


        }
    }
}
