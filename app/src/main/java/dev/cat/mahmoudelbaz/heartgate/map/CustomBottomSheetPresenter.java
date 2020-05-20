package dev.cat.mahmoudelbaz.heartgate.map;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;


public class CustomBottomSheetPresenter implements CustomBottomSheetInterface {
    private static final String DISCONNECT_URL = "http://heartgate.co/api_heartgate/messages/connectuser/disconnect/";
    private static final String CANCEL_URL = "http://heartgate.co/api_heartgate/messages/connectuser/cancel/";
    private Context context;
    private static final String ADD_URL = "http://heartgate.co/api_heartgate/messages/connectuser/add";
    private static final String APPROVE_URL = "http://heartgate.co/api_heartgate/messages/connectuser/approve/";
    private BottomSheetViewInterface bottomSheetViewInterface;
    private SharedPreferences shared;
    private String userID;
    private String userName;
    private String url;


    public CustomBottomSheetPresenter(Context context, BottomSheetViewInterface viewInstance) {
        this.context = context;
        bottomSheetViewInterface = viewInstance;
        shared = context.getSharedPreferences("id", Context.MODE_PRIVATE);
    }

    @Override
    public void callAddConnection(ModelMyConnections object) {
        userID = shared.getString("id", "0");
        userName = shared.getString("Name", "0");
        int receiveId = object.getId();
        String receiveIdString = Integer.toString(receiveId);
        url = ADD_URL;
        JSONObject json = new JSONObject();
        try {
            json.put("fk_userid_send", userID);
            json.put("fk_user_id_received", receiveIdString);
            json.put("create_user_id", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "success" + response, Toast.LENGTH_SHORT).show();
                bottomSheetViewInterface.openAlertDialog("Add successfully");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(context).add(postRequest);
    }

    @Override
    public void cancelConnectionRequest(ModelMyConnections object) {
        shared = context.getSharedPreferences("id", Context.MODE_PRIVATE);
        userID = shared.getString("id", "0");
        userName = shared.getString("Name", "0");
        url = CANCEL_URL + object.getConnection_id();
        JSONObject json = new JSONObject();
        try {
            json.put("update_user_id", "Android");
            json.put("fk_conn_state", "3");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                bottomSheetViewInterface.openAlertDialog("Cancelled Successfully");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(context).add(loginRequest);
    }

    @Override
    public void disconnectConnectionRequest(ModelMyConnections object) {
        userID = shared.getString("id", "0");
        userName = shared.getString("Name", "0");
        url = DISCONNECT_URL + object.getConnection_id();
        StringRequest loginRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "dev.cat.mahmoudelbaz.heartgate.signUp.LiveRsponse" + response, Toast.LENGTH_SHORT).show();

                bottomSheetViewInterface.openAlertDialog("disconnected Successfully");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(context).add(loginRequest);
    }

    @Override
    public void approveConnectionRequest(ModelMyConnections object, boolean isApproved) {
        userID = shared.getString("id", "0");
        userName = shared.getString("Name", "0");
        url = APPROVE_URL + object.getConnection_id();
        JSONObject json = new JSONObject();
        try {
            //to update for rejected
            json.put("update_user_id", "Android");
            json.put("fk_conn_state", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest postrequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "sucess ", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(context).add(postrequest);

    }

}






