package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.model.Application;
import com.m.m.hhsearcher.model.SearcherInterface;
import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Item;
import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mac on 30.08.17.
 */

public class Presenter implements PresenterInterface, SearcherCallbackInterface {
    private static volatile Presenter instance;
    private FragmentManagerInterface mFragmentManager;
    private SearchResultViewInterface mSearchResultView;
    @Inject SearcherInterface mSearcher;
    private String mSearchWord;
    private Integer mSearchTime;
    private List<Item> mVacancyList;
    private Vacancy mVacancy;
    private boolean isActivityRestarting = false;


    @Override
    public void onFragmentManagerCreated(FragmentManagerInterface fragmentManager) {
        if (mSearcher == null){
            Application.getInstance().getInjectionComponent().inject(this);
        }
        if (isActivityRestarting) {
            mFragmentManager = fragmentManager;
        } else {
            mFragmentManager = fragmentManager;
            mFragmentManager.displaySearchFragment();
        }
        isActivityRestarting = false;
    }

    @Override
    public void startSearch(String searchWord) {
        mSearchTime = 1;
        mSearchWord = searchWord;
        mVacancyList = null;
        mSearcher.search(searchWord, true);
    }

    @Override
    public void onAskedForMoreData() {
        if (!mSearcher.getIsBusy()) {
            mSearchTime++;
            mSearcher.search(mSearchWord, false);
        }
    }

    @Override
    public void onVacancyListFound(List<Item> vacancyList) {
        if (mVacancyList == null) {
            mVacancyList = vacancyList;
        } else {
            mVacancyList.addAll(vacancyList);
        }
        if (mSearchResultView == null) {
            mFragmentManager.displaySearchResultFragment();
        } else {
            mSearchResultView.showVacancyList(vacancyList);
        }
    }

    @Override
    public void updateSearchResultData() {
        mSearcher.searchForNew(mSearchWord, 10);
    }

    @Override
    public void updateSearchResultView(List<Item> newVacancyList) {
        if (newVacancyList.isEmpty()) {
            if (mSearchResultView != null) {
                mSearchResultView.updateVacancyList(new ArrayList<>());
            }
        } else {
            if (!mVacancyList.get(0).equals(newVacancyList.get(0))) {
                for (int i = newVacancyList.size() - 1; i >= 0; i--) {
                    if (!mVacancyList.contains(newVacancyList.get(i))) {
                        mVacancyList.add(0, newVacancyList.get(i));
                    }
                }
                if (mSearchResultView != null) {
                    mSearchResultView.updateVacancyList(newVacancyList);
                }
            } else {
                if (mSearchResultView != null) {
                    mSearchResultView.updateVacancyList(new ArrayList<>());
                }
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
        mFragmentManager.makeAToast(message);
    }

    @Override
    public void setFragmentManager(FragmentManagerInterface fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void setSearchResultView(SearchResultViewInterface searchResultView) {
        this.mSearchResultView = searchResultView;
    }

    @Override
    public void clearViewLink(String TAG) {
        switch (TAG) {
            case "SEARCH_RESULT_FRAGMENT":
                mSearchResultView = null;
                break;
            case "ACTIVITY":
                mFragmentManager = null;
                break;
        }

    }

    @Override
    public void onActivityRestarting() {
        isActivityRestarting = true;
    }

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
        if (instance == null) {
            instance = new Presenter();
        }
        return instance;
    }

    private Presenter() {
    }
}
