package com.m.m.hhsearcher.model;

import com.m.m.hhsearcher.vacancy.Vacancy;

import java.util.LinkedList;

/**
 * Created by mac on 30.08.17.
 */

public interface SearcherInterface {
    LinkedList search(String searchWord);
    boolean getIsBusy();
    Vacancy findVacancy(String vacancyId);
}
