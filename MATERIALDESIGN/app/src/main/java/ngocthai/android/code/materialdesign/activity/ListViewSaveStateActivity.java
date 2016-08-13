package ngocthai.android.code.materialdesign.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ngocthai.android.code.materialdesign.R;
import ngocthai.android.code.materialdesign.adapter.AdapterListView;
import ngocthai.android.code.materialdesign.custom.ContactListView;

public class ListViewSaveStateActivity extends AppCompatActivity {

    private AdapterListView adapter;
    private ArrayList<ContactListView> listData;
    private ListView lv;
    private ActionBar ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_save_state);

        ac = getSupportActionBar();
        ac.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c62828")));
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setTitle("List View Demo");

        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(ListViewSaveStateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        // find view by id
        lv = (ListView) findViewById(R.id.lvMain);

        listData = CreateListData();

        adapter = new AdapterListView(listData, ListViewSaveStateActivity.this);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListView contact = adapter.getListData().get(position);
                Toast.makeText(ListViewSaveStateActivity.this, "" + contact.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*
  Create data for list contact
   */
    private ArrayList<ContactListView> CreateListData() {
        ArrayList<ContactListView> ls = new ArrayList<>();
        for (int i = 10; i < 30; i++) {
            ls.add(new ContactListView(i, "Hoang Ngoc Thai " + i, "Header " + i, "I'll be in your neighborhood doing extras " + i, 15 + i, false));
        }
        return ls;
    }
}
