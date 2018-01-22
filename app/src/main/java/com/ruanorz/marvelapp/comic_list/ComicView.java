package com.ruanorz.marvelapp.comic_list;


import com.ruanorz.marvelapp.CharacterListResponse;
import com.ruanorz.marvelapp.ComicListResponse;

/**
 * Created by ruano on 20/01/2018.
 */

public interface ComicView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getComicListSuccess(ComicListResponse comicListResponse);

    void getCharacterListSuccess(CharacterListResponse characterListResponse);

}