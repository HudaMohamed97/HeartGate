package dev.cat.mahmoudelbaz.heartgate.signUp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.webServices.Webservice;
import retrofit2.Call;
import retrofit2.Callback;

public class UpdateData extends AppCompatActivity {

    private ProgressBar progress;
    private SharedPreferences shared;
    private ImageView back;
    private EditText userName, firstName, middleName, lastName, email, password, confrimPassword, phoneNumber, dateOfBirth;
    private Spinner speciality;
    private Button updateDataButton;
    private RadioGroup radioGender;
    private RadioButton male, female;
    private String url;
    private String userID;
    private int year = 1991;
    private int month = 0;
    private int day = 1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        shared = getSharedPreferences("id", Context.MODE_PRIVATE);
        userID = shared.getString("id", "0");
        setViews();
        dateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DatePickerDialog datePick = new DatePickerDialog(UpdateData.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            int month = monthOfYear + 1;
                            dateOfBirth.setText(year + "-" + String.format("%02d", month) + "-" + String.format("%02d", dayOfMonth));
                        }
                    }, year, month, day);
                    datePick.show();
                }
            }
        });

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePick = new DatePickerDialog(UpdateData.this, new DatePickerDialog.OnDateSetListener() {
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

        updateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
    }

    private void updateData() {
        final String userName = UpdateData.this.userName.getText().toString();
        final String firstName = UpdateData.this.firstName.getText().toString();
        final String middleName = UpdateData.this.middleName.getText().toString();
        final String lastName = UpdateData.this.lastName.getText().toString();
        final String email = UpdateData.this.email.getText().toString();
        final String password = UpdateData.this.password.getText().toString();
        final String confirmPassword = confrimPassword.getText().toString();
        final String phoneNumber = UpdateData.this.phoneNumber.getText().toString();
        final String dateOfBirthday = dateOfBirth.getText().toString();
        final int selectedSpeciality = speciality.getSelectedItemPosition();
        int selectedGender = radioGender.getCheckedRadioButtonId();
        View radioButton = radioGender.findViewById(selectedGender);
        final int genderidx = radioGender.indexOfChild(radioButton);
        final String genderidxtxt = Integer.toString(genderidx);

        HashMap<String, Object> map = new HashMap<>();
        map.put("firstname", firstName);
        map.put("midname", middleName);
        map.put("lastname", lastName);
        map.put("username", userName);
        map.put("password", password);
        map.put("email", email);
        map.put("mobile_number", phoneNumber);
        map.put("birthdate", dateOfBirthday);
        map.put("fk_gender_id", genderidxtxt);
        map.put("fk_speciality_id", selectedSpeciality);
        if (userName.length() == 0 || firstName.length() == 0
                || middleName.length() == 0 || lastName.length() == 0
                || email.length() == 0 || phoneNumber.length() == 0
                || dateOfBirthday.length() == 0 || selectedSpeciality == 0 || genderidx == -1 || genderidx == 0) {
            Toast.makeText(UpdateData.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            callUpdateDataApi(map);
        }
    }

    private void callUpdateDataApi(Map<String, Object> map) {
        progress.setVisibility(View.VISIBLE);
        Webservice.getInstance().getApi().updateUser(userID, map).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateData.this, "Data Update Successfully", Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                } else {
                    Toast.makeText(UpdateData.this, response.message(), Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(UpdateData.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
            }
        });
    }

    private void setViews() {
        back = findViewById(R.id.bck);
        userName = findViewById(R.id.etUserName);
        firstName = findViewById(R.id.etFirstName);
        middleName = findViewById(R.id.etMiddleName);
        lastName = findViewById(R.id.etLastName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        password.setVisibility(View.GONE);
        confrimPassword = findViewById(R.id.etConfirmPassword);
        confrimPassword.setVisibility(View.GONE);
        phoneNumber = findViewById(R.id.etPhoneNumber);
        dateOfBirth = findViewById(R.id.etDateOfBirth);
        speciality = findViewById(R.id.spinnerSpeciality);
        updateDataButton = findViewById(R.id.btnRegister);
        updateDataButton.setText("Update");
        radioGender = findViewById(R.id.radioGender);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
        progress = findViewById(R.id.progressBar);
        progress.setVisibility(View.INVISIBLE);
    }

}
