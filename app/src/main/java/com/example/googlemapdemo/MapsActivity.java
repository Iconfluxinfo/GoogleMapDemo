package com.example.googlemapdemo;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        MapsInitializer.initialize(getApplicationContext());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng California = new LatLng(36.778261, -119.41);
        mMap.addMarker(new MarkerOptions().position(California)
                .title("Marker in California")
                .flat(true)
                .rotation(245)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.nav_icon)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(California));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(California, 13));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(California)
                .zoom(5)
                .bearing(90)
                .tilt(45)
                .build();

        // Animate the change in camera view over 2 seconds
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                10000, null);

        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(36.778261, -119.41))  // California
                .add(new LatLng(34.741612, -116.77))  // SanFrancisco
                .add(new LatLng(34.244273, -116.03))  // LasVegas
                .add(new LatLng(35.778261, -115.41))  // Mountain View
        );



    }


}
