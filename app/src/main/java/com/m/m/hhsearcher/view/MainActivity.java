package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.presenter.PresenterInterface;

/**
 * Created by mac on 29.08.17.
 */

public class MainActivity extends AppCompatActivity implements FragmentManagerInterface {

    private Fragment mDisplayedFragment;
    private PresenterInterface mPresenterInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_layout);
        mPresenterInterface = Presenter.getInstance();
        mPresenterInterface.setFragmentManager(this);
        displaySearchFragment();
    }

    private void displayFragment(Fragment fragment, String TAG){
        mDisplayedFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container,fragment, TAG)
                .commit();
    }

    @Override
    public void displaySearchFragment() {
        displayFragment(new SearchFragment(), getString(R.string.search_fragment));
    }

    @Override
    public void displaySearchResultFragment() {
        displayFragment(new SearchResultFragment(), getString(R.string.search_result_fragment));
    }

    @Override
    public void displayVacancyFragment() {
        displayFragment(new VacancyFragment(), getString(R.string.vacancy_fragment));
    }
}
