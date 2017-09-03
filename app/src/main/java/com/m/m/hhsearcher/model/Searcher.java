package com.m.m.hhsearcher.model;

import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Example;
import com.m.m.hhsearcher.model.vacancy_item.Item;
import com.m.m.hhsearcher.presenter.PresenterModelInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
        mHHApi.getData(searchWord, mPresenter.getSearchTime(),"publication_time", 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallBackWrapper<Example>() {
                    @Override
                    protected void onSuccess(Example example) {
                        isBusy = false;
                        if(example.items != null) {
                            if(!example.items.isEmpty()) {
                                if (isFirstSearch) {
                                    mLatestFoundItemTime = getLatestPublicationTime(example.items.get(0));
                                }
                                mPresenter.updateView(example.items);
                            }else {
                                mPresenter.ShowErrorMessage("Invalid search word");
                            }
                        }
                    }
                });
    }

    @Override
    public void findVacancy(String vacancyId) {
        mHHApi.getVacancy(vacancyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallBackWrapper<Vacancy>() {
                    @Override
                    protected void onSuccess(Vacancy vacancy) {
                        mPresenter.displayVacancyData(vacancy);
                    }
                });
    }

    @Override
    public void searchForNew(String searchWord) {
        mHHApi.getNewData(searchWord, 1, "publication_time", 10, mLatestFoundItemTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallBackWrapper<Example>() {
                    @Override
                    protected void onSuccess(Example example) {
                        mLatestFoundItemTime = getLatestPublicationTime(example.items.get(0));
                        if (example.items != null){
                            mPresenter.refreshSearchResultView(example.items);
                        }
                    }
                });
    }

    private String getLatestPublicationTime(Item item){
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = dt.parse(item.createdAt.substring(0, 19));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, 1);
        return dt.format(c.getTime());
    }

    @Override
    public boolean getIsBusy() {
        return isBusy;
    }
}
