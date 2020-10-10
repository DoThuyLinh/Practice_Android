package com.example.myself.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    ListView lvMessage;
    ArrayList<String> listMessage;
    ArrayAdapter<String> adapterMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_message );
        addControls();
        addEvents();

        ReadAllMessageFromDevice();
    }

    private void ReadAllMessageFromDevice() {
        Uri uri = Uri.parse( "content://sms/inbox");
        Cursor cursor = getContentResolver().query( uri,null,null,null,null );
        listMessage.clear();
        while (cursor.moveToNext()){
            int indexPhoneNumber = cursor.getColumnIndex( "address" );
            int indexTimeStamp = cursor.getColumnIndex( "date" );
            int indexBody = cursor.getColumnIndex( "body" );

            String phoneNumber = cursor.getString( indexPhoneNumber );
            String timeStamp = cursor.getString( indexTimeStamp );
            String body = cursor.getString( indexBody );

            listMessage.add( phoneNumber +"\n" + timeStamp +"\n" + body);

        }
        cursor.close();
        adapterMessage.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvMessage = findViewById( R.id.lvMessage );
        listMessage = new ArrayList<String>(  );
        adapterMessage = new ArrayAdapter<String>( MessageActivity.this,
                android.R.layout.simple_list_item_1,listMessage);
        lvMessage.setAdapter( adapterMessage );
    }
}
