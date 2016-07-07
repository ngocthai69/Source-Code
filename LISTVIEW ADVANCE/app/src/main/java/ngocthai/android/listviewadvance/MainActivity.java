package ngocthai.android.listviewadvance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private ArrayList<Enity> ls_data;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        // find view by id
        lv = (ListView) findViewById(R.id.lv_main);

        ls_data = getData();
        adapter = new MyAdapter(ls_data, MainActivity.this); // create adapter
        lv.setAdapter(adapter); // set adapter
        adapter.notifyDataSetChanged(); // update adapter

        // click
        lv.setOnItemClickListener(click_lv);
    }

    /*
    Main of function click
     */
    private AdapterView.OnItemClickListener click_lv = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            
            Toast.makeText(MainActivity.this, "" + adapter.getLs_data().get(position).getId(), Toast.LENGTH_SHORT).show();
        }
    };

    /*
    get list data
     */
    private ArrayList<Enity> getData() {
        ArrayList<Enity> ls = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            ls.add(new Enity(i, R.mipmap.ic_launcher, "Thai " + i, "" + i + 20, "0169995012" + i));
        }
        return ls;
    }
}
