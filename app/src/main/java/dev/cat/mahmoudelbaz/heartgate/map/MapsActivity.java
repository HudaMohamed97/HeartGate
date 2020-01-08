package dev.cat.mahmoudelbaz.heartgate.map;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;
import dev.cat.mahmoudelbaz.heartgate.webServices.Webservice;
import retrofit2.Call;
import retrofit2.Callback;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, mapListener, GoogleMap.OnInfoWindowClickListener {

    SharedPreferences shared;
    ImageView back;
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private MapWrapperLayout mapWrapperLayout;
    private CustomBottomSheet customBottomSheet;
    private String myUserID;

    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapWrapperLayout = findViewById(R.id.map_relative_layout);
        mapFragment.getMapAsync(this);
        back = findViewById(R.id.bck);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        shared = getSharedPreferences("id", Context.MODE_PRIVATE);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
        } else {
            showGPSDisabledAlertToUser();
        }
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

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
        mapWrapperLayout.init(googleMap, getPixelsFromDp(this, 39 + 20));
        mMap.setOnInfoWindowClickListener(this);
        myUserID = shared.getString("id", "0");
        String finalUrl = "http://heartgate.co/api_heartgate/users/nearby/" + myUserID + "/0";
        final StringRequest postsRequest = new StringRequest(
                Request.Method.GET, finalUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray object = new JSONArray(response);
                    for (int i = 0; i < object.length(); i++) {
                        JSONObject current = object.getJSONObject(i);
                        int id = current.getInt("id");
                        String lat = current.getString("lat");
                        String lng = current.getString("lng");
                        String nme = current.getString("fullname");
                        String speciality = current.getString("speciality");
                        int isReciever = current.getInt("is_reciever");
                        String last_time_location = current.getString("last_time_location");
                        int stateId = current.getInt("state_id");
                        int connection_id = current.getInt("connection_id");
                        String pic = "http://heartgate.co/api_heartgate/layout/images/" + current.getString("image_profile");
                        ModelMyConnections modelMyConnections = new ModelMyConnections(stateId, id, nme, speciality, pic, last_time_location);
                        modelMyConnections.setConnection_id(connection_id);
                        modelMyConnections.setIs_reciever(isReciever);
                        if (!(lat.equals("") || lng.equals(""))) {
                            LatLng l = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                            mMap.setInfoWindowAdapter(new InfoWindowAdapter(MapsActivity.this, MapsActivity.this));
                            if (stateId == 0) {
                                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                                        .position(l).title(nme)).setTag(modelMyConnections);
                            }
                            if (stateId == 1) {
                                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                        .position(l).title(nme)).setTag(modelMyConnections);
                            }
                            if (stateId == 2) {
                                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                                        .position(l).title(nme)).setTag(modelMyConnections);
                            }
                        }
                    }
                    Log.d("response", response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("response", error.toString());

                    }
                }
        );

        Volley.newRequestQueue(this).add(postsRequest);

    }


    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }


    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            Location userCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (userCurrentLocation != null) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("user_id", myUserID);
                map.put("lat", userCurrentLocation.getLatitude());
                map.put("lng", userCurrentLocation.getLongitude());
                map.put("distance", "");
                map.put("player_id", "");
                updateCurrentLocation(map);
                MarkerOptions currentUserLocation = new MarkerOptions();
                LatLng currentUserLatLang = new LatLng(userCurrentLocation.getLatitude(), userCurrentLocation.getLongitude());
                currentUserLocation.icon(BitmapDescriptorFactory.fromResource(R.drawable.currentlocation));
                currentUserLocation.position(currentUserLatLang);
                currentUserLocation.title("Current Location");
                mMap.addMarker(currentUserLocation);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUserLatLang, 9));

            }
        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onConnected(null);
        } else {
            Toast.makeText(MapsActivity.this, "Need Permission To Use this section", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    @Override
    public void setWrapperLayout(Marker marker, View infoWindow) {
        mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);
    }

    @Override
    public void updateCurrentLocation(Map<String, Object> map) {
        Webservice.getInstance().getApi().updateCurrentLocation(map).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MapsActivity.this, "Current location updated Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MapsActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        customBottomSheet = new CustomBottomSheet((ModelMyConnections) marker.getTag());
        customBottomSheet.show(getSupportFragmentManager(), "Dialog");
    }
}
