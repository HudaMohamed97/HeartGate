package dev.cat.mahmoudelbaz.heartgate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final Context context;
    private SharedPreferences shared;
    private ModelMyConnections object;
    private OnInfoWindowElemTouchListener infoButtonListener;
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
        ImageView ivIcon = myContentsView.findViewById(R.id.icon);
        shared = context.getSharedPreferences("id", Context.MODE_PRIVATE);
        object = (ModelMyConnections) marker.getTag();
        tvTitle.setText(object.getName());
        tvSnippet.setText(object.getJobTitle());
        Picasso.with(context).load(object.getImageUrl()).placeholder(R.drawable.iconconcor).error(R.drawable.profile).into(ivIcon);

       /* infoButtonListener = new OnInfoWindowElemTouchListener(connectButton, context.getResources().getDrawable(R.drawable.bg), context.getResources().getDrawable(R.drawable.bg)) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                Toast.makeText(context, "click on button 1", Toast.LENGTH_SHORT).show();
            }
        };
        connectButton.setOnTouchListener(infoButtonListener);
        infoButtonListener.setMarker(marker);
        mapListener.setWrapperLayout(marker, myContentsView);*/

      /*  connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                String userID = shared.getString("id", "0");
                String userName = shared.getString("Name", "0");
                int receiveId = object.getId();
                String receiveIdString = Integer.toString(receiveId);
                String url = "http://heartgate.co/api_heartgate/messages/connectuser/add";
                JSONObject json = new JSONObject();
                try {
                    json.put("fk_userid_send", userID);
                    json.put("fk_user_id_received", receiveIdString);
                    json.put("create_user_id", userName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest postrequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "success" + response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });

                Volley.newRequestQueue(context).add(postrequest);
            }
        });*/
        return myContentsView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }
}