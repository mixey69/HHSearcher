package com.m.m.hhsearcher.presenter;

import android.util.Log;

import com.m.m.hhsearcher.model.Searcher;
import com.m.m.hhsearcher.model.SearcherInterface;
import com.m.m.hhsearcher.vacancy.Vacancy;
import com.m.m.hhsearcher.vacancy_item.Item;
import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;
import com.m.m.hhsearcher.view.VacancyViewInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by mac on 30.08.17.
 */

public class Presenter implements PresenterInterface {
    private static volatile Presenter instance;
    private FragmentManagerInterface mFragmentManager;
    private SearchResultViewInterface mSearchResultView;
    private SearcherInterface mSearcher;
    private VacancyViewInterface mVacancyView;
    private String mSearchWord;
    private Integer mSearchTime;
    private String mLastRefreshmentTime;
    private DateFormat mDateFormat;


    public static Presenter getInstance() {
        Presenter localInstance = instance;
        if (localInstance == null) {
            synchronized (Presenter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Presenter();
                }
            }
        }
        return localInstance;
    }

    public Presenter() {

        TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
        mDateFormat = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss±hhmm");
        mDateFormat.setTimeZone(tz);
    }

    public void setFragmentManager(FragmentManagerInterface mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public void updateView(List<Item> vacancyList) {
        mSearchResultView.showVacancyList(vacancyList);
    }

    @Override
    public void startSearch(String searchWord) {
        mSearchTime = 1;
        mSearchWord = searchWord;
        mFragmentManager.displaySearchResultFragment();
        if (mSearcher == null){
            mSearcher = new Searcher(this);
        }
        mLastRefreshmentTime =mDateFormat.format(new Date());
        mSearcher.search(searchWord);
    }

    @Override
    public void loadMore() {
        mSearchTime++;
        mSearcher.search(mSearchWord);
    }

    @Override
    public void refreshSearchResultData() {
        mSearcher.searchForNew(mLastRefreshmentTime,mSearchWord);
        mLastRefreshmentTime = mDateFormat.format(new Date());
        Log.e("timeOfCall", mLastRefreshmentTime);
    }

    @Override
    public void refreshSearchResultView(List<Item> newVacancyList) {
        mSearchResultView.refreshVacancyList(newVacancyList);
    }

    @Override
    public boolean checkIfBusy() {
        return mSearcher.getIsBusy();
    }

    @Override
    public void getFullVacancyDescription(String vacancyId) {
        mSearcher.findVacancy(vacancyId);
    }

    @Override
    public void displayVacancyData(Vacancy vacancy) {
        mVacancyView.showVacancy(vacancy);
    }

    @Override
    public Integer getSearchTime() {
        return mSearchTime;
    }

    public void setSearchResultView(SearchResultViewInterface searchResultView) {
        this.mSearchResultView = searchResultView;
    }

    @Override
    public void setVacancyView(VacancyViewInterface viewInterface) {
        mVacancyView = viewInterface;
    }
}
