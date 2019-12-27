package dev.cat.mahmoudelbaz.heartgate.map;

import android.view.View;

import com.google.android.gms.maps.model.Marker;

interface mapListener {
    void setWrapperLayout(Marker marker, View infoWindow);
}
