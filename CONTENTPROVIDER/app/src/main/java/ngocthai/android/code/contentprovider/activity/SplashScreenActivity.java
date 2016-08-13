package ngocthai.android.code.contentprovider.activity;

import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import ngocthai.android.code.contentprovider.R;
import ngocthai.android.code.contentprovider.custom.Contact;
import ngocthai.android.code.contentprovider.database.SQLiteSupport;

public class SplashScreenActivity extends AppCompatActivity {

    private Cursor cursor;
    private ArrayList<Contact> ls_database;
    private ArrayList<Contact> ls_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initDatabase();
        selectCursor();
        initView();
    }

    private void initView() {

        getData();

        checkData(ls_database, ls_contact);

        Intent intent = new Intent(SplashScreenActivity.this, ListContact.class);
        startActivity(intent);
        this.finish();

    }

    //---get data for use---
    private void getData() {
        //---get all data from database
        ls_contact = getContact(cursor);
        SQLiteSupport ss = new SQLiteSupport(SplashScreenActivity.this);
        ls_database = ss.getAllContact();
        //---get all data from contact
    }

    private void checkData(ArrayList<Contact> database, ArrayList<Contact> contact) {
        SQLiteSupport ss = new SQLiteSupport(SplashScreenActivity.this);
        if (database.size() == 0) {
            //---insert contact to database---
            for (int i = 0; i < contact.size(); i++) {
                boolean result = ss.insertDatabase(contact.get(i));
                result = false;
            }
        } else if (database.size() < contact.size()) {
            //---edit all data and insert missing---
            for (int i = 0; i < database.size(); i++) {
                boolean edit = ss.updateContact(contact.get(i));
                edit = false;
            }

            for (int i = database.size(); i < contact.size(); i++) {
                boolean result = ss.insertDatabase(contact.get(i));
                result = false;
            }
        } else if (database.size() == contact.size()) {
            //---edit all to update new contact
            for (int i = 0; i < database.size(); i++) {
                boolean edit = ss.updateContact(contact.get(i));
                edit = false;
            }
        } else if (database.size() > contact.size()) {
            for (int i = 0; i < contact.size(); i++) {
                boolean edit = ss.updateContact(contact.get(i));
                edit = false;
            }
            for (int i = contact.size(); i < database.size(); i++) {
                boolean delete = ss.deleteContact(Integer.valueOf(database.get(i).getId()));
            }
        }
    }

    //---copy database from assets folder to memory android---
    private void initDatabase() {
        try {
            SQLiteSupport ss = new SQLiteSupport(SplashScreenActivity.this);
            ss.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---create select data by cursor---
    private void selectCursor() {
        Uri allContact = ContactsContract.Contacts.CONTENT_URI;

        /* >>The second parameter of the managedQuery() method (third parameter for the CursorLoader class)
            controls how many columns are returned by the query; this parameter is known as the projection
           >>The third parameter of the managedQuery() method (fourth parameter for the CursorLoader class)
           enable you to specify a SQL WHERE clause to filter the result of the query
           >>The fourth parameter of the managedQuery() method (the fifth parameter for the CursorLoader class)
           enables you to specify a SQL ORDER BY clause to sort the result of the query, either in ascending or descending order
        * */
        String[] projection = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER};


        if (Build.VERSION.SDK_INT < 11) {
            cursor = managedQuery(allContact, projection, null, null, null);
        } else {

            CursorLoader cursorLoader = new CursorLoader(this, allContact, projection, null, null, null);
            cursor = cursorLoader.loadInBackground();
        }
    }

    //---get contacts from user and return is list object contact---
    private ArrayList<Contact> getContact(Cursor c) {
        ArrayList<Contact> ls = new ArrayList<>();

        ContentResolver cr = getContentResolver();
        //---display the contact id and name and phone number----
        if (c.moveToFirst()) {
            do {
                //---get the contact id and name----
                String contactID = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                String contactDisplayName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String contactDisplayPhone = "";
                //---get phone number by getColumnIndex will be return value zero-base and -1 if don't have numColumn---
                int hasPhone = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (hasPhone == 1) {
                    //---get phone number by command Cursor---
                    Cursor phoneCursor = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +
                                    contactID, null, null);
                    while (phoneCursor.moveToNext()) {
                        //---get phone number in here---
                        contactDisplayPhone = phoneCursor.getString(
                                phoneCursor.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phoneCursor.close();

                }
                ls.add(new Contact(contactID, R.drawable.add_contact, contactDisplayName, contactDisplayPhone));

            } while (c.moveToNext());
        }

        return ls;
    }
}
