package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.presenter.PresenterInterface;

import java.util.List;

/**
 * Created by mac on 29.08.17.
 */

public class SearchResultFragment extends Fragment implements SearchResultViewInterface{

    PresenterInterface mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchresultfragment_layout,container,false);
        mPresenter = Presenter.getInstance();
        mPresenter.setSearchResultView(this);
        return view;
    }

    @Override
    public void showVacancyList(List<?> vacancyList) {
        Toast.makeText(getContext(), "We're finally here", Toast.LENGTH_SHORT).show();
//        for (int i = 0; i < vacancyList.size(); i++){
//            Toast.makeText(getContext(), vacancyList.get(i).toString(), Toast.LENGTH_SHORT).show();
//        }
    }

    public class VacancyListAdapter extends RecyclerView.Adapter{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
