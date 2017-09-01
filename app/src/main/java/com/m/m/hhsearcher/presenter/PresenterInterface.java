package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.vacancy.Vacancy;
import com.m.m.hhsearcher.vacancy_item.Item;
import com.m.m.hhsearcher.view.FragmentManagerInterface;
import com.m.m.hhsearcher.view.SearchResultViewInterface;
import com.m.m.hhsearcher.view.VacancyViewInterface;

import java.util.List;

/**
 * Created by mac on 30.08.17.
 */

public interface PresenterInterface {
    void updateView(List<Item> vacancyList);
    void startSearch(String searchWord);
    void setFragmentManager(FragmentManagerInterface mFragmentManager);
    void setSearchResultView(SearchResultViewInterface searchResultViewInterface);
    Integer getSearchTime();
    void loadMore();
    boolean checkIfBusy();
    void getFullVacancyDescription(String vacancyId);
    void setVacancyView(VacancyViewInterface viewInterface);
    void displayVacancyData(Vacancy vacancy);
    void refreshSearchResultData();
    void refreshSearchResultView(List<Item> newVacancyList);
}
