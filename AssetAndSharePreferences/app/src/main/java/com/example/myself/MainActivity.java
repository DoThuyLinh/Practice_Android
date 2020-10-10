package com.example.myself;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String DATABASE_NAME = "dbContact.sqlite";
    static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;

    ListView lvContact;
    ArrayList<String> listContact;
    ArrayAdapter<String> adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        processCopy();

        addControls();
        addEvents();

        showAllContactListView();
    }

    private void showAllContactListView() {
        database = openOrCreateDatabase( DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery( "select * from Contact",null );
        listContact.clear();
        while (cursor.moveToNext()){
            int ma = cursor.getInt( 0 );
            String ten = cursor.getString( 1 );
            String phone = cursor.getString( 2 );
            listContact.add( ma + "-" + ten + "-" + phone );
        }
        cursor.close();

        adapterContact.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvContact = findViewById( R.id.lvContact );
        listContact = new ArrayList<String>(  );
        adapterContact = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,listContact  );
        lvContact.setAdapter( adapterContact );
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
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
            String outFileName = getDatabasePath();
            File f = new File( getApplicationInfo().dataDir+ DB_PATH_SUFFIX );
            if(!f.exists()){
                f.mkdir();
            }

            OutputStream myOutput = new FileOutputStream( outFileName );
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0){
                myOutput.write( buffer,0,length );
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getDatabasePath(){
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }
}
