package com.apps.noura.tab;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static android.Manifest.*;

/**
 * Created by Noura Alsomaikhi on 12/28/2017.
 */

public class Tab1Fragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "MapFrag";
    private Button B1;

    GoogleMap mGoogleMap;
    MapView mMapView;
    private Boolean mLocationPermissionsGranted = false;
    FusedLocationProviderClient mFusedLocationProviderClient;
    View mView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.tab1_fragment,container, false );

        B1 =(Button) mView.findViewById(R.id.btn1);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"B1", Toast.LENGTH_SHORT).show();
            }
        });
        return mView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        }
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

                try{
                    if(mLocationPermissionsGranted)
                    {
                        final Task location = mFusedLocationProviderClient.getLastLocation();

                    location.addOnCompleteListener(new OnCompleteListener() {
                 @Override
                    public void onComplete(@NonNull Task task) {
                                                    if(task.isSuccessful()){
                                     Log.d(TAG, "onComplete: found location!");
                                     Location currentLocation = (Location) task.getResult();

                                     googleMap.addMarker(new MarkerOptions().position(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude())).title("Statue").snippet("Hi"));
                   CameraPosition Liberty = CameraPosition.builder().target(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())).zoom(16).bearing(0).tilt(45).build();
              googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

                                              }else{
                                        Log.d(TAG, "onComplete: current location is null");
                                                   //  Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                                                     }
                                             }
                    });
                    }

                     }catch (SecurityException e){
                     Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
                        }
      //  googleMap.addMarker(new MarkerOptions().position(new LatLng(currentLocation.getLatitude();, -74.044502)).title("Statue").snippet("Hi"));

       // CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.689247, -74.044502)).zoom(16).bearing(0).tilt(45).build();
     //   googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

    }
}
