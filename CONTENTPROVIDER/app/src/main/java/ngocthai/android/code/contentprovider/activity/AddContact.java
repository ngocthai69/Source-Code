package ngocthai.android.code.contentprovider.activity;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ngocthai.android.code.contentprovider.R;
import ngocthai.android.code.contentprovider.adapter.ContactAdapter;
import ngocthai.android.code.contentprovider.custom.Contact;
import ngocthai.android.code.contentprovider.database.SQLiteSupport;

public class AddContact extends AppCompatActivity {

    private Button btnAdd;
    private ActionBar ac;
    private EditText edtName, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        ac = getSupportActionBar();
        ac.setDisplayHomeAsUpEnabled(true);

        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(AddContact.this, ListContact.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }

        return true;
    }

    private void initView() {
        //--find view by id---
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtName = (EditText) findViewById(R.id.edtADD_NAME);
        edtPhone = (EditText) findViewById(R.id.edtADD_PHONE);

        //---click on button add---
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---add contact in here---
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                //---check number---
                long exist = getContactID(getContentResolver(), phone);

                if (name.equalsIgnoreCase("") && phone.equalsIgnoreCase("")) {
                    //---input is null. not work
                    Snackbar snackbar = Snackbar.make(v, "Data is empty", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    if (exist == -1) {
                        // no exist number -> insert to database and contact---
                        Contact contact = new Contact(0 + "", R.drawable.add_contact, name, phone);
                        SQLiteSupport ss = new SQLiteSupport(AddContact.this);
                        boolean result = ss.insertDatabase(contact);
                        //---insert to contact system android---
                        insertContact(getContentResolver(), name, phone);
                        //---notification success---
                        if (result) {
                            Toast.makeText(AddContact.this, "Insert Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddContact.this, ListContact.class);
                            finish();
                            startActivity(intent);
                        } else {
                            Snackbar snackbar = Snackbar.make(v, "Something wrong", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    } else {
                        //---contact is exist. do nothing---
                        Snackbar snackbar = Snackbar.make(v, "Account Exist. Please check your number", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }
            }
        });
    }

    //---insert contact to system android---
    public static boolean insertContact(ContentResolver contactAdder, String firstName, String mobileNumber) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, firstName).build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
                mobileNumber).withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
        try {
            contactAdder.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            return false;
        }
        return true;
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
