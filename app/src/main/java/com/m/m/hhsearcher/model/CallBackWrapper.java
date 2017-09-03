package com.m.m.hhsearcher.model;

import com.m.m.hhsearcher.presenter.Presenter;

import java.io.IOException;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by mac on 03.09.17.
 */

abstract class CallBackWrapper <T> extends DisposableObserver<T> {

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof IOException){
            Presenter.getInstance().ShowErrorMessage("Connection problem. Check your Internet connection and try again");
        }else {
            Presenter.getInstance().ShowErrorMessage(e.toString());
        }
    }

    @Override
    public void onComplete() {}
}
