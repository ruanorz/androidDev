package com.ruanorz.marvelapp.comic_list;

import com.ruanorz.marvelapp.CharacterListResponse;
import com.ruanorz.marvelapp.ComicListResponse;
import com.ruanorz.marvelapp.Result;
import com.ruanorz.marvelapp.networking.NetworkError;
import com.ruanorz.marvelapp.networking.Service;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
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

    private Realm realm;

    public ListPresenter(Service service, ComicView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getComicList(final int page) {
        view.showWait();

        Subscription subscription = service.getComicList(page ,new Service.GetComicListCallback() {
            @Override
            public void onSuccess(ComicListResponse comicListResponse) {

                comicListResponse.setSavedAt(new Date());

                fullComicList = comicListResponse.getData().getResults();

                realm = Realm.getDefaultInstance();

                //Copy comic list to db
                realm.beginTransaction();
                if (page==0) {
                    realm.delete(Result.class);
                }
                realm.copyToRealmOrUpdate(fullComicList);
                realm.commitTransaction();


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
        realm.close();
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
}