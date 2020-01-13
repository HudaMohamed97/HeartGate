package dev.cat.mahmoudelbaz.heartgate.signUp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import dev.cat.mahmoudelbaz.heartgate.Login;
import dev.cat.mahmoudelbaz.heartgate.R;

public class SignUp extends AppCompatActivity {

    private ProgressBar progress;
    private ImageView back;
    private EditText userName, firstName, middleName, lastName, email, password, confrimPassword, phoneNumber, dateOfBirth;
    private Spinner speciality;
    private Button register;
    private RadioGroup radioGender;
    private RadioButton male, female;
    private RelativeLayout layout;
    private String url;
    private int year = 1991;
    private int month = 0;
    private int day = 1;
    boolean isFirstTime = false;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setViews();
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });


        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    hideKeyboard(view);
                }
            }
        });
        confrimPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    hideKeyboard(view);
                }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    hideKeyboard(view);
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    hideKeyboard(view);
                }
            }
        });


       /* dateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    hideKeyboard(view);
                    DatePickerDialog datePick = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            int month = monthOfYear + 1;
                            dateOfBirth.setText(year + "-" + String.format("%02d", month) + "-" + String.format("%02d", dayOfMonth));
                        }
                    }, year, month, day);
                    datePick.show();
                    hideKeyboard(view);
                } else {
                    hideKeyboard(view);

                }
            }
        });*/

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                DatePickerDialog datePick = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear + 1;
                        dateOfBirth.setText(year + "-" + String.format("%02d", month) + "-" + String.format("%02d", dayOfMonth));
                    }
                }, year, month, day);
                datePick.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userNametxt = userName.getText().toString();
                final String firstNametxt = firstName.getText().toString();
                final String middleNametxt = middleName.getText().toString();
                final String lastNametxt = lastName.getText().toString();
                final String emailtxt = email.getText().toString();
                final String passwordtxt = password.getText().toString();
                final String confirmPasswordtxt = confrimPassword.getText().toString();
                final String phoneNumbertxt = phoneNumber.getText().toString();
                final String dateOfBirthtxt = dateOfBirth.getText().toString();
                final int selectedSpeciality = speciality.getSelectedItemPosition();
                final String selectedSpecialitytxt = Integer.toString(selectedSpeciality);

                int selectedGender = radioGender.getCheckedRadioButtonId();
                View radioButton = radioGender.findViewById(selectedGender);
                final int genderidx = radioGender.indexOfChild(radioButton);
                final String genderidxtxt = Integer.toString(genderidx);

                if (userNametxt.length() == 0 || firstNametxt.length() == 0
                        || middleNametxt.length() == 0 || lastNametxt.length() == 0
                        || emailtxt.length() == 0 || passwordtxt.length() == 0
                        || confirmPasswordtxt.length() == 0 || phoneNumbertxt.length() == 0
                        || dateOfBirthtxt.length() == 0 || selectedSpeciality == 0 || genderidx == -1 || genderidx == 0) {

                    Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (passwordtxt == confirmPasswordtxt) {
                    Toast.makeText(SignUp.this, "Password and Confirm Password not matched", Toast.LENGTH_SHORT).show();
                } else {

                    progress.setVisibility(View.VISIBLE);
                    url = "http://heartgate.co/api_heartgate/users/add";

                    JSONObject jsobj = new JSONObject();
                    try {
                        jsobj.put("firstname", firstNametxt);
                        jsobj.put("midname", middleNametxt);
                        jsobj.put("lastname", lastNametxt);
                        jsobj.put("username", userNametxt);
                        jsobj.put("password", passwordtxt);
                        jsobj.put("confirm_password", confirmPasswordtxt);
                        jsobj.put("email", emailtxt);
                        jsobj.put("mobile_number", phoneNumbertxt);
                        jsobj.put("birthdate", dateOfBirthtxt);
                        jsobj.put("fk_gender_id", genderidx);
                        jsobj.put("fk_speciality_id", selectedSpeciality);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest postrequest = new JsonObjectRequest(Request.Method.POST, url, jsobj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            progress.setVisibility(View.INVISIBLE);
                            String message = null;
                            int state = 0;

                            try {

                                message = response.getString("Message");
                                state = response.getInt("state");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (state == 0) {
                                Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                            } else if (state == 1) {
                                Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUp.this, Login.class);
                                startActivity(i);
                                finish();


                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progress.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignUp.this, error.toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignUp.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Volley.newRequestQueue(SignUp.this).add(postrequest);

                }

            }
        });
    }

    private void setViews() {
        layout = findViewById(R.id.activity_sign_up);
        back = findViewById(R.id.bck);
        userName = findViewById(R.id.etUserName);
        firstName = findViewById(R.id.etFirstName);
        middleName = findViewById(R.id.etMiddleName);
        lastName = findViewById(R.id.etLastName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        confrimPassword = findViewById(R.id.etConfirmPassword);
        phoneNumber = findViewById(R.id.etPhoneNumber);
        dateOfBirth = findViewById(R.id.etDateOfBirth);
        speciality = findViewById(R.id.spinnerSpeciality);
        register = findViewById(R.id.btnRegister);
        radioGender = findViewById(R.id.radioGender);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
        progress = findViewById(R.id.progressBar);
        progress.setVisibility(View.INVISIBLE);
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
