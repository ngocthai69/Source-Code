package code.android.ngocthai.searchview.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import code.android.ngocthai.searchview.R;
import code.android.ngocthai.searchview.adapter.ContactAdapter;
import code.android.ngocthai.searchview.enity.ContactEnity;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main;
    private ContactAdapter adapter;
    private ArrayList<ContactEnity> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set background cua action bar
        getSupportActionBar().setBackgroundDrawable
                (new ColorDrawable(Color.parseColor("#1E9E07")));

        initView();
    }

    /*
    Function work some UI
     */
    private void initView() {
        // find view by id
        lv_main = (ListView) findViewById(R.id.lv);
        ls = getData();
        adapter = new ContactAdapter(ls, this);
        lv_main.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // click of list view
        lv_main.setOnItemClickListener(click);
    }

    /*
    Function main of list view click
     */
    private AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "" + adapter.getLs_data().get(position).getAge(), Toast.LENGTH_SHORT).show();
        }
    };

    /*
    Get data
     */
    private ArrayList<ContactEnity> getData() {
        ArrayList<ContactEnity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new ContactEnity(i, R.mipmap.ic_launcher, "Ngọc Thái", "0169995012" + i, "" + i + 20));
        }
        return list;
    }

    /*
    Create search view in this activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.mnSearch);
        SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(searchItem);

        searchView.setOnQueryTextListener(OnQuerySearch);

        return super.onCreateOptionsMenu(menu);
    }

    /*
    Change of text when you search
     */
    SearchView.OnQueryTextListener OnQuerySearch = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // code search in here
            // when have change of text. Set adapter again searchOnList Key
            adapter.setLs_data(searchOnList(newText));
            // set adapter for list view again
            adapter.notifyDataSetChanged();
            return false;
        }
    };

    /*
    Algorithm to search view
     */
    private ArrayList searchOnList(String key) {
        ArrayList result = new ArrayList();
        for (int i = 0; i < ls.size(); i++) {
            ContactEnity currentContact = (ContactEnity) ls.get(i);
            String searchList = currentContact.getName() + currentContact.getAge() + +currentContact.getId();

            if (searchList.toLowerCase().indexOf(key.toLowerCase()) > -1) {
                result.add(currentContact);
            }
        }

        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
