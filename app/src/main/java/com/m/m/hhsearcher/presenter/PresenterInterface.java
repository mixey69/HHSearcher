package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;

import java.util.List;

/**
 * Created by mac on 30.08.17.
 */

public interface PresenterInterface {
    void updateView(List<?> vacancyList);
    void startSearch(String searchWord);
    void setFragmentManager(FragmentManagerInterface mFragmentManager);
    void setSearchResultView(SearchResultViewInterface searchResultViewInterface);
}
