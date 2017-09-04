package com.m.m.hhsearcher.model;

import com.m.m.hhsearcher.presenter.Presenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mac on 04.09.17.
 */

@Singleton
@Component(modules = MainModule.class)
public interface InjectionComponent {

    void inject(Presenter presenter);

}
