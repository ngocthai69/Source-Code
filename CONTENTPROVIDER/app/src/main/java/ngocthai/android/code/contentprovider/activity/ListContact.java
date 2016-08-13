package ngocthai.android.code.contentprovider.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ngocthai.android.code.contentprovider.R;
import ngocthai.android.code.contentprovider.adapter.ContactAdapter;
import ngocthai.android.code.contentprovider.custom.Contact;
import ngocthai.android.code.contentprovider.database.SQLiteSupport;

public class ListContact extends AppCompatActivity {

    private ArrayList<Contact> listData;
    private ListView lv;
    private ContactAdapter adapter;
    private static final String key_object_contact = "CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        initView();

    }

    //---create some UI in here---
    private void initView() {
        //--find view by id---
        lv = (ListView) findViewById(R.id.lvContact);

        SQLiteSupport ss = new SQLiteSupport(ListContact.this);
        listData = ss.getAllContact();

        Toast.makeText(ListContact.this, "" + listData.size(), Toast.LENGTH_SHORT).show();

        adapter = new ContactAdapter(listData, ListContact.this);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(click_to_show);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.mn_search_view);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(on_search);

        return true;
    }

    private SearchView.OnQueryTextListener on_search = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {

            ArrayList<Contact> ls = searchOnList(newText);
            adapter=new ContactAdapter(ls, ListContact.this);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            return false;
        }
    };

    /*
    Function search data by key change
     */
    private ArrayList searchOnList(String key) {
        ArrayList list_result = new ArrayList();
        for (int index = 0; index < listData.size(); index++) {
            Contact current_data = listData.get(index);
            String search_list = current_data.getName() + current_data.getNumber();

            if (search_list.toLowerCase().indexOf(key.toLowerCase()) > -1) {
                list_result.add(current_data);
            }
        }
        return list_result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAdd: {
                Intent i = new Intent(ListContact.this, AddContact.class);
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //---click of item in list view---
    private AdapterView.OnItemClickListener click_to_show = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Contact contact = adapter.getItem(position);
            Intent intent = new Intent(ListContact.this, EditContact.class);
            intent.putExtra(key_object_contact, contact);
            startActivityForResult(intent, 2);
        }
    };


}


