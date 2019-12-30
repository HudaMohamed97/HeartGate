package dev.cat.mahmoudelbaz.heartgate.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final Context context;
    private SharedPreferences shared;
    private ModelMyConnections object;
    private mapListener mapListener;

    InfoWindowAdapter(Context cx, mapListener mapListener) {
        this.context = cx;
        this.mapListener = mapListener;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myContentsView = li.inflate(R.layout.custom_info_contents, null);
        TextView tvTitle = myContentsView.findViewById(R.id.title);
        TextView tvSnippet = myContentsView.findViewById(R.id.snippet);
        shared = context.getSharedPreferences("id", Context.MODE_PRIVATE);
        object = (ModelMyConnections) marker.getTag();
        if (object != null && object.getName() != null) {
            tvTitle.setText(object.getName());
        }
        if (object != null && object.getLast_time_location() != null) {
            tvSnippet.setText(object.getLast_time_location());

        }
        return myContentsView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }
}