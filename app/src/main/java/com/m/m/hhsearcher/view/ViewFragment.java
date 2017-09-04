package com.m.m.hhsearcher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.presenter.PresenterInterface;

import butterknife.ButterKnife;

/**
 * Created by mac on 01.09.17.
 */

public abstract class ViewFragment extends Fragment {
    protected PresenterInterface mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = Presenter.getInstance();
        View view = inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int getLayoutId();
}
