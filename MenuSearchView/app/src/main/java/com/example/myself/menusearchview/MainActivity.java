package com.example.myself.menusearchview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    ListView lvTinhThanh;
    ArrayList<String> listTinhThanh;
    ArrayAdapter<String> adapterTinhThanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        addControls();
        addEvents();

    }

    private void addEvents() {
    }

    private void addControls() {
        lvTinhThanh = findViewById( R.id.lvTinhThanh );
        listTinhThanh = new ArrayList<String>(  );
        listTinhThanh.addAll( Arrays.asList( getResources().getStringArray( R.array.arrTinhThanh ) ) );
        adapterTinhThanh = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,listTinhThanh  );
        lvTinhThanh.setAdapter( adapterTinhThanh );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu,menu );
        MenuItem mnuSearch = menu.findItem( R.id.mnuSearch );
        SearchView searchView = (SearchView) mnuSearch.getActionView();
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterTinhThanh.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTinhThanh.getFilter().filter( newText );
                return false;
            }
        } );

        return super.onCreateOptionsMenu( menu );
    }
}
