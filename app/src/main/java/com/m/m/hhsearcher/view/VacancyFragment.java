package com.m.m.hhsearcher.view;

import android.os.Build;
import android.text.Html;
import android.widget.TextView;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.model.vacancy.Vacancy;

import butterknife.BindView;

/**
 * Created by mac on 29.08.17.
 */

public class VacancyFragment extends ViewFragment {
    @BindView(R.id.city) TextView mCityName;
    @BindView(R.id.job_name) TextView mJobName;
    @BindView(R.id.company_name) TextView mCompanyName;
    @BindView(R.id.type_of_employment) TextView mEmployment;
    @BindView(R.id.salary) TextView mSalary;
    @BindView(R.id.job_description) TextView mDescription;
    Vacancy mDisplayedVacancy;

    @Override
    public void onStart() {
        super.onStart();
        if(mDisplayedVacancy == null){
           mDisplayedVacancy = mPresenter.getVacancy();
        }
        mCityName.setText(mDisplayedVacancy.address == null ? "not defined" : mDisplayedVacancy.address.city);
        mJobName.setText(mDisplayedVacancy.name == null ? "not defined" : mDisplayedVacancy.name);
        mCompanyName.setText(mDisplayedVacancy.employer.name == null ? "not defined" : mDisplayedVacancy.employer.name);
        mEmployment.setText(mDisplayedVacancy.employment == null ? "not defined" : mDisplayedVacancy.employment.name);
        mSalary.setText(mDisplayedVacancy.salary == null ? "not defined" : mDisplayedVacancy.salary.toString());
        mDescription.setText(mDisplayedVacancy.description == null ? "not defined" : Build.VERSION.SDK_INT <= 24 ? Html.fromHtml(mDisplayedVacancy.description) : Html.fromHtml(mDisplayedVacancy.description,Html.FROM_HTML_MODE_COMPACT));
    }

    public void setDisplayedVacancy(Vacancy mDisplayedVacancy) {
        this.mDisplayedVacancy = mDisplayedVacancy;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vacancyfragment_layout;
    }
}
