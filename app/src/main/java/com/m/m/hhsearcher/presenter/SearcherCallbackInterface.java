package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Item;

import java.util.List;

/**
 * Created by mac on 01.09.17.
 */

public interface SearcherCallbackInterface {
    Integer getSearchTime();
    void onVacancyListFound(List<Item> vacancyList);
    void displayVacancyData(Vacancy vacancy);
    void updateSearchResultView(List<Item> newVacancyList);
    void ShowErrorMessage(String message);
}
