package com.ruanorz.marvelapp.networking;

import android.util.Log;

import com.ruanorz.marvelapp.ComicListResponse;
import com.ruanorz.marvelapp.utils.Constants;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

        Log.e("error", timeStamp+" - "+hash+" - "+stringToHash);

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
                    public void onNext(ComicListResponse cityListResponse) {
                        callback.onSuccess(cityListResponse);

                    }
                });
    }

    public interface GetComicListCallback{
        void onSuccess(ComicListResponse cityListResponse);

        void onError(NetworkError networkError);
    }

}