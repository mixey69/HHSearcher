package com.m.m.hhsearcher.model;

import android.util.Log;

import com.m.m.hhsearcher.presenter.PresenterInterface;
import com.m.m.hhsearcher.vacancy_item.Example;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac on 30.08.17.
 */

public class Searcher implements SearcherInterface{
    private PresenterInterface mPresenter;
    private Retrofit mRetrofit;
    private HHApi mHHApi;
    private boolean isBusy;

    private final static String SEARCH_URL = "https://api.hh.ru/";

    @Override
    public LinkedList search(String searchWord) {
        isBusy = true;
        mHHApi.getData(searchWord, mPresenter.getSearchTime(),"publication_time", 10).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                isBusy = false;
                mPresenter.updateView(response.body().items);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                isBusy = false;
                Log.e("retrofit", "called onFailure");
            }
        });
        return null;
    }

    @Override
    public boolean getIsBusy() {
        return isBusy;
    }

    public Searcher(PresenterInterface presenter) {
        mPresenter = presenter;
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(SEARCH_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        if(mHHApi == null){
            mHHApi = mRetrofit.create(HHApi.class);
        }
    }
}
