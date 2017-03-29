package com.ruanorz.prueba;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView sharedImage = (ImageView) findViewById(R.id.sharedimage);
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
                    startActivity(intent, options.toBundle());
                }else{
                    Log.e(TAG, "Before LOLLIPOP");
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
