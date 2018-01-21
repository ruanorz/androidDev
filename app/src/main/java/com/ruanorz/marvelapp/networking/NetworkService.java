package com.ruanorz.marvelapp.networking;


import com.ruanorz.marvelapp.ComicListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ruano on 20/01/2018.
 */

public interface NetworkService {

    String API_KEY = "apikey";
    String HASH = "hash";
    String TIMESTAMP = "ts";
    String LIMIT = "limit";
    String OFFSET = "offset";

    @GET("v1/public/comics?hasDigitalIssue=true")
    Observable<ComicListResponse> getComicList(
         @Query(TIMESTAMP) long timestamp,
         @Query(API_KEY) String publicKey,
         @Query(HASH) String hash,
         @Query(LIMIT) Integer limit,
         @Query(OFFSET) Integer offset);


}
