package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.vacancy.Item;

/**
 * Created by mac on 29.08.17.
 */

public class VacancyFragment extends Fragment {
    Item mDisplayedItem;

    public void setDisplayedItem(Item item){
        mDisplayedItem = item;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vacancyfragment_layout,container,false);
        TextView cityName = (TextView) view.findViewById(R.id.city);
        cityName.setText(mDisplayedItem.address == null ? "not defined" : mDisplayedItem.address.city);
        TextView jobName = (TextView) view.findViewById(R.id.job_name);
        jobName.setText(mDisplayedItem.name == null ? "not defined" : mDisplayedItem.name);
        TextView companyName = (TextView) view.findViewById(R.id.company_name);
        companyName.setText(mDisplayedItem.employer.name == null ? "not defined" : mDisplayedItem.employer.name);
        TextView employment = (TextView) view.findViewById(R.id.type_of_employment);
        employment.setText(mDisplayedItem.employment == null ? "not defined" : mDisplayedItem.employment.name);
        TextView salary = (TextView) view.findViewById(R.id.salary);
        salary.setText(mDisplayedItem.salary == null ? "not defined" : mDisplayedItem.salary.toString());
        TextView description = (TextView) view.findViewById(R.id.job_description);
        description.setText(mDisplayedItem.snippet == null ? "not defined" : mDisplayedItem.snippet.toString());
        return view;
    }
}
