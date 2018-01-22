package com.ruanorz.marvelapp.dbwrapper;

import com.ruanorz.marvelapp.Result;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ruano on 22/01/2018.
 */


public class Wrapper {
    private static Realm realm;

    public static RealmResults<Result> getAllComics(){

        realm = Realm.getDefaultInstance();

        return realm.where(Result.class)
                .findAll();
    }

    public static void saveComics(int page, List<Result> fullComicList){

        realm = Realm.getDefaultInstance();

        //Copy comic list to db
        realm.beginTransaction();
        if (page==0) {
            realm.delete(Result.class);
        }
        realm.copyToRealmOrUpdate(fullComicList);
        realm.commitTransaction();
    }

    public static void closeRealm(){
        realm.close();
    }

}
