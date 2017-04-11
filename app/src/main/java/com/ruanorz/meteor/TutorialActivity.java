package com.ruanorz.meteor;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruanorz.meteor.fragments.TutorialFragmentPageAdapter;
import com.ruanorz.meteor.fragments.TutorialSlidePageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ruano on 10/04/2017.
 */

public class TutorialActivity extends AppCompatActivity {

    private static final String TAG = TutorialActivity.class.getName();

    private View parentLayout;

    ViewPager pager = null;
    TutorialFragmentPageAdapter pagerAdapter;

    @BindView(R.id.tv_logo_tutorial)
    TextView tv_logo_tutorial;

    @BindView(R.id.button_start)
    TextView button_start;

    @BindView(R.id.viewPager_tutorial)
    ViewPager viewPager_tutorial;

    @BindView(R.id.pager_countTotal)
    LinearLayout pager_countTotal;

    @BindView(R.id.layout_button_start)
    LinearLayout layout_button_start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);

        //Cambio fuente logo
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/ochobits.ttf");
        tv_logo_tutorial.setTypeface(tf);
        button_start.setTypeface(tf);


        // Instantiate a ViewPager
        this.pager = (ViewPager) this.findViewById(R.id.viewPager_tutorial);

        final String[] stringsTutorial = getResources().getStringArray(R.array.tutorial_text);
        // Create an adapter with the fragments we show on the ViewPager
        TutorialFragmentPageAdapter adapter = new TutorialFragmentPageAdapter(
                getSupportFragmentManager(), stringsTutorial);

        //Efecto de carrousel al viewpager
        pager.setPageMargin((int) (getResources().getDisplayMetrics().widthPixels * -0.33));
        pager.setOffscreenPageLimit(5);
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override public void transformPage(View page, float position) {
                page.setScaleX(0.7f - Math.abs(position * 0.2f));
                page.setScaleY(0.85f - Math.abs(position * 0.5f));
                page.setAlpha(1.0f - Math.abs(position * 0.5f));
            }
        });
        this.pager.setAdapter(adapter);


        //Ahora vamos a crear los dots de la pagina actual.
        //Creamos la barra en la que se dispondrán todos los puntos
        pager_countTotal.setWeightSum(stringsTutorial.length);


        for(int i=0;i<stringsTutorial.length;i++){
            View inflatedLayout= getLayoutInflater().inflate(R.layout.single_viewpager_dot, pager_countTotal, false);
            inflatedLayout.setId(i);
            if(i==0){
                inflatedLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
            pager_countTotal.addView(inflatedLayout);

        }

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i=0;i<stringsTutorial.length;i++){
                    Log.e(TAG, ""+i+" - "+position);
                    LinearLayout viewPageSelected = (LinearLayout) findViewById(i);
                    viewPageSelected.setBackgroundColor(Color.parseColor("#ff0000"));
                    if(i==position){
                        Log.e(TAG, "BLANCO");
                        viewPageSelected.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }else{
                        Log.e(TAG, "NEGRO");
                        viewPageSelected.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.whiteTranslucent));
                    }

                }

                if(position == stringsTutorial.length-1){
                    button_start.setText(getString(R.string.continue_button));
                    //Animacion blink por xml
                    Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    button_start.startAnimation(startAnimation);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick(R.id.layout_button_start)
    public void button_start_pressed(View view) {
        GoToLogin();
    }

    private void GoToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



}
