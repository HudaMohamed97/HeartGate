package dev.cat.mahmoudelbaz.heartgate.map;

import android.view.View;

import com.google.android.gms.maps.model.Marker;

import java.util.Map;

interface mapListener {
    void setWrapperLayout(Marker marker, View infoWindow);

    void updateCurrentLocation(Map<String, Object> map);
}
