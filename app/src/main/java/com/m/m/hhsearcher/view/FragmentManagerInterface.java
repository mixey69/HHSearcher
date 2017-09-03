package com.m.m.hhsearcher.view;

import com.m.m.hhsearcher.model.vacancy.Vacancy;

/**
 * Created by mac on 30.08.17.
 */

public interface FragmentManagerInterface {
        void displaySearchFragment();
        void displaySearchResultFragment();
        void displayVacancyFragment(Vacancy vacancy);
        void makeAToast(String text);
}
