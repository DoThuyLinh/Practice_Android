package com.example.myself.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnXoayControl, btnXoayManHinh, btnXoay3s, btnHieuUngListview;
    Animation animation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        addControls();
        addEvents();
        
    }

    private void addEvents() {
        btnXoayControl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation( MainActivity.this,R.anim.xoaycontrol );
                btnXoayControl.startAnimation( animation );
            }
        } );

        btnXoayManHinh.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutManHinh = findViewById( R.id.layoutManHinh );
                animation = AnimationUtils.loadAnimation( MainActivity.this,R.anim.xoaymanhinh );
                layoutManHinh.startAnimation( animation );
            }
        } );

        btnXoay3s.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation( MainActivity.this,R.anim.xoaynguoc3s );
                btnXoay3s.startAnimation( animation );

                animation.setAnimationListener( new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                } );
            }
        } );

        btnHieuUngListview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this,
                        Main2Activity.class) );
            }
        } );

    }

    private void addControls() {
        btnXoayControl = findViewById( R.id.btnXoayControl );
        btnXoayManHinh = findViewById( R.id.btnXoayManHinh );
        btnXoay3s = findViewById( R.id.btnXoay3s );
        btnHieuUngListview = findViewById( R.id.btnHieuUngListView );



    }
}
