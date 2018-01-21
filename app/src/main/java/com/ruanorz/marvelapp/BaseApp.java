package com.ruanorz.marvelapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ruanorz.marvelapp.deps.DaggerDeps;
import com.ruanorz.marvelapp.deps.Deps;
import com.ruanorz.marvelapp.networking.NetworkModule;

import java.io.File;

/**
 * Created by ruano on 20/01/2018.
 */

public class BaseApp extends AppCompatActivity {
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}