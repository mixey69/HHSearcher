package com.m.m.hhsearcher.model;

import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.model.vacancy_item.Example;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mac on 30.08.17.
 */

public interface HHApi {
    @GET("vacancies")
    Observable<Example> getData(@Query("text") String searchWord, @Query("page") int pageNumber, @Query("order_by") String orderBy, @Query("per_page") int perPage);

    @GET("vacancies/{id}")
    Observable<Vacancy> getVacancy(@Path("id") String vacancyId);

    @GET("vacancies")
    Observable<Example> getNewData(@Query("text") String searchWord, @Query("page") int pageNumber, @Query("order_by") String orderBy, @Query("per_page") int perPage, @Query("date_from") String timeOfLastRefreshment);

}
