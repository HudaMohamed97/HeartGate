package dev.cat.mahmoudelbaz.heartgate.signUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.cat.mahmoudelbaz.heartgate.Login;
import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.myAccount.oldChat.UserDataModel;
import dev.cat.mahmoudelbaz.heartgate.webServices.Webservice;
import retrofit2.Call;
import retrofit2.Callback;

public class UpdatePassword extends AppCompatActivity {
    private ProgressBar progress;
    private SharedPreferences shared;
    private EditText password, confrimPassword, oldPassword;
    private String userName, firstName, middleName, lastName, email, genderidxtxt, phoneNumber, dateOfBirth, dateOfBirthday, selectedSpeciality;
    private Button updateDataButton;
    private String userID;
    private UserDataModel userDataModel;
    private RelativeLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password);
        shared = getSharedPreferences("id", Context.MODE_PRIVATE);
        userID = shared.getString("id", "0");
        userDataModel = (UserDataModel) getIntent().getSerializableExtra("userDataModel");
        setViews();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });
        updateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
    }

    private void updateData() {
        final String oldPassword = this.oldPassword.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String confirmPassword = confrimPassword.getText().toString().trim();
        HashMap<String, Object> map = new HashMap<>();
        String lineOfCurrencies = userDataModel.getName();
        String[] currencies = lineOfCurrencies.split(" ");
        firstName = currencies[0];
        middleName = currencies[1];
        lastName = currencies[2];
        map.put("oldpassword", oldPassword);
        map.put("password", password);
        map.put("confirm_password", password);
        if (oldPassword.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            Toast.makeText(UpdatePassword.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(UpdatePassword.this, "Password and Confirm Password not matched", Toast.LENGTH_SHORT).show();
        } else {
            callUpdateDataApi(map);
        }
    }

    private void callUpdateDataApi(Map<String, Object> map) {
        progress.setVisibility(View.VISIBLE);
        Webservice.getInstance().getApi().updatePassword(userID, map).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdatePassword.this, "Data Update Successfully", Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                    SharedPreferences.Editor myEdit = shared.edit();
                    myEdit.putString("id", "0");
                    myEdit.apply();
                    Intent i = new Intent(UpdatePassword.this, Login.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(UpdatePassword.this, response.message(), Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(UpdatePassword.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
            }
        });
    }

    private void setViews() {
        password = findViewById(R.id.password);
        oldPassword = findViewById(R.id.oldPassword);
        confrimPassword = findViewById(R.id.etConfirmPassword);
        updateDataButton = findViewById(R.id.btnRegister);
        progress = findViewById(R.id.progressBar);
        progress.setVisibility(View.INVISIBLE);
        layout = findViewById(R.id.activity_UpdatePassword);

    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

