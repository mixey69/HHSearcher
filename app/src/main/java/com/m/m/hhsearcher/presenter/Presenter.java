package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.model.Searcher;
import com.m.m.hhsearcher.model.SearcherInterface;
import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Item;
import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 30.08.17.
 */

public class Presenter implements PresenterViewInterface, PresenterModelInterface {
    private static volatile Presenter instance;
    private FragmentManagerInterface mFragmentManager;
    private SearchResultViewInterface mSearchResultView;
    private SearcherInterface mSearcher;
    private String mSearchWord;
    private Integer mSearchTime;
    private List<Item> mVacancyList;
    private Vacancy mVacancy;


    @Override
    public void onFragmentManagerCreated(FragmentManagerInterface fragmentManager) {
        if(mFragmentManager != null){
            mFragmentManager = null;
            mFragmentManager = fragmentManager;
        }else {
            mFragmentManager = fragmentManager;
            mFragmentManager.displaySearchFragment();
        }
    }

    @Override
    public void startSearch(String searchWord) {
        mSearchTime = 1;
        mSearchWord = searchWord;
        mFragmentManager.displaySearchResultFragment();
        mVacancyList = null;
        if (mSearcher == null) {
            mSearcher = Searcher.getInstance(this);
        }
        mSearcher.search(searchWord, true);
    }

    @Override
    public void onAskedForMoreData() {
        if(!mSearcher.getIsBusy()){
            mSearchTime++;
            mSearcher.search(mSearchWord,false);
        }
    }

    @Override
    public void onVacancyListFound(List<Item> vacancyList) {
        if (mVacancyList == null){
            mVacancyList = vacancyList;
        }else {
            mVacancyList.addAll(vacancyList);
        }
        mSearchResultView.showVacancyList(vacancyList);
    }

    @Override
    public void updateSearchResultData() {
        mSearcher.searchForNew(mSearchWord, 10);
    }

    @Override
    public void updateSearchResultView(List<Item> newVacancyList) {
        if (newVacancyList.isEmpty()){
            mSearchResultView.updateVacancyList(new ArrayList<>());
        }else {
            if (!mVacancyList.get(0).equals(newVacancyList.get(0))) {
                for (int i = newVacancyList.size() - 1; i >= 0; i--) {
                    if (!mVacancyList.contains(newVacancyList.get(i))) {
                        mVacancyList.add(0, newVacancyList.get(i));
                    }
                }
                mSearchResultView.updateVacancyList(newVacancyList);
            } else {
                mSearchResultView.updateVacancyList(new ArrayList<>());
            }
        }
    }

    @Override
    public void getFullVacancyDescription(String vacancyId) {
        mSearcher.findVacancy(vacancyId);
    }

    @Override
    public void displayVacancyData(Vacancy vacancy) {
        mVacancy = vacancy;
        mFragmentManager.displayVacancyFragment(vacancy);
    }

    @Override
    public void ShowErrorMessage(String message) {
        if (message.equals("Invalid search word")){
            mFragmentManager.displaySearchFragment();
        }
        mFragmentManager.makeAToast(message);
    }

    public void setSearchResultView(SearchResultViewInterface searchResultView) {
        this.mSearchResultView = searchResultView;
    }

    @Override
    public void clearViewLink() { mSearchResultView = null; }

    public List<Item> getVacancyList() {
        return mVacancyList;
    }

    @Override
    public Vacancy getVacancy() {
        return mVacancy;
    }

    @Override
    public Integer getSearchTime() {
        return mSearchTime;
    }

    public static Presenter getInstance() {
        Presenter localInstance = instance;
        if (localInstance == null) {
            instance = localInstance = new Presenter();
        }
        return localInstance;
    }

    private Presenter() {
    }
}
