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
    private List<Item> mVacancyList;
    private Vacancy mVacancy;


    public static Presenter getInstance() {
        Presenter localInstance = instance;
        if (localInstance == null) {
            instance = localInstance = new Presenter();
        }
        return localInstance;
    }

    private Presenter() {
    }

//    public void setFragmentManager(FragmentManagerInterface mFragmentManager, ViewFragment fragment) {
//        this.mFragmentManager = mFragmentManager;
//        if(fragment == null){
//            mFragmentManager.displaySearchFragment();
//        }else{
//            switch (fragment.getTag()){
//                case "SEARCH_FRAGMENT":
//                    mFragmentManager.displaySearchFragment();
//                case "SEARCH_RESULT_FRAGMENT":
//                    mFragmentManager.displaySearchResultFragment();
//                case "VACANCY_FRAGMENT":
//                    mFragmentManager.displayVacancyFragment();
//            }
//        }
//    }

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
    public void clearView(String TAG) {
        switch (TAG){
            case "SEARCH_RESULT_FRAGMENT":
                mSearchResultView = null;
            case "VACANCY_FRAGMENT":
                mVacancyView = null;
        }

    }

    public List<Item> getVacancyList() {
        return mVacancyList;
    }


    @Override
    public void updateView(List<Item> vacancyList) {
        if (mVacancyList == null){
            mVacancyList = vacancyList;
        }else {
            mVacancyList.addAll(vacancyList);
        }
        mSearchResultView.showVacancyList(vacancyList);
    }

    @Override
    public void startSearch(String searchWord) {
        mSearchTime = 1;
        mSearchWord = searchWord;
        mVacancyList = null;
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
    public void updateSearchResultData() {
        mSearcher.searchForNew(mSearchWord);
    }

    @Override
    public void refreshSearchResultView(List<Item> newVacancyList) {
        for (int i = newVacancyList.size() -1 ; i >= 0; i--){
            mVacancyList.add(0,newVacancyList.get(i));
        }
        mSearchResultView.updateVacancyList(newVacancyList);
    }

    @Override
    public void getFullVacancyDescription(String vacancyId) {
        mFragmentManager.displayVacancyFragment();
        mSearcher.findVacancy(vacancyId);
    }

    @Override
    public void displayVacancyData(Vacancy vacancy) {
        mVacancy = vacancy;
        mVacancyView.showVacancy(vacancy);
    }

    @Override
    public Vacancy getVacancy() {
        return mVacancy;
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
