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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac on 29.08.17.
 */

public class SearchResultFragment extends ViewFragment implements SearchResultViewInterface{
    @BindView(R.id.vacancy_list) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    VacancyListAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    FragmentManagerInterface mFragmentManager;
    volatile ArrayList<Item> mVacancyList;
    Disposable mSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        mPresenter.setSearchResultView(this);
        mAdapter = new VacancyListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentManager = (MainActivity)getActivity();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = mLayoutManager.getChildCount();//смотрим сколько элементов на экране
                int totalItemCount = mLayoutManager.getItemCount();//сколько всего элементов
                int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();//какая позиция первого элемента
                    if ((visibleItemCount+firstVisibleItem) >= totalItemCount) {
                            mPresenter.loadMore();
                            mProgressBar.setVisibility(View.VISIBLE);
                    }
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if(firstVisibleItem == 0) {
                    if (mSubscription == null) {
                        mSubscription = Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(@io.reactivex.annotations.NonNull Long aLong) throws Exception {
                                        if (mLayoutManager.findFirstVisibleItemPosition() != 0) {
                                            mSubscription.dispose();
                                            mSubscription = null;
                                        } else {
                                            mPresenter.refreshSearchResultData();
                                            mProgressBar.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                    }
                }
            }
        });

        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.searchresultfragment_layout;
    }

    @Override
    public void showVacancyList(List<Item> vacancyList) {
        if(mVacancyList == null){
            mVacancyList = new ArrayList<>(vacancyList);
        }else {
            mVacancyList.addAll(vacancyList);
        }
        mAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateVacancyList(List<Item> newVacancyList) {
        for (int i = newVacancyList.size() -1 ; i >= 0; i--){
            mVacancyList.add(0,newVacancyList.get(i));
        }
       mAdapter.notifyItemRangeInserted(0, newVacancyList.size());
        mProgressBar.setVisibility(View.GONE);
    }

    class VacancyListAdapter extends RecyclerView.Adapter<VacancyListAdapter.VacancyViewHolder>{
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
            if (jobDescription.length() > 150){
                jobDescription = jobDescription.substring(0,150) + "...";
            }
            holder.mJobDescription.setText(jobDescription);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.displayVacancyFragment();
                    mPresenter.getFullVacancyDescription(displayedItem.id);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mVacancyList == null){
                return 0;
            }else {
                return mVacancyList.size();
            }
        }

        class VacancyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            @BindView(R.id.item_company_name) TextView mCompanyName;
            @BindView(R.id.item_vacancy_name) TextView mVacancyName;
            @BindView(R.id.item_job_description) TextView mJobDescription;
            SearchResultViewInterface mParentInterface;

            private VacancyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
                mParentInterface = (SearchResultFragment)getParentFragment();
            }

            //TODO: перенести онклик сюда - написать метод, который вызовем в адаптере, чтобы передать сюда показываемый item
            @Override
            public void onClick(View view) {}
        }
    }
}
