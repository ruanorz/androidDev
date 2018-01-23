package com.ruanorz.marvelapp.networking;


import com.ruanorz.marvelapp.CharacterListResponse;
import com.ruanorz.marvelapp.ComicListResponse;
import com.ruanorz.marvelapp.utils.Constants;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ruano on 20/01/2018.
 */

public class Service {


    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getComicList(int page, final GetComicListCallback callback) {

        long timeStamp = System.currentTimeMillis();
        String stringToHash = timeStamp + Constants.getPrivateKey() + Constants.getPublicKey();
        String hash = new String(Hex.encodeHex(DigestUtils.md5(stringToHash)));

        Integer offsetPetition=page*Constants.getLimitPerPage();


        return networkService.getComicList(timeStamp, Constants.getPublicKey(), hash, Constants.getLimitPerPage(), offsetPetition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ComicListResponse>>() {
                    @Override
                    public Observable<? extends ComicListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ComicListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ComicListResponse comicListResponse) {
                        callback.onSuccess(comicListResponse);

                    }
                });
    }

    public interface GetComicListCallback{
        void onSuccess(ComicListResponse comicListResponse);

        void onError(NetworkError networkError);
    }



    public Subscription getCharacterList(int page, final GetCharacterCallback callback) {

        long timeStamp = System.currentTimeMillis();
        String stringToHash = timeStamp + Constants.getPrivateKey() + Constants.getPublicKey();
        String hash = new String(Hex.encodeHex(DigestUtils.md5(stringToHash)));


        Integer offsetPetition=page*Constants.getLimitPerPage();


        return networkService.getCharacterList(timeStamp, Constants.getPublicKey(), hash, Constants.getLimitPerPage(), offsetPetition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CharacterListResponse>>() {
                    @Override
                    public Observable<? extends CharacterListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CharacterListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CharacterListResponse comicListResponse) {
                        callback.onSuccess(comicListResponse);

                    }
                });
    }

    public interface GetCharacterCallback{
        void onSuccess(CharacterListResponse comicListResponse);

        void onError(NetworkError networkError);
    }


    public Subscription getComicListFromCharacterID(int page, int characterID, final GetComicListFromCharacterIDCallback callback) {

        long timeStamp = System.currentTimeMillis();
        String stringToHash = timeStamp + Constants.getPrivateKey() + Constants.getPublicKey();
        String hash = new String(Hex.encodeHex(DigestUtils.md5(stringToHash)));


        Integer offsetPetition=page*Constants.getLimitPerPage();


        return networkService.getComicListFromCharacterID(timeStamp, Constants.getPublicKey(), hash, Constants.getLimitPerPage(), offsetPetition, characterID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ComicListResponse>>() {
                    @Override
                    public Observable<? extends ComicListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ComicListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ComicListResponse comicListResponse) {
                        callback.onSuccess(comicListResponse);

                    }
                });
    }

    public interface GetComicListFromCharacterIDCallback{
        void onSuccess(ComicListResponse comicListResponse);

        void onError(NetworkError networkError);
    }

}