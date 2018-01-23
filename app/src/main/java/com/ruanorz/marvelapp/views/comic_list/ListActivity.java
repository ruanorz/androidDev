package com.ruanorz.marvelapp.views.comic_list;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ruanorz.marvelapp.BaseApp;
import com.ruanorz.marvelapp.CharacterListResponse;
import com.ruanorz.marvelapp.ComicListResponse;
import com.ruanorz.marvelapp.R;
import com.ruanorz.marvelapp.Result;
import com.ruanorz.marvelapp.utils.UtilsUI;
import com.ruanorz.marvelapp.views.comic_list.adapter.CharacterListAdapter;
import com.ruanorz.marvelapp.views.comic_list.adapter.ComicListAdapter;
import com.ruanorz.marvelapp.networking.Service;
import com.ruanorz.marvelapp.utils.EndlessRecyclerViewScrollListener;
import com.ruanorz.marvelapp.views.comic_list.interfaces.ComicView;
import com.ruanorz.marvelapp.views.comic_list.presenter.ListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;

public class ListActivity extends BaseApp implements ComicView {

    private RecyclerView list;
    private RecyclerView list_characters;
    @Inject
    public Service service;
    ProgressBar progressBar;

    private EndlessRecyclerViewScrollListener scrollListener;
    private EndlessRecyclerViewScrollListener scrollListenerCharacters;
    RealmResults<Result> fullComicList;
    List<Result> fullComicListFromCharacter;
    List<Result> fullCharacterList;
    ComicListAdapter adapter;
    CharacterListAdapter adapterCharacters;

    private ListPresenter presenter;

    private RelativeLayout ly_progress_scroll;

    private RelativeLayout btn_fab;

    private RelativeLayout dialog_search;

    private ImageView dialog_filter_btn_fab;

    public float finalPositionX;
    public float finalPositionY;
    public boolean dialogOpened = false;
    private ImageView iv_lupa;
    private RelativeLayout btn_fab_initial;
    private boolean searchingByCharacter = false;
    private RelativeLayout loading_more_characters;
    private RelativeLayout tv_no_comics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);


        renderView();
        init();

        presenter = new ListPresenter(service, this);
        presenter.getComicList(0);

        fullComicList = presenter.getComicListFromDB();

        fullCharacterList = new ArrayList<>();

        adapter = new ComicListAdapter(fullComicList, this);
        list.setAdapter(adapter);

        adapterCharacters = new CharacterListAdapter(fullCharacterList, this, presenter);
        list_characters.setAdapter(adapterCharacters);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(linearLayoutManager);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                presenter.getComicList(page);

                ly_progress_scroll.setVisibility(View.VISIBLE);
            }
        };
        // Adds the scroll listener to RecyclerView
        list.addOnScrollListener(scrollListener);

        LinearLayoutManager linearLayoutManagerCharacter = new LinearLayoutManager(this);
        list_characters.setLayoutManager(linearLayoutManagerCharacter);
        scrollListenerCharacters = new EndlessRecyclerViewScrollListener(linearLayoutManagerCharacter) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                presenter.getCharacterList(page);

                loading_more_characters.setVisibility(View.VISIBLE);

            }
        };
        // Adds the scroll listener to RecyclerView
        list_characters.addOnScrollListener(scrollListenerCharacters);


        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revealCharacterDialog();
            }
        });



        ViewTreeObserver vto = dialog_filter_btn_fab.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                dialog_filter_btn_fab.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                finalPositionY = dialog_filter_btn_fab.getY();
                finalPositionX = dialog_filter_btn_fab.getX();
                // Don't forget to remove your listener when you are done with it.


            }
        });
    }

    public  void renderView(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            //fade.excludeTarget(R.id.appBar, true);
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);

            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // inside your activity (if you did not enable transitions in your theme)
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            // set an exit transition
            getWindow().setExitTransition(new Fade());
        }

        setContentView(R.layout.activity_list);

        ly_progress_scroll = (RelativeLayout) findViewById(R.id.ly_progress_scroll);
        btn_fab = (RelativeLayout) findViewById(R.id.btn_fab);
        dialog_search = (RelativeLayout) findViewById(R.id.dialog_search);
        dialog_filter_btn_fab = (ImageView) findViewById(R.id.dialog_filter_btn_fab);
        iv_lupa = (ImageView) findViewById(R.id.iv_lupa);
        btn_fab_initial = (RelativeLayout) findViewById(R.id.btn_fab_initial);
        list = (RecyclerView) findViewById(R.id.list);
        list_characters = (RecyclerView) findViewById(R.id.list_characters);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        loading_more_characters = (RelativeLayout) findViewById(R.id.loading_more_characters);
        tv_no_comics = (RelativeLayout) findViewById(R.id.tv_no_comics);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
        list_characters.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getComicListSuccess(ComicListResponse comicListResponse) {

        fullComicList = presenter.getComicListFromDB();


        ly_progress_scroll.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void getCharacterListSuccess(CharacterListResponse characterListResponse) {

//        fullCharacterList = characterListResponse.getData().getResults();
        fullCharacterList.addAll(characterListResponse.getData().getResults());
        loading_more_characters.setVisibility(View.GONE);
        adapterCharacters.notifyDataSetChanged();

    }


    @Override
    public void getComicListFromCharacterIDSuccess(ComicListResponse comicListResponse) {

        searchingByCharacter = true;
        if (comicListResponse.getData().getResults().size()<1){
            tv_no_comics.setVisibility(View.VISIBLE);
        }else {
            tv_no_comics.setVisibility(View.GONE);
        }

        fullComicList = presenter.getComicListFromDB();

        ly_progress_scroll.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.closeRealm();
        presenter.unsuscribeRxAndroid();

    }

    private void revealCharacterDialog(){

        moveFAB();

        presenter.getCharacterList(0);
    }
























    private void moveFAB(){

        if (!dialogOpened) {

            if (searchingByCharacter){
                searchingByCharacter=false;
                presenter.getComicList(0);





                ObjectAnimator anim = ObjectAnimator.ofFloat(iv_lupa,"alpha",0.0f);
                anim.setDuration(200);
                anim.start();
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                            iv_lupa.setImageDrawable(getDrawable(R.drawable.search));
                            ObjectAnimator anim2 = ObjectAnimator.ofFloat(iv_lupa,"alpha",1.0f);
                            anim2.setDuration(200);
                            anim2.start();
                        }
                });
            }else {
                openCharacterDialog();
            }
//            OPEN DIALOG

        }else {

//            CLOSE DIALOG
            closeCharacterDialog();

        }

    }
    public void setAnimValues(ObjectAnimator objectAnimator,int duration,int repeatMode)
    {
        objectAnimator.setDuration(duration);
        objectAnimator.setRepeatMode(repeatMode);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void closeCharacterDialog(){

        dialogOpened=false;
        btn_fab.setClickable(false);

        int cx = dialog_search.getWidth();
        int cy = dialog_search.getHeight();

        int startX = (int) (finalPositionX+ UtilsUI.dpToPx(56/2));
        int startY = (int) (0);

        float finalRadius = Math.max(cx, cy) * 1.2f;

        Animator reveal = ViewAnimationUtils
                .createCircularReveal(dialog_search, startX, startY, finalRadius, 0f);

        reveal.setDuration(1000);

        reveal.start();

        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dialog_search.setVisibility(View.INVISIBLE);

                ObjectAnimator anim = ObjectAnimator.ofFloat(btn_fab,"scaleX",1.0f);
                anim.setDuration(1000);
                anim.start();

                // Make the object height 50%
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(btn_fab,"scaleY",1.0f);
                anim2.setDuration(1000);
                anim2.start();

                iv_lupa.animate()
                        .alpha(0f)
                        .setDuration(200)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);


                                if (searchingByCharacter){
                                    iv_lupa.setImageDrawable(getDrawable(R.drawable.clear));
                                }else{
                                    iv_lupa.setImageDrawable(getDrawable(R.drawable.search));
                                }

                                ObjectAnimator anim4 = ObjectAnimator.ofFloat(iv_lupa,"alpha",1.0f);
                                anim4.setDuration(200);
                                anim4.start();
                                anim4.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        if (searchingByCharacter){
                                            iv_lupa.setImageDrawable(getDrawable(R.drawable.clear));
                                        }else{
                                            iv_lupa.setImageDrawable(getDrawable(R.drawable.search));
                                        }
                                    }
                                });

                                Path path2 = new Path();
                                path2.moveTo(btn_fab.getX(), btn_fab.getY());
                                path2.quadTo(finalPositionX * 2, finalPositionY, btn_fab_initial.getX(), btn_fab_initial.getY());

                                ObjectAnimator objectAnimator2 =
                                        ObjectAnimator.ofFloat(btn_fab, View.X, View.Y, path2);
                                setAnimValues(objectAnimator2, 1200, ValueAnimator.REVERSE);
                                objectAnimator2.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);

                                        btn_fab.setClickable(true);
                                    }
                                });
                                objectAnimator2.start();
                            }
                        })
                        .start();
            }

        });

    }

    public void openCharacterDialog(){

        dialogOpened=true;
        btn_fab.setClickable(false);
        Path path = new Path();
        path.moveTo(btn_fab.getX(), btn_fab.getY());
        path.quadTo(finalPositionX * 2, finalPositionY, dialog_filter_btn_fab.getX(), dialog_filter_btn_fab.getY());

        ObjectAnimator objectAnimator =
                ObjectAnimator.ofFloat(btn_fab, View.X, View.Y, path);
        setAnimValues(objectAnimator, 1200, ValueAnimator.REVERSE);
        objectAnimator.start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);


                iv_lupa.animate()
                        .alpha(0f)
                        .setDuration(200)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                iv_lupa.setImageDrawable(getDrawable(R.drawable.close_icon));
                                iv_lupa.animate()
                                        .alpha(1f)
                                        .setDuration(200)
                                        .start();
                            }
                        })
                        .start();

                ObjectAnimator anim = ObjectAnimator.ofFloat(btn_fab,"scaleX",2.0f);
                anim.setDuration(1000); // duration 3 seconds
                anim.start();

                // Make the object height 50%
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(btn_fab,"scaleY",2.0f);
                anim2.setDuration(1000); // duration 3 seconds
                anim2.start();

                dialog_search.setVisibility(View.VISIBLE);

                int cx = dialog_search.getWidth();
                int cy = dialog_search.getHeight();

                int startX = (int) (finalPositionX+UtilsUI.dpToPx(56/2));
                int startY = (int) (0);

                float finalRadius = Math.max(cx, cy) * 1.2f;

                Animator reveal = ViewAnimationUtils
                        .createCircularReveal(dialog_search, startX, startY, 0f, finalRadius);

                reveal.setDuration(1000);

                reveal.start();

                reveal.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        btn_fab.setClickable(true);
                    }
                });

            }
        });

    }
}