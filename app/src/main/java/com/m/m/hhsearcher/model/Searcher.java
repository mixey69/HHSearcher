package com.m.m.hhsearcher.model;

import android.util.Log;

import com.m.m.hhsearcher.presenter.PresenterInterface;
import com.m.m.hhsearcher.vacancy.Example;

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
    private String searchWord;
    private Retrofit mRetrofit;
    private HHApi mHHApi;

    private final static String SEARCH_URL = "https://api.hh.ru/";

    @Override
    public LinkedList firstSearch(String searchWord) {
        mHHApi.getData(searchWord, 1,"publication_time", 10).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                mPresenter.updateView(response.body().items);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("retrofit", "called onFailure");
            }
        });
        return null;
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
