package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.model.vacancy.Vacancy;
import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.presenter.PresenterInterface;

/**
 * Created by mac on 29.08.17.
 */

public class MainActivity extends AppCompatActivity implements FragmentManagerInterface {

    private PresenterInterface mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_layout);
        mPresenter = Presenter.getInstance();
        mPresenter.onFragmentManagerCreated(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.clearViewLink("ACTIVITY");
        mPresenter.onActivityRestarting();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setFragmentManager(this);
    }

    private void displayFragment(ViewFragment fragment, String TAG){
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(TAG)
                .replace(R.id.main_fragment_container,fragment, TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void makeAToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
    public void displayVacancyFragment(Vacancy vacancy) {
        VacancyFragment fragment = new VacancyFragment();
        fragment.setDisplayedVacancy(vacancy);
        displayFragment(fragment, getString(R.string.vacancy_fragment));
    }
}
