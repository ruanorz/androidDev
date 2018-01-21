package com.ruanorz.marvelapp.comic_list;

import android.animation.Animator;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ruanorz.marvelapp.BaseApp;
import com.ruanorz.marvelapp.ComicListResponse;
import com.ruanorz.marvelapp.R;
import com.ruanorz.marvelapp.Result;
import com.ruanorz.marvelapp.comic_list.adapter.ComicListAdapter;
import com.ruanorz.marvelapp.networking.Service;
import com.ruanorz.marvelapp.utils.EndlessRecyclerViewScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListActivity extends BaseApp implements ComicView {

    private RecyclerView list;
    @Inject
    public Service service;
    ProgressBar progressBar;

    private EndlessRecyclerViewScrollListener scrollListener;
    RealmResults<Result> fullComicList;
    ComicListAdapter adapter;

    private ListPresenter presenter;

    private Realm realm;

    private RelativeLayout ly_progress_scroll;

    private FloatingActionButton btn_fab;

    private RelativeLayout dialog_search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        realm = Realm.getDefaultInstance();

        renderView();
        init();

        presenter = new ListPresenter(service, this);
        presenter.getComicList(0);

        fullComicList = realm.where(Result.class)
                .findAll();
        adapter = new ComicListAdapter(fullComicList, this);
        list.setAdapter(adapter);

        ly_progress_scroll = (RelativeLayout) findViewById(R.id.ly_progress_scroll);
        btn_fab = (FloatingActionButton) findViewById(R.id.btn_fab);
        dialog_search = (RelativeLayout) findViewById(R.id.dialog_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(linearLayoutManager);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                presenter.getComicList(page);
                Log.e("error", "NECESITO MOAAAAAAAAAAAAAAR");
                ly_progress_scroll.setVisibility(View.VISIBLE);
            }
        };
        // Adds the scroll listener to RecyclerView
        list.addOnScrollListener(scrollListener);


        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revealCharacterDialog();
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

        list = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
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


        fullComicList = realm.where(Result.class)
                        .findAll();

        Log.e("error", "AAAAAAAAAAAAAAA "+realm.where(Result.class).contains("title", "Civil War").count()+ " - " +realm.where(Result.class).count());

        ly_progress_scroll.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.closeRealm();
    }



    private void revealCharacterDialog(){

        dialog_search.setVisibility(View.VISIBLE);

        int cx = dialog_search.getWidth();
        int cy = dialog_search.getHeight();

        int startX = (int) (btn_fab.getX());
        int startY = (int) (btn_fab.getY());

        float finalRadius = Math.max(cx, cy) * 2.9f;

        Animator reveal = ViewAnimationUtils
                .createCircularReveal(dialog_search, startX, startY, 0f, finalRadius);

        reveal.setDuration(700);


        reveal.start();

    }
}