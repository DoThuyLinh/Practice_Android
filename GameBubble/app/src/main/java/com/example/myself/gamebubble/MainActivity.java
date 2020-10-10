package com.example.myself.gamebubble;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    Random rd;
    TextView txtScore;
    ViewGroup.LayoutParams layoutParams;
    LinearLayout layoutBubble;
    Button btnCreateBubble;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        addControls();
        addEvents();
    }
    private void addEvents() {
        btnCreateBubble.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i =0; i <= rd.nextInt(5);i++){
                    ProcessAnimation();
                }
            }
        } );
        
    }

    private void addControls() {
        txtScore = findViewById( R.id.txtScore );
        rd = new Random(  );
        layoutBubble = findViewById( R.id.layoutBubble );
        layoutParams = new ViewGroup.LayoutParams(
          LinearLayout.LayoutParams.WRAP_CONTENT,
          LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnCreateBubble = findViewById( R.id.btnCreateBubble );
    }

    private void ProcessAnimation() {
        ImageView img = getImageView();
        img.setBackground( getDrawable() );
        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBubble.removeView( v );
                txtScore.setText( "Score : " + (score+1) );
            }
        } );

        objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(
                MainActivity.this,
                R.animator.bubbleanimation);
        objectAnimator.setDuration( rd.nextInt(1000) + 2000 );
        objectAnimator.setTarget( img );

        layoutBubble.addView( img,layoutParams );

        objectAnimator.addListener( new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layoutBubble.removeView( (View)((ObjectAnimator)animation).getTarget() );
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        } );
    }

    private ImageView getImageView() {
        ImageView img = new ImageView( MainActivity.this);
        img.setX( rd.nextInt(500) );
        return img;
    }

    private Drawable getDrawable() {
        Drawable drawable;
        int i =0; //rd.nextInt(5);
        switch (i){
            case 0:
                drawable = getResources().getDrawable( R.drawable.bb1 );
                break;
//            case 1:
//                drawable = getResources().getDrawable( R.drawable.bb2 );
//                break;
//            case 2:
//                drawable = getResources().getDrawable( R.drawable.bb3 );
//                break;
//            case 3:
//                drawable = getResources().getDrawable( R.drawable.bb4 );
//                break;
//            case 4:
//                drawable = getResources().getDrawable( R.drawable.bb5 );
//                break;
                default:drawable = getResources().getDrawable( R.drawable.bb6);
        }
        return drawable;
    }


}
