package ngocthai.android.code.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<Fragment> list_fm;
    private ViewPagerAdapter adapter;

    /*
    Function run first
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /*
    Function work  some UI in this Activity
     */
    private void initView() {
        // find view by id
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        list_fm = getDataFragment();
        // set list fragment to adapter
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list_fm);
        // set adapter for viewpager
        viewPager.setAdapter(adapter);
        // update adapter
        adapter.notifyDataSetChanged();
        // click viewpager
        viewPager.setOnPageChangeListener(change_tabs);

    }

    /*
    Function main of click on ViewPager
     */
    private ViewPager.OnPageChangeListener change_tabs = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // select tab change
            Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /*
    Get list fragment to ArrayList
     */
    private ArrayList<Fragment> getDataFragment() {
        ArrayList<Fragment> ls = new ArrayList<>();
        ls.add(new FragmentBlue());
        ls.add(new FragmentRed());
        ls.add(new FragmentGreen());
        ls.add(new FragmentBlue());
        ls.add(new FragmentRed());
        ls.add(new FragmentGreen());
        return ls;
    }
}
