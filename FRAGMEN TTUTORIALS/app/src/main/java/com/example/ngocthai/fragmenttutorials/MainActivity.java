package com.example.ngocthai.fragmenttutorials;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt_red, txt_blue, txt_green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /*
    Function work some UI in this activity
     */
    private void initView() {
        // find view by id
        txt_red = (TextView) findViewById(R.id.txt_red);
        txt_blue = (TextView) findViewById(R.id.txt_blue);
        txt_green = (TextView) findViewById(R.id.txt_green);

        // click
        txt_red.setOnClickListener(click_red);
        txt_blue.setOnClickListener(click_blue);
        txt_green.setOnClickListener(click_green);
    }

    /*
    Function main of click to show red fragment
     */
    private View.OnClickListener click_red = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            putFragment(new RedFragment());
        }
    };

    /*
    Function main of click to show blue fragment
     */
    private View.OnClickListener click_blue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            putFragment(new BlueFragment());
        }
    };

    /*
    Function main of click to show green fragment
     */
    private View.OnClickListener click_green = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            putFragment(new GreenFragment());
        }
    };

    /*
    Function to replace fragment
     */
    private void putFragment(Fragment fragment) {
        FragmentTransaction ft;
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frm_main, fragment); // replace
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }
}
