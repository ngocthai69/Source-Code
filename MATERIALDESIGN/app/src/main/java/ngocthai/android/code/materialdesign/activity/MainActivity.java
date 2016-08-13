package ngocthai.android.code.materialdesign.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ngocthai.android.code.materialdesign.R;
import ngocthai.android.code.materialdesign.adapter.MainAdapter;
import ngocthai.android.code.materialdesign.fragment.FragmentTextInputLayout;
import ngocthai.android.code.materialdesign.fragment.FragmentGridRecycle;
import ngocthai.android.code.materialdesign.fragment.FragmentRecycleView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private MainAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;
    private String[] color = {"#c62828", "#2E7D32", "#283593"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initFloatingButton();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // dung icon co mau cua minh add vao
        navigationView.setItemIconTintList(null);

        initView();
    }

    /*
    Floating button support in here
     */
    private void initFloatingButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customSnackBar(view);
            }
        });

    }


    /*
    Snack bar khong chua duoc layout tu tao ma no chi thay the cho toast de thong bao
    No khong chua gi hon ngoai mot hanh dong va tin nhan
    Nhung cung co cach an text di de goi vao do 1 layout do minh tu custom
    Custom view of snackbar
     */
    private void customSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);

        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // background
        layout.setBackgroundColor(Color.WHITE);

        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        Button btn = (Button) layout.findViewById(android.support.design.R.id.snackbar_action);
        textView.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.GONE);

        // Inflate our custom view
        LayoutInflater inflater = getLayoutInflater();
        View snackView = inflater.inflate(R.layout.layout_snack_bar, null);

        Button btnred, btngreen, btnblue, btn_snackbar;
        btnred = (Button) snackView.findViewById(R.id.btnRed);
        btngreen = (Button) snackView.findViewById(R.id.btnGreen);
        btnblue = (Button) snackView.findViewById(R.id.btnBlue);
        btn_snackbar = (Button) snackView.findViewById(R.id.snack_bar_default);

        btnred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Red", Toast.LENGTH_SHORT).show();
            }
        });
        btngreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Green", Toast.LENGTH_SHORT).show();
            }
        });
        btnblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CLick Blue", Toast.LENGTH_SHORT).show();
            }
        });
        btn_snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a simple snackbar
                Snackbar sb = Snackbar.make(v, "This is snackbar default !", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Got it", Toast.LENGTH_SHORT).show();
                    }
                });
                sb.show();
            }
        });

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
        // Show the Snackbar
        snackbar.show();

    }

    private void initView() {
        // find view by id
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        tabLayout.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[1])));

        setupViewpager(viewPager);
        viewPager.setCurrentItem(1); // tabs default is center
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOnPageChangeListener(pager_change);

    }

    /*
    TODO : BAT SU KIEN CHO VIEWPAGER SELECTED TABS
     */
    private ViewPager.OnPageChangeListener pager_change = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 1) {
                tabLayout.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[1])));
                toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[1])));
                fab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[1])));
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
            } else if (position == 0) {
                tabLayout.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[0])));
                toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[0])));
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
                fab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[0])));
            } else {
                tabLayout.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[2])));
                toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[2])));
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
                fab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[2])));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /*
    Setup fragment and title to view pager
     */
    private void setupViewpager(ViewPager vp) {
        adapter = new MainAdapter(getSupportFragmentManager());
        // create fragment
        FragmentRecycleView red = new FragmentRecycleView();
        FragmentTextInputLayout blue = new FragmentTextInputLayout();
        FragmentGridRecycle green = new FragmentGridRecycle();

        String[] tabs_name = {getString(R.string.tabs_name_green),
                getString(R.string.tabs_name_recycle_view), getString(R.string.tabs_name_textinput)};
        adapter.addTabs(green, tabs_name[0]);
        adapter.addTabs(red, tabs_name[1]);
        adapter.addTabs(blue, tabs_name[2]);
        // set adapter for viewpager
        vp.setAdapter(adapter);
    }

    // Support toolbar
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color[1])));
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list_view_save_state) {
            // go to listview save state activity
            Intent intent = new Intent(MainActivity.this, ListViewSaveStateActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_collapsing_layout) {
            Intent intent = new Intent(MainActivity.this, CollapsingToolbars.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
