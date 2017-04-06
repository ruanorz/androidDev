package com.ruanorz.prueba;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private RelativeLayout background_purple;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las dimensiones de la pantalla para
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        //Handler para generar las estrellas asincronamente
        final Handler mHandlerStar = new Handler();
        final Runnable runnableStar =new Runnable() {

            public void run() {
                //Generamos la estrella
                generateLeftStar();
                mHandlerStar.removeCallbacks(this);
                //Se volverá a generar una estrella cada X milisegundos con el parametro
                mHandlerStar.postDelayed(this, 150);
            }
        };
        runnableStar.run();

        //Handler para generar los asteroides asincronamente
        final Handler mHandlerAsteroid = new Handler();
        final Runnable runnableAsteroid =new Runnable() {

            public void run() {
                //Generamos la estrella
                generateMeteor();
                mHandlerAsteroid.removeCallbacks(this);
                //Se volverá a generar un asteroide cada X milisegundos con el parametro
                mHandlerAsteroid.postDelayed(this, 4000);
            }
        };
        runnableAsteroid.run();

        //Animacion rotacion asteroide
        ImageView asteroid = (ImageView)findViewById(R.id.ball);
        RotateAnimation rotate = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF,
                0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(6000);
        rotate.setRepeatCount(-1);
        rotate.setInterpolator(new LinearInterpolator());
        asteroid.startAnimation(rotate);

        //Cambio fuente logo
        TextView textLogo = (TextView) findViewById(R.id.tv_logo);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/ochobits.ttf");
        textLogo.setTypeface(tf);
        textLogo.setText(getString(R.string.nameLogo));




        /*
        //Shared view animation
        final LinearLayout sharedImage = (LinearLayout) findViewById(R.id.sharedimage);
        sharedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This is where the magic happens.
                // makeSceneTransitionAnimation takes a context, view,
                // a name for the target view.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.e(TAG, "LOLLIPOP or more");
                    ActivityOptions options =
                            ActivityOptions.
                                    makeSceneTransitionAnimation(MainActivity.this, sharedImage, "sharedImage");
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    mHandler.removeCallbacks(runnable);
                    startActivity(intent, options.toBundle());

                }else{
                    Log.e(TAG, "Before LOLLIPOP");
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            }
        });*/

        //Animacion cohete
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.e(TAG, "Animation Start");
            Path path = new Path();


            //path.rQuadTo(100,900,350,450);
            //path.rQuadTo(-250,-450,250,350);
            path.rMoveTo(0,446);
            path.rCubicTo(346,-46,400,-158,186,-188); path.rCubicTo(126,-74,348,175,106,-330);


            ValueAnimator pathAnimator = ObjectAnimator.ofFloat(sharedImage, "x", "y", path);

            pathAnimator.setDuration(3000);
            //pathAnimator.start();
        }*/

    }


    /* **********************************************

    Funcion para generar estrellas por la izquierda.

    ************************************************ */

    public void generateLeftStar(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Inflamos el layout para poner la estrella
            background_purple = (RelativeLayout) findViewById(R.id.background_purple);
            final View view = getLayoutInflater().inflate(R.layout.single_star, background_purple,false);

            //Ubicamos la estrella en el lugar donde nos interesa (Pegado a la izquierda y la altura es aleatoria) y su tamaño aleatorio
            Random r = new Random();
            int numRandom = r.nextInt((height-50) - 0);
            int numRandomSize = r.nextInt(100-40)+40;

            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(numRandomSize, numRandomSize);
            params2.leftMargin = 0;
            params2.topMargin = numRandom;

            //Se instancia la estrella
            background_purple.addView(view, 1,params2);

            //Creamos una primera animacion para decirle que se salga por la izquierda 40. y la duracion 0 es para que lo haga instantaneamente
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "x", -40);
            animator1.setDuration(0);

            //Hacemos que la estrella se desplaze a lo largo de la anchura de la pantalla
            ObjectAnimator moveX = ObjectAnimator.ofFloat(view, "x", width);
            moveX.setDuration(3000);

            //Concatenamos las dos animaciones y la ejecutamos.
            AnimatorSet set = new AnimatorSet();
            set.setInterpolator(new LinearInterpolator());
            set.play(animator1).before(moveX);
            set.start();

            //Por ultimo eliminamos el view de la estrella cuando acaba la animacion para no sobrecargar la app
            moveX.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                    // ...
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    // ...
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    // ...
                }
            });

        }

    }

    public void generateMeteor(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Inflamos el layout para poner la estrella
            background_purple = (RelativeLayout) findViewById(R.id.background_purple);
            final View view = getLayoutInflater().inflate(R.layout.single_meteor, background_purple,false);

            //Ubicamos la estrella en el lugar donde nos interesa (Pegado a la izquierda y la altura es aleatoria) y su tamaño aleatorio
            Random r = new Random();
            int numRandomX = r.nextInt(width);
            int numRandomY = r.nextInt(height);
            int numRandomAltoOrAncho = r.nextInt(2);


            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(40, 40);
            params2.leftMargin = numRandomX;
            params2.topMargin = numRandomY;

            //Se instancia la estrella
            background_purple.addView(view, 1,params2);

            //Si numRandomAltoOrAncho es igual a 0, se genera un asteroide por el lateral derecho de la pantalla
            //Si es 1, se genera por el lateral superior el asteroide.
            if(numRandomAltoOrAncho==0) {
                //Hacemos que la estrella se desplaze a lo largo de la anchura de la pantalla
                ObjectAnimator moveX = ObjectAnimator.ofFloat(view, "x", width, -40);
                moveX.setDuration(10000);
                moveX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        view.setY(view.getY() + 1);
                    }
                });

                //Concatenamos las dos animaciones y la ejecutamos.
                AnimatorSet set = new AnimatorSet();
                set.setInterpolator(new LinearInterpolator());
                set.play(moveX);
                set.start();

                //Por ultimo eliminamos el view de la estrella cuando acaba la animacion para no sobrecargar la app
                moveX.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        // ...
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        // ...
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ((ViewGroup) view.getParent()).removeView(view);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        // ...
                    }
                });
            }else{
                //Hacemos que la estrella se desplaze a lo largo de la anchura de la pantalla
                ObjectAnimator moveX = ObjectAnimator.ofFloat(view, "y", -70, height);
                moveX.setDuration(10000);
                moveX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        view.setX(view.getX() - 2);
                    }
                });

                //Concatenamos las dos animaciones y la ejecutamos.
                AnimatorSet set = new AnimatorSet();
                set.setInterpolator(new LinearInterpolator());
                set.play(moveX);
                set.start();

                //Por ultimo eliminamos el view de la estrella cuando acaba la animacion para no sobrecargar la app
                moveX.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        // ...
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        // ...
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ((ViewGroup) view.getParent()).removeView(view);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        // ...
                    }
                });
            }





        }

    }
}
