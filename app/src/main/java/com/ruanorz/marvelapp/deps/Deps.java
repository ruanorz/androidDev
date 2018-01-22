package com.ruanorz.marvelapp.deps;

import com.ruanorz.marvelapp.views.comic_list.ListActivity;
import com.ruanorz.marvelapp.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(ListActivity listActivity);
}