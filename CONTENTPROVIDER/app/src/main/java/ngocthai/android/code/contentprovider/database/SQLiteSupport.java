package ngocthai.android.code.contentprovider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import ngocthai.android.code.contentprovider.custom.Contact;

/**
 * Created by NgocThai on 10/08/2016.
 */
public class SQLiteSupport extends SQLiteDataController {

    private static String table_row_id = "ID";
    private static String table_row_name = "NAME";
    private static String table_row_phone = "PHONE";
    private static String table_name = "CONTACT";


    //---constructor default---
    public SQLiteSupport(Context con) {
        super(con);
    }

    //---get all contact from database---
    public ArrayList<Contact> getAllContact() {
        ArrayList<Contact> ls = new ArrayList<>();

        try {
            openDataBase();

            Cursor cursor = database.rawQuery("SELECT * FROM " + table_name, null);

            while (cursor.moveToNext()) {
                ls.add(new Contact("" + cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return ls;
    }

    //---insert contact---
    public boolean insertDatabase(Contact contact) {
        boolean result = false;

        try {
            openDataBase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(table_row_id, Integer.valueOf(contact.getId()));
            contentValues.put(table_row_name, contact.getName());
            contentValues.put(table_row_phone, contact.getNumber());

            long kq = database.insert(table_name, null, contentValues);

            if (kq > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

    //---update contact---
    public boolean updateContact(Contact contact) {
        boolean update = false;

        try {
            openDataBase();

            //---put data by content values---
            ContentValues contentValues = new ContentValues();

            contentValues.put(table_row_id, Integer.valueOf(contact.getId()));
            contentValues.put(table_row_name, contact.getName());
            contentValues.put(table_row_phone, contact.getNumber());

            long kq = database.update(table_name, contentValues, "ID = " + contact.getId(), null);

            if (kq > 0) {
                update = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return update;
    }

    //---delete contact by contact id---
    public boolean deleteContact(int contact_id) {
        boolean delete = false;

        try {
            openDataBase();

            int kq = database.delete(table_name, "ID = " + contact_id, null);

            if (kq > 0) {
                delete = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return delete;
    }
}
