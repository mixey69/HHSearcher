package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.vacancy.Vacancy;

/**
 * Created by mac on 29.08.17.
 */

public class VacancyFragment extends Fragment implements VacancyViewInterface{
    Vacancy mDisplayedVacancy;
    Presenter mPresenter;


    @Override
    public void showVacancy(Vacancy vacancy) {
        mDisplayedVacancy = vacancy;
        View view = getView();
        TextView cityName = (TextView) view.findViewById(R.id.city);
        cityName.setText(mDisplayedVacancy.address == null ? "not defined" : mDisplayedVacancy.address.city);
        TextView jobName = (TextView) view.findViewById(R.id.job_name);
        jobName.setText(mDisplayedVacancy.name == null ? "not defined" : mDisplayedVacancy.name);
        TextView companyName = (TextView) view.findViewById(R.id.company_name);
        companyName.setText(mDisplayedVacancy.employer.name == null ? "not defined" : mDisplayedVacancy.employer.name);
        TextView employment = (TextView) view.findViewById(R.id.type_of_employment);
        employment.setText(mDisplayedVacancy.employment == null ? "not defined" : mDisplayedVacancy.employment.name);
        TextView salary = (TextView) view.findViewById(R.id.salary);
        salary.setText(mDisplayedVacancy.salary == null ? "not defined" : mDisplayedVacancy.salary.toString());
        TextView description = (TextView) view.findViewById(R.id.job_description);
        description.setText(mDisplayedVacancy.description == null ? "not defined" : Html.fromHtml(mDisplayedVacancy.description));
        //TODO: use butterknife and get rid of deprecated method
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = Presenter.getInstance();
        mPresenter.setVacancyView(this);
        View view = inflater.inflate(R.layout.vacancyfragment_layout,container,false);
        return view;
    }
}
