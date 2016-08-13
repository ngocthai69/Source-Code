package ngocthai.android.code.contentprovider.activity;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ngocthai.android.code.contentprovider.R;
import ngocthai.android.code.contentprovider.custom.Contact;
import ngocthai.android.code.contentprovider.database.SQLiteSupport;

public class EditContact extends AppCompatActivity {

    private Button btnSave, btnDel;
    private EditText edtName, edtPhone;
    private Contact contact;
    private ActionBar ac;
    private static final String key_object_contact = "CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        ac = getSupportActionBar();
        ac.setDisplayHomeAsUpEnabled(true);

        getData();

        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(EditContact.this, ListContact.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }

        return true;
    }

    private void initView() {
        //---find view by id---
        edtName = (EditText) findViewById(R.id.edtNAME);
        edtPhone = (EditText) findViewById(R.id.edtPHONE);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDelete);

        //---set text for edit---
        edtName.setText(contact.getName());
        edtPhone.setText(contact.getNumber());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Integer.valueOf(contact.getId()));
                Intent it = new Intent(Intent.ACTION_EDIT);
                it.setData(contactUri);
                finish();
                startActivityForResult(it, 1994);

            }
        });

        //---click to delete contact on system android---
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(getContentResolver(), contact.getNumber());
                SQLiteSupport ss = new SQLiteSupport(EditContact.this);
                boolean delete = ss.deleteContact(Integer.valueOf(contact.getId()));
                delete = false;
                Intent intent = new Intent(EditContact.this, ListContact.class);
                finish();
                startActivity(intent);
            }
        });

    }

    //---get object contact from list contact---
    private void getData() {
        contact = (Contact) getIntent().getSerializableExtra(key_object_contact);
    }

    public static void deleteContact(ContentResolver contactHelper, String number) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        String[] args = new String[]{String.valueOf(getContactID(contactHelper, number))};
        ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).withSelection(ContactsContract.RawContacts.CONTACT_ID + "=?", args).build());
        try {
            contactHelper.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    //---search contact by id. return -1 if exist contact on system android---
    private static long getContactID(ContentResolver contactHelper, String number) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String[] projection = {ContactsContract.PhoneLookup._ID};
        Cursor cursor = null;
        try {
            cursor = contactHelper.query(contactUri, projection, null, null, null);
            if (cursor.moveToFirst()) {
                int personID = cursor.getColumnIndex(ContactsContract.PhoneLookup._ID);
                return cursor.getLong(personID);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return -1;
    }

}
