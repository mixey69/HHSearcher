package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Item;
import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;
import com.m.m.hhsearcher.view.VacancyViewInterface;

import java.util.List;

/**
 * Created by mac on 30.08.17.
 */

public interface PresenterViewInterface {
    void startSearch(String searchWord);
    void setSearchResultView(SearchResultViewInterface searchResultViewInterface);
    void loadMore();
    void getFullVacancyDescription(String vacancyId);
    void setVacancyView(VacancyViewInterface viewInterface);
    void updateSearchResultData();
    void onFragmentManagerCreated(FragmentManagerInterface fragmentManager);
    List<Item> getVacancyList();
    Vacancy getVacancy();
    void clearView(String TAG);
}
