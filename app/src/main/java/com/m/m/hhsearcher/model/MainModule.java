package com.m.m.hhsearcher.model;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 04.09.17.
 */

@Module
class MainModule {

    private Context mContext;

    MainModule(Context context){
        mContext = context;
    }

    @Provides
    @Singleton
    SearcherInterface providesSearcherInterface(){
        return new Searcher();
    }
}
