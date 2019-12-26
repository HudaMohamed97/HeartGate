package dev.cat.mahmoudelbaz.heartgate.webServices;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;

public class CustomBottomSheet extends BottomSheetDialogFragment {

    private SharedPreferences shared;
    private ModelMyConnections object;

    public CustomBottomSheet() {
    }

    @SuppressLint("ValidFragment")
    public CustomBottomSheet(ModelMyConnections object) {
        this.object = object;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_dialog_bottom_sheet, container, false);
        Button connectButton = v.findViewById(R.id.connectButton);
        TextView textView = v.findViewById(R.id.txtTitle);
        TextView txtName = v.findViewById(R.id.txtName);
        textView.setText(object.getJobTitle());
        txtName.setText(object.getName());
        shared = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAddConnection();
            }
        });
        return v;
    }

    private void callAddConnection() {
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
                Toast.makeText(getActivity(), "success" + response, Toast.LENGTH_SHORT).show();
                dismiss();
                openAlertDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(getActivity()).add(postrequest);
    }

    private void openAlertDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("you Added Successfully");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}


