package com.example.myself.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myself.model.Contact;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    ListView lvContact;
    ArrayList<Contact> listContact;
    ArrayAdapter<Contact> adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contact );
        addControls();
        addEvents();
        
        showAllContactFromDevice();
    }

    private void showAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query( uri,null,null,null,null );
        listContact.clear();
        while (cursor.moveToNext()){
            String nameColName = ContactsContract.Contacts.DISPLAY_NAME;
            String nameColPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int indexName = cursor.getColumnIndex( nameColName );
            int indexPhone = cursor.getColumnIndex( nameColPhone );
            String name = cursor.getString( indexName );
            String phone = cursor.getString( indexPhone );
            Contact contact = new Contact( name,phone );
            listContact.add( contact );
        }
        adapterContact.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvContact = findViewById( R.id.lvContact);
        listContact = new ArrayList<Contact>(  );
        adapterContact = new ArrayAdapter<Contact>( ContactActivity.this,
                android.R.layout.simple_list_item_1,listContact);
        lvContact.setAdapter( adapterContact );
    }
}
