package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.presenter.PresenterViewInterface;

/**
 * Created by mac on 29.08.17.
 */

public class MainActivity extends AppCompatActivity implements FragmentManagerInterface {

    private PresenterViewInterface mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_layout);
        mPresenter = Presenter.getInstance();
        mPresenter.onFragmentManagerCreated(this);
    }

    private void displayFragment(ViewFragment fragment, String TAG){
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(TAG)
                .replace(R.id.main_fragment_container,fragment, TAG)
                .commitAllowingStateLoss();
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
