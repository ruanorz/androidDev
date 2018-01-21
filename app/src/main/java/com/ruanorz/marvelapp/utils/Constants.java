package com.ruanorz.marvelapp.utils;

/**
 * Created by ruano on 20/01/2018.
 */

public class Constants {

    private static String PUBLIC_KEY = "8e8ec19e20209ce058e57958ae823c48";
    private static String PRIVATE_KEY = "32f7852b3630f2b6c91342f1b0ada27d4414024d";
    private static String BASE_URL = "http://gateway.marvel.com/";
    private static Integer CACHE_TIME = 6000;
    private static Integer LIMIT_PER_PAGE = 20;

    public static String getBASE_URL() {
        return BASE_URL;
    }

    public static Integer getCACHE_TIME() {
        return CACHE_TIME;
    }

    public static String getPublicKey() {
        return PUBLIC_KEY;
    }

    public static String getPrivateKey() {
        return PRIVATE_KEY;
    }

    public static Integer getLimitPerPage() {
        return LIMIT_PER_PAGE;
    }
}
