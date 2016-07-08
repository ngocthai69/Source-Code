package code.android.ngocthai.mylocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.google.android.gms.ads.AdView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class YourLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap maps;
    private SupportMapFragment support_maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_location);

//        initAd();
        initToolbar();
        initMaps();
    }

    /*
    Use ad in this activity
    */
    private void initAd() {
        // Initialize the Mobile Ads SDK.
//        MobileAds.initialize(this, "ca-app-pub-4572614651158941/6192236518");

        // find view by id
//        adView = (AdView) findViewById(R.id.ad_view_your_location_activity);

//        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        // start loading the ad in the background
//        adView.loadAd(adRequest);
    }

    /*
    Support maps in this activity
     */
    private void initMaps() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        support_maps = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_your_location_activity);

        support_maps.getMapAsync(YourLocationActivity.this);
    }

    /*
    Implement function by OnMapReallyCallBack
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        maps = googleMap;

        // change of location will call it
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        maps.setMyLocationEnabled(true); // my location is true
        maps.setOnMyLocationChangeListener(map_change_location); // on click of change maps

    }

    /*
    Called when gps location changed
     */
    private GoogleMap.OnMyLocationChangeListener map_change_location = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            maps.clear();
            LatLng your_location = new LatLng(location.getLatitude(), location.getLongitude());
            maps.addMarker(new MarkerOptions().position(your_location).title("Your Location"));
            maps.animateCamera(CameraUpdateFactory.newLatLngZoom(your_location, 15));
//            maps.moveCamera(CameraUpdateFactory.newLatLngZoom(your_location, 15));
        }
    };

    /*
    Support actionbar
     */
    private void initToolbar() {
        ActionBar ac=getSupportActionBar();
        ac.setTitle("Your Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    Click of icon back in actionbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                // goto activity interested
                Intent intent = new Intent(YourLocationActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
        return true;
    }
}
