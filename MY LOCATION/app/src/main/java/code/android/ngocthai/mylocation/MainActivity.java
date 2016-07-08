package code.android.ngocthai.mylocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import code.android.ngocthai.mylocation.library.GPSTracker;

public class MainActivity extends AppCompatActivity {

    private TextView txt_map_activity, txt_check_location, txt_show_my_location;

    /*
    Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /*
    Function work some UI
     */
    private void initView() {
        // find view by id
        txt_check_location = (TextView) findViewById(R.id.txt_check_location);
        txt_map_activity = (TextView) findViewById(R.id.txt_map_activity);
        txt_show_my_location = (TextView) findViewById(R.id.txt_show_location);

        // click
        txt_map_activity.setOnClickListener(click_map_activity);
        txt_show_my_location.setOnClickListener(click_show_my_location);
        txt_check_location.setOnClickListener(click_check_location);

    }

    /*
    Click to go map activity
     */
    private View.OnClickListener click_map_activity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    };

    /*
    Click to go my location activity
     */
    private View.OnClickListener click_show_my_location = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, YourLocationActivity.class);
            startActivity(intent);
        }
    };

    /*
    Click to show your location by latitude and longitude
     */
    private View.OnClickListener click_check_location = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            autoCheckLocation();
        }
    };

    /*
    TODO CHECK YOUR LOCATION BY GPS
     */
    private void autoCheckLocation() {
        GPSTracker gps = new GPSTracker(MainActivity.this);
        if (gps.canGetLocation()) {
            Toast.makeText(MainActivity.this, "Your lication is : \nLatitude is : " + gps.getLatitude() + "\nLongitude is : " + gps.getLongitude(), Toast.LENGTH_SHORT).show();
        } else {
            gps.getLocation();
        }
    }
}
