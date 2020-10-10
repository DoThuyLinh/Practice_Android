package com.example.myself.animationimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    ImageView imgAnimation;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        } );

        btnStop.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        } );

    }

    private void addControls() {
        btnStart = findViewById( R.id.btnStart );
        btnStop = findViewById( R.id.btnStop );
        imgAnimation = findViewById( R.id.imgAnimation );

        imgAnimation.setBackgroundResource( R.drawable.animationimage );
        animationDrawable = (AnimationDrawable) imgAnimation.getBackground();
    }
}
