package com.ruanorz.marvelapp.dbwrapper;

import com.ruanorz.marvelapp.Result;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ruano on 22/01/2018.
 */


public class Wrapper {

    private static Wrapper wrapperInstance = null;

    private static Realm realm;

    public static Wrapper getInstance(){
        if (wrapperInstance == null){
            wrapperInstance = new Wrapper();

            realm = Realm.getDefaultInstance();
        }
        return wrapperInstance;
    }

    public RealmResults<Result> getAllComics(){



        return realm.where(Result.class)
                .findAll();
    }

    public void saveComics(int page, List<Result> fullComicList){



        //Copy comic list to db
        realm.beginTransaction();
        if (page==0) {
            realm.delete(Result.class);
        }
        realm.copyToRealmOrUpdate(fullComicList);
        realm.commitTransaction();
    }

    public Result getComicListFromCharacterID(int comicID){



        return realm.where(Result.class).equalTo("id", comicID).findFirst();
    }

    public void closeRealm(){
        realm.close();
    }

}
