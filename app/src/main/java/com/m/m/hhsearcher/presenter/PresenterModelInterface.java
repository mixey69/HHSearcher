package com.m.m.hhsearcher.presenter;

import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Item;

import java.util.List;

/**
 * Created by mac on 01.09.17.
 */

public interface PresenterModelInterface {
    Integer getSearchTime();
    void updateView(List<Item> vacancyList);
    void displayVacancyData(Vacancy vacancy);
    void refreshSearchResultView(List<Item> newVacancyList);
    void ShowErrorMessage(String message);
}
