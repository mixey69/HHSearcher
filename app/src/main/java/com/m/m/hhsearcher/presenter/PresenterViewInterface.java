package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;
import com.m.m.hhsearcher.view.VacancyViewInterface;

/**
 * Created by mac on 30.08.17.
 */

public interface PresenterViewInterface {
    void startSearch(String searchWord);
    void setFragmentManager(FragmentManagerInterface mFragmentManager);
    void setSearchResultView(SearchResultViewInterface searchResultViewInterface);
    void loadMore();
    void getFullVacancyDescription(String vacancyId);
    void setVacancyView(VacancyViewInterface viewInterface);
    void refreshSearchResultData();
}
