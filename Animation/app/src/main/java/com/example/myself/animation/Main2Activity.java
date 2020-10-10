package com.example.myself.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myself.adapter.MyAdapter;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView lvAnimation;
    ArrayList<String> listData;
    MyAdapter adapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );

        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvAnimation = findViewById( R.id.lvAnimation );
        listData = new ArrayList<String>(  );
        for (int i=0; i<5000;i++){
            listData.add( "Đây là dòng "+ i );
        }
        adapterData = new MyAdapter( Main2Activity.this,android.R.layout.simple_list_item_1,listData );
        lvAnimation.setAdapter( adapterData );


    }
}
