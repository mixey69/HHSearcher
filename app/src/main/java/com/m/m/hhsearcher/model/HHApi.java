package com.m.m.hhsearcher.model;

import com.m.m.hhsearcher.vacancy.Vacancy;
import com.m.m.hhsearcher.vacancy_item.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mac on 30.08.17.
 */

public interface HHApi {
    @GET("vacancies")
    Call<Example> getData(@Query("text") String searchWord, @Query("page") int pageNumber, @Query("order_by") String orderBy, @Query("per_page") int perPage);

    @GET("vacancies/{id}")
    Call<Vacancy> getVacancy(@Path("id") String vacancyId);

}
