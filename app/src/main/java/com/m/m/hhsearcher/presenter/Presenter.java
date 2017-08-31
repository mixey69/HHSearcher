package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.model.Searcher;
import com.m.m.hhsearcher.model.SearcherInterface;
import com.m.m.hhsearcher.vacancy.Item;
import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;

import java.util.List;

/**
 * Created by mac on 30.08.17.
 */

public class Presenter implements PresenterInterface {
    private static volatile Presenter instance;
    private FragmentManagerInterface mFragmentManager;
    private SearchResultViewInterface mSearchResultView;
    private SearcherInterface mSearcher;
    private List<String> firstSearchResult;


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

    public void setFragmentManager(FragmentManagerInterface mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public void updateView(List<Item> vacancyList) {
        mSearchResultView.showVacancyList(vacancyList);
    }

    @Override
    public void startSearch(String searchWord) {
        mFragmentManager.displaySearchResultFragment();
        if (mSearcher == null){
            mSearcher = new Searcher(this);
        }
        mSearcher.firstSearch(searchWord);
    }

    public void setFirstSearchResult(List<String> firstSearchResult) {
        this.firstSearchResult = firstSearchResult;
    }


    public void setSearchResultView(SearchResultViewInterface searchResultView) {
        this.mSearchResultView = searchResultView;
    }


}
