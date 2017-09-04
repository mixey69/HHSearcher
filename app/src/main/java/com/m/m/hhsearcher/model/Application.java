package com.m.m.hhsearcher.model;

/**
 * Created by mac on 04.09.17.
 */

public class Application extends android.app.Application {

    private static Application instance;
    private InjectionComponent component;

    public static Application getInstance() {
        return instance;
    }

    public InjectionComponent getInjectionComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerInjectionComponent.builder().mainModule(new MainModule(this)).build();
    }
}
