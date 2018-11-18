package pe.edu.utp.matchfinder;

import android.app.Activity;
import android.app.Dialog;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
        Marker lugar;
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);



        // Add a marker in Sydney and move the camera
        LatLng lugar1 = new LatLng(-12.066481, -77.037968);
        mMap.addMarker(new MarkerOptions().position(lugar1).title("Tío Versus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar1));

        LatLng lugar2 = new LatLng(-12.064099, -77.035817);
        mMap.addMarker(new MarkerOptions().position(lugar2).title("Rampage Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar2));

        LatLng lugar3 = new LatLng(-12.065851, -77.036015);
        mMap.addMarker(new MarkerOptions().position(lugar3).title("Los Nobs"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar3));

        LatLng lugar4 = new LatLng(-12.067320, -77.035790);
        mMap.addMarker(new MarkerOptions().position(lugar4).title("Fornite Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar4));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lugar4, 2));

        
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if( mLocationListener != null )
            {
                mLocationListener.onLocationChanged( location );
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));

            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
