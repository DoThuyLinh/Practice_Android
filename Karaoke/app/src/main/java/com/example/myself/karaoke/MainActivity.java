package com.example.myself.karaoke;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.myself.adapter.MusicAdapter;
import com.example.myself.model.Music;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    
    ListView lvAllSong;
    ArrayList<Music> listAllSong;
    MusicAdapter adapterAllSong;

    ListView lvFavoriteSong;
    ArrayList<Music> listFavoriteSong;
    MusicAdapter adapterFavoriteSong;

    public static String DATABASE_NAME = "dbSongList.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        processCopy();

        addControls();
        addEvents();
    }

    private void processCopy() {
        File dbFile = getDatabasePath( DATABASE_NAME );
        if(!dbFile.exists()){
            try {
                CopyDatabaseFromAsset();
                Toast.makeText( this,"Success!",Toast.LENGTH_SHORT ).show();
            }
            catch (Exception ex){
                Toast.makeText( this,ex.toString(),Toast.LENGTH_SHORT ).show();
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try {
            InputStream myInput = getAssets().open( DATABASE_NAME );
            String outFileName = getDatabasePathInMain();
            File f = new File( getApplicationInfo().dataDir + DB_PATH_SUFFIX );
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream( outFileName );
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))> 0){
                myOutput.write( buffer,0,length );
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception ex){
            Log.e( "Error copy",ex.toString() );
        }
    }

    private String getDatabasePathInMain() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener( new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase( "t1" )){
                    xuLyHienThiAllSong();
                }
                else if(tabId.equalsIgnoreCase( "t2" )){
                    xuLyHienThiFavoriteSong();
                }
            }
        } );
    }

    private void xuLyHienThiFavoriteSong() {
        /*listFavoriteSong.clear();
        for (Music music: listAllSong){
            if(music.getLike())
                listFavoriteSong.add( music );
        }
        adapterFavoriteSong.notifyDataSetChanged();*/

        database = openOrCreateDatabase( DATABASE_NAME,MODE_PRIVATE,null );
        Cursor cursor = database.query( "SongList",null, "likeSong=?",new String[]{"1"},
                null,null,null);
        listFavoriteSong.clear();
        while (cursor.moveToNext()){
            String idSong = cursor.getString( 0 );
            String nameSong = cursor.getString( 1 );
            String singer = cursor.getString( 3 );
            int likeSong = cursor.getInt( 5 );

            Music music = new Music(  );
            music.setId( idSong );
            music.setName( nameSong );
            music.setSinger( singer );
            music.setLike( likeSong ==1 );
            listFavoriteSong.add( music );
        }
        cursor.close();
        adapterFavoriteSong.notifyDataSetChanged();

    }

    private void xuLyHienThiAllSong() {
        database = openOrCreateDatabase( DATABASE_NAME,MODE_PRIVATE,null );
        Cursor cursor = database.rawQuery( "select * from SongList",null );
        listAllSong.clear();
        while (cursor.moveToNext()){
            String idSong = cursor.getString( 0 );
            String nameSong = cursor.getString( 1 );
            String singer = cursor.getString( 3 );
            int likeSong = cursor.getInt( 5 );

            Music music = new Music(  );
            music.setId( idSong );
            music.setName( nameSong );
            music.setSinger( singer );
            music.setLike( likeSong ==1 );
            listAllSong.add( music );
        }
        cursor.close();
        adapterAllSong.notifyDataSetChanged();
    }

    private void addControls() {

        tabHost = findViewById( R.id.tabHost );
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec( "t1" );
        tab1.setContent( R.id.tab1 );
        tab1.setIndicator( "",getResources().getDrawable( R.drawable.icons8_music_32 ) );
        tabHost.addTab( tab1 );

        TabHost.TabSpec tab2 = tabHost.newTabSpec( "t2" );
        tab2.setContent( R.id.tab2 );
        tab2.setIndicator( "",getResources().getDrawable( R.drawable.icons8_hearts_32 ) );
        tabHost.addTab( tab2 );

        lvAllSong = findViewById( R.id.lvAllSong );
        listAllSong = new ArrayList<Music>(  );
        adapterAllSong = new MusicAdapter( MainActivity.this, R.layout.item_song,listAllSong);
        lvAllSong.setAdapter( adapterAllSong );

        lvFavoriteSong = findViewById( R.id.lvFavoriteSong );
        listFavoriteSong = new ArrayList<Music>(  );
        adapterFavoriteSong = new MusicAdapter( MainActivity.this, R.layout.item_song,listFavoriteSong);
        lvFavoriteSong.setAdapter( adapterFavoriteSong );

        xuLyHienThiAllSong();

        //demo();
    }

    /*private void demo() {
        listAllSong.add( new Music( "1111","Anh doi em duoc khong","My Tam",true ) );
        listAllSong.add( new Music( "2222","Rieng mot goc troi","Tuan Ngoc",false ) );
        listAllSong.add( new Music( "3333","So em biet anh con yeu em","Juun Dang Dung",true ) );
        listAllSong.add( new Music( "4444","None of my business","Cher Lloyd",true ) );
        adapterAllSong.notifyDataSetChanged();
    }*/
}
