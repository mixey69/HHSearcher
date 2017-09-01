package com.m.m.hhsearcher.view;

import com.m.m.hhsearcher.vacancy_item.Item;

import java.util.List;

/**
 * Created by mac on 29.08.17.
 */

public interface SearchResultViewInterface {
    void showVacancyList(List<Item> vacancyList);
    void refreshVacancyList(List<Item> newVacancyList);
}
