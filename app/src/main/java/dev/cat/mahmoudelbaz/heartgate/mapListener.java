package dev.cat.mahmoudelbaz.heartgate;

import android.view.View;

import com.google.android.gms.maps.model.Marker;

interface mapListener {
    void setWrapperLayout(Marker marker, View infoWindow);
}
