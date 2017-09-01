package com.m.m.hhsearcher.model;

import android.util.Log;

import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Example;
import com.m.m.hhsearcher.presenter.PresenterModelInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac on 30.08.17.
 */

public class Searcher implements SearcherInterface{
    private static volatile Searcher instance;
    private PresenterModelInterface mPresenter;
    private Retrofit mRetrofit;
    private HHApi mHHApi;
    private boolean isBusy;
    private String mLatestFoundItemTime;

    private final static String SEARCH_URL = "https://api.hh.ru/";

    private Searcher(PresenterModelInterface presenter) {
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

    public static Searcher getInstance(PresenterModelInterface presenter){
        Searcher localInstance = instance;
        if (localInstance == null) {
            instance = localInstance = new Searcher(presenter);
        }
        return localInstance;
    }

    @Override
    public void search(String searchWord, final boolean isFirstSearch) {
        isBusy = true;
        mHHApi.getData(searchWord, mPresenter.getSearchTime(),"publication_time", 10).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                isBusy = false;
                if (response.body().items != null){
                    if (isFirstSearch){
                        mLatestFoundItemTime = response.body().items.get(0).createdAt.substring(0,19);
                        //TODO:intercepting with searchfornew() data. loading all items twice
                    }
                }
                mPresenter.updateView(response.body().items);
            }
            /*
            date_from – дата, которая ограничивает снизу диапазон дат публикации вакансий.
            Нельзя передавать вместе с параметром period.
            Значение указывается в формате ISO 8601 - YYYY-MM-DD или с точность до секунды YYYY-MM-DDThh:mm:ss±hhmm.
             */

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                isBusy = false;
                Log.e("retrofit", "called onFailure");
            }
        });
    }

    @Override
    public void findVacancy(String vacancyId) {
        mHHApi.getVacancy(vacancyId).enqueue(new Callback<Vacancy>() {
            @Override
            public void onResponse(Call<Vacancy> call, Response<Vacancy> response) {
                mPresenter.displayVacancyData(response.body());
            }

            @Override
            public void onFailure(Call<Vacancy> call, Throwable t) {
                Log.e("retrofit", "called onFailure");
            }
        });
    }

    @Override
    public void searchForNew(String searchWord) {
        mHHApi.getNewData(searchWord, 1, "publication_time", 10, mLatestFoundItemTime).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.body() == null || response.body().items == null ){
                    return;
                }else {
                    Log.e("response",response.body().items.toString());
                    mLatestFoundItemTime = response.body().items.get(0).createdAt.substring(0,19);
                    mPresenter.refreshSearchResultView(response.body().items);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean getIsBusy() {
        return isBusy;
    }
}
