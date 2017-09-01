package com.m.m.hhsearcher.model;

/**
 * Created by mac on 30.08.17.
 */

public interface SearcherInterface {
    void search(String searchWord);
    boolean getIsBusy();
    void findVacancy(String vacancyId);
    void searchForNew(String searchWord);
}
