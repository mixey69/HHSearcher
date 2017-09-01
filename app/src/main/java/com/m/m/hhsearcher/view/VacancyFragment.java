package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.model.vacancy.Vacancy;

import butterknife.BindView;

/**
 * Created by mac on 29.08.17.
 */

public class VacancyFragment extends ViewFragment implements VacancyViewInterface{
    @BindView(R.id.city) TextView mCityName;
    @BindView(R.id.job_name) TextView mJobName;
    @BindView(R.id.company_name) TextView mCompanyName;
    @BindView(R.id.type_of_employment) TextView mEmployment;
    @BindView(R.id.salary) TextView mSalary;
    @BindView(R.id.job_description) TextView mDescription;
    Vacancy mDisplayedVacancy;


    @Override
    public void showVacancy(Vacancy vacancy) {
        mDisplayedVacancy = vacancy;
        mCityName.setText(mDisplayedVacancy.address == null ? "not defined" : mDisplayedVacancy.address.city);
        mJobName.setText(mDisplayedVacancy.name == null ? "not defined" : mDisplayedVacancy.name);
        mCompanyName.setText(mDisplayedVacancy.employer.name == null ? "not defined" : mDisplayedVacancy.employer.name);
        mEmployment.setText(mDisplayedVacancy.employment == null ? "not defined" : mDisplayedVacancy.employment.name);
        mSalary.setText(mDisplayedVacancy.salary == null ? "not defined" : mDisplayedVacancy.salary.toString());
        mDescription.setText(mDisplayedVacancy.description == null ? "not defined" : Html.fromHtml(mDisplayedVacancy.description));
        //TODO: get rid of deprecated method
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        mPresenter.setVacancyView(this);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vacancyfragment_layout;
    }
}
