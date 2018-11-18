package pe.edu.utp.matchfinder;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;

    RequestQueue mQueue;

    public MapFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        String url = "http://andre2sm.000webhostapp.com/Businesses/getAllBusinesses.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("businesses");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject  business = jsonArray.getJSONObject(i);

                        String name = business.getString("name");
                        String description = business.getString("description");
                        double latitude = business.getDouble("latitude");
                        double longitude = business.getDouble("longitude");

                        LatLng marker = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(marker).title(name));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
        // Add a marker in Sydney and move the camera
        /*LatLng lugar1 = new LatLng(-12.066481, -77.037968);
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
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lugar4, 16));*/
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

    private void jsonParse() {

    }

}
