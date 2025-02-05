package com.example.myself.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void btnReadContact(View view) {
        startActivity( new Intent( MainActivity.this, ContactActivity.class ) );
    }

    public void btnReadMessage(View view) {
        startActivity( new Intent( MainActivity.this,MessageActivity.class) );
    }
}
