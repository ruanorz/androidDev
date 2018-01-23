package com.ruanorz.marvelapp.views.comic_list.presenter;

import com.ruanorz.marvelapp.CharacterListResponse;
import com.ruanorz.marvelapp.ComicListResponse;
import com.ruanorz.marvelapp.Result;
import com.ruanorz.marvelapp.dbwrapper.Wrapper;
import com.ruanorz.marvelapp.networking.NetworkError;
import com.ruanorz.marvelapp.networking.Service;
import com.ruanorz.marvelapp.views.comic_list.interfaces.ComicView;

import java.util.Date;
import java.util.List;

import io.realm.RealmResults;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ruano on 20/01/2018.
 */

public class ListPresenter {
    private final Service service;
    private final ComicView view;
    private CompositeSubscription subscriptions;

    private List<Result> fullComicList;


    public ListPresenter(Service service, ComicView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getComicList(final int page) {
        if (page==0) {
            view.showWait();
        }

        Subscription subscription = service.getComicList(page ,new Service.GetComicListCallback() {
            @Override
            public void onSuccess(ComicListResponse comicListResponse) {

                comicListResponse.setSavedAt(new Date());

                fullComicList = comicListResponse.getData().getResults();

                Wrapper.getInstance().saveComics(page, fullComicList);

                view.removeWait();
                view.getComicListSuccess(comicListResponse);

            }

            @Override
            public void onError(NetworkError networkError) {

                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }


    public void onStop() {
        subscriptions.unsubscribe();
    }



    public void closeRealm(){
        Wrapper.getInstance().closeRealm();
    }




    public void getCharacterList(final int page) {


        Subscription subscription = service.getCharacterList(page ,new Service.GetCharacterCallback() {
            @Override
            public void onSuccess(CharacterListResponse characterListResponse) {


                view.getCharacterListSuccess(characterListResponse);

            }

            @Override
            public void onError(NetworkError networkError) {

                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public RealmResults<Result> getComicListFromDB(){

        return Wrapper.getInstance().getAllComics();
    }


    public void getComicListFromCharacterID(final int page, int characterID) {

        if (page==0) {
            view.showWait();
        }

        Subscription subscription = service.getComicListFromCharacterID(page, characterID, new Service.GetComicListFromCharacterIDCallback() {
            @Override
            public void onSuccess(ComicListResponse comicListResponse) {


                fullComicList = comicListResponse.getData().getResults();

                Wrapper.getInstance().saveComics(page, fullComicList);

                view.removeWait();
                view.getComicListFromCharacterIDSuccess(comicListResponse);

            }

            @Override
            public void onError(NetworkError networkError) {

                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void unsuscribeRxAndroid(){
        if (subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }

}