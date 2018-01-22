package com.ruanorz.marvelapp.comic_detail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanorz.marvelapp.R;
import com.ruanorz.marvelapp.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private Realm realm;
    private Result comic;

    @BindView(R.id.iv_comic_image)
    ImageView iv_comic_image;
    @BindView(R.id.miniaturaComic)
    ImageView miniaturaComic;

    @BindView(R.id.comic_description)
    TextView comic_description;

    @BindView(R.id.tv_title_comic)
    TextView tv_title_comic;

    @BindView(R.id.comic_price)
    TextView comic_price;

    @BindView(R.id.comic_authors)
    TextView comic_authors;

    private boolean collapsed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        supportPostponeEnterTransition();

        Integer ID_COMIC_CLICKED = null;

        if (getIntent().hasExtra("ID_COMIC_CLICKED")) {
            ID_COMIC_CLICKED = getIntent().getIntExtra("ID_COMIC_CLICKED",0);
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + "ID_COMIC_CLICKED");
        }

        Toast.makeText(this,"COMIC_ID: "+ID_COMIC_CLICKED,
                Toast.LENGTH_LONG).show();

        comic = realm.where(Result.class).equalTo("id", ID_COMIC_CLICKED).findFirst();

        String comic_photo_url = comic.getThumbnail().getPath() +"."+ comic.getThumbnail().getExtension().toLowerCase();


        if (comic_photo_url.contains("image_not_available")){
            iv_comic_image.setImageDrawable(this.getDrawable(R.drawable.defaultbackground));
            supportStartPostponedEnterTransition();
        }else {
            Picasso.with(this)
                    .load(comic_photo_url)
                    .fit()
                    .centerCrop()
                    .into(iv_comic_image, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();

                            miniaturaComic.animate().alpha(1.0f).setDuration(1000).start();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        }


        Picasso.with(this)
                .load(comic_photo_url)
                .resize(300, 450)
                .into(miniaturaComic, new Callback() {
                    @Override
                    public void onSuccess() {
                        miniaturaComic.animate().alpha(1.0f).setDuration(1000).start();
                    }

                    @Override
                    public void onError() {
                        miniaturaComic.animate().alpha(1.0f).setDuration(1000).start();
                    }
                });



        if (comic!=null && comic.getDescription()!=null && comic.getVariantDescription()!=null) {
            if (comic.getDescription()!=null) {
                comic_description.setText(comic.getDescription().toString());
            }else{
                comic_description.setText(comic.getVariantDescription().toString());
            }
        }


        if (comic.getPrices().get(0).getPrice()!=0.0) {
            comic_price.setText(getString(R.string.price, comic.getPrices().get(0).getPrice().toString())+"€");
        }else {
            comic_price.setText(getString(R.string.price, "FREE"));
        }

        tv_title_comic.setText(comic.getTitle().trim());


        String authors = "";
        for (int i=0; i<comic.getCreators().getItems().size();i++){
            authors += comic.getCreators().getItems().get(i).getName()+"("+comic.getCreators().getItems().get(i).getRole()+")\n";
        }
        if(comic.getCreators().getItems().size()>0){
            comic_authors.setText(authors);
        }

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbarLayout);
        appBarLayout.addOnOffsetChangedListener(this);

    }

    @Override
    public void onBackPressed() {


        miniaturaComic.animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ActivityCompat.finishAfterTransition(DetailActivity.this);
            }
        }).start();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset)
    {

        if (offset > -120)
        {
            // Collapsed
            if (!collapsed) {
                miniaturaComic.animate().alpha(1.0f).setDuration(750).start();
                Log.e("error", "AAAAAAAAAAAAAAAAAAA COLLAPSED");
                collapsed=true;
            }
        }
        else
        {
            // Not collapsed
            if (collapsed) {
                miniaturaComic.animate().alpha(0.0f).setDuration(750).start();
                Log.e("error", "AAAAAAAAAAAAAAAAAAA COLLAPSED " + offset);
                collapsed=false;
            }
        }
    }
}
