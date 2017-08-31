package com.m.m.hhsearcher.model;

import java.util.LinkedList;

/**
 * Created by mac on 30.08.17.
 */

public interface SearcherInterface {
    LinkedList search(String searchWord);
    boolean getIsBusy();
}
