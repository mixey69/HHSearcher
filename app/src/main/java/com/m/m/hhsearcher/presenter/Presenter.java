package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.model.Searcher;
import com.m.m.hhsearcher.model.SearcherInterface;
import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Item;
import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;
import com.m.m.hhsearcher.view.VacancyViewInterface;

import java.util.List;

/**
 * Created by mac on 30.08.17.
 */

public class Presenter implements PresenterViewInterface, PresenterModelInterface {
    private static volatile Presenter instance;
    private FragmentManagerInterface mFragmentManager;
    private SearchResultViewInterface mSearchResultView;
    private SearcherInterface mSearcher;
    private VacancyViewInterface mVacancyView;
    private String mSearchWord;
    private Integer mSearchTime;


    public static Presenter getInstance() {
        Presenter localInstance = instance;
        if (localInstance == null) {
            instance = localInstance = new Presenter();
        }
        return localInstance;
    }

    private Presenter() {
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
        if (mSearcher == null) {
            mSearcher = Searcher.getInstance(this);
        }
        mSearcher.search(searchWord, true);
    }

    @Override
    public void loadMore() {
        if(!mSearcher.getIsBusy()){
            mSearchTime++;
            mSearcher.search(mSearchWord,false);
        }
    }

    @Override
    public void refreshSearchResultData() {
        mSearcher.searchForNew(mSearchWord);
    }

    @Override
    public void refreshSearchResultView(List<Item> newVacancyList) {
        mSearchResultView.updateVacancyList(newVacancyList);
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
