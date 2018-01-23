package com.ruanorz.marvelapp.views.comic_detail.presenter;

import com.ruanorz.marvelapp.Result;
import com.ruanorz.marvelapp.dbwrapper.Wrapper;
import com.ruanorz.marvelapp.networking.Service;
import com.ruanorz.marvelapp.views.comic_detail.interfaces.DetailComicView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by ruano on 23/01/2018.
 */

public class DetailPresenter {

    private final Service service;
    private final DetailComicView view;
    private CompositeSubscription subscriptions;


    public DetailPresenter(Service service, DetailComicView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public Result getComicListFromCharacterID(int comicID){

        return Wrapper.getInstance().getComicListFromCharacterID(comicID);
    }

    public void unsuscribeRxAndroid(){
        if (subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }

    public void closeRealm(){
        Wrapper.getInstance().closeRealm();
    }
}
