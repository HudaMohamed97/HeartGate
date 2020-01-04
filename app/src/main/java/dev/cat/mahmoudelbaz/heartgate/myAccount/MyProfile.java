package dev.cat.mahmoudelbaz.heartgate.myAccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import dev.cat.mahmoudelbaz.heartgate.BitmapHelper;
import dev.cat.mahmoudelbaz.heartgate.ImageBase64;
import dev.cat.mahmoudelbaz.heartgate.myAccount.oldChat.UserDataModel;
import dev.cat.mahmoudelbaz.heartgate.signUp.UpdateData;
import dev.cat.mahmoudelbaz.heartgate.signUp.UpdatePassword;
import dev.cat.mahmoudelbaz.heartgate.webServices.Webservice;
import permission.auron.com.marshmallowpermissionhelper.ActivityManagePermission;
import permission.auron.com.marshmallowpermissionhelper.PermissionResult;
import permission.auron.com.marshmallowpermissionhelper.PermissionUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import dev.cat.mahmoudelbaz.heartgate.R;
import retrofit2.Call;
import retrofit2.Callback;

public class MyProfile extends ActivityManagePermission {

    private static final String IMAGE_URL = "http://heartgate.co/api_heartgate/layout/images/";
    private static final String BASE_URL = "http://heartgate.co/api_heartgate/users/current/";
    private SharedPreferences shared;
    private String userID;
    private TextView name, email, mobile, dateOfBirth, gender, speciality, jobTitle, currentLiving;
    private Button btnUpdate;
    private Button btnUpdatePassword;
    private ImageView imgprofile;
    private ProgressBar progress;
    private String url;
    private TypedArray specialityArr, jobTitleArr, currentLivingArr, currentWorkArr, prevWorkArr, expArr;
    JSONObject res;
    private static int RESULT_LOAD_IMAGE = 1;
    private UserDataModel userDataModel;
    private String genderType = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        shared = getSharedPreferences("id", Context.MODE_PRIVATE);
        userID = shared.getString("id", "0");
        setViews();
        getUserData();
        setClickListener();


    }

    private void setClickListener() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToUppdateDataScreen();
            }
        });

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToUppdatPasswordScreen();
            }
        });


        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isGranted = isPermissionsGranted(MyProfile.this, new String[]{PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE});
                if (isGranted) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                } else {
                    askCompactPermissions(new String[]{PermissionUtils.Manifest_READ_EXTERNAL_STORAGE}, new PermissionResult() {
                        @Override
                        public void permissionGranted() {
                            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, RESULT_LOAD_IMAGE);
                        }

                        @Override
                        public void permissionDenied() {
                            Toast.makeText(MyProfile.this, "You Cannot use this ferature Granting permission", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void permissionForeverDenied() {
                            Toast.makeText(MyProfile.this, "Please Enable Storage Permission", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    private void getUserData() {
        url = BASE_URL + userID;
        StringRequest loginRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray usersarray = new JSONArray(response);
                    res = usersarray.getJSONObject(0);
                    final String fullName = res.getString("fullname");
                    final String username = res.getString("username");
                    final String myemail = res.getString("email");
                    final String mobile_number = res.getString("mobile_number");
                    final String dateofBirthDay = res.getString("birthdate");
                    final int genderint = res.getInt("fk_gender_id");
                    final int specialityint = res.getInt("fk_speciality_id");
                    final String specialitystring = (String) specialityArr.getText(specialityint);
                    final int jobTitleint = res.getInt("fk_job_id");
                    final String jobTitlestring = (String) jobTitleArr.getText(jobTitleint);
                    final int currentLivingint = res.getInt("fk_current_living_place");
                    final String currentLivingstring = (String) currentLivingArr.getText(currentLivingint);
                    final String imgstring = res.getString("image_profile");
                    final String imgurl = IMAGE_URL + imgstring;
                    if (genderint == 1) {
                        gender.setText("Male");
                        genderType = "Male";
                    } else if (genderint == 2) {
                        gender.setText("Female");
                        genderType = "Female";
                    }
                    userDataModel = new UserDataModel(fullName, myemail, mobile_number, dateofBirthDay, genderType, specialitystring, username);
                    name.setText(fullName);
                    email.setText(myemail);
                    mobile.setText(mobile_number);
                    dateOfBirth.setText(dateofBirthDay);
                    speciality.setText(specialitystring);
                    jobTitle.setText(jobTitlestring);
                    currentLiving.setText(currentLivingstring);
                    Picasso.with(MyProfile.this).load(imgurl).placeholder(R.drawable.profile).error(R.drawable.profile).into(imgprofile);
                    progress.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.setVisibility(View.GONE);
                Toast.makeText(MyProfile.this, "Network Error", Toast.LENGTH_SHORT).show();


            }
        });

        Volley.newRequestQueue(MyProfile.this).add(loginRequest);
    }

    private void setViews() {
        specialityArr = getResources().obtainTypedArray(R.array.speciality_array);
        jobTitleArr = getResources().obtainTypedArray(R.array.jobTitle_array);
        currentLivingArr = getResources().obtainTypedArray(R.array.currentLiving_array);
        currentWorkArr = getResources().obtainTypedArray(R.array.currentWork_array);
        prevWorkArr = getResources().obtainTypedArray(R.array.previousWork_array);
        expArr = getResources().obtainTypedArray(R.array.yearsOfExp_array);
        imgprofile = findViewById(R.id.imgProfile);
        progress = findViewById(R.id.progressBar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);
        name = findViewById(R.id.profileTextName);
        email = findViewById(R.id.profileEmail);
        mobile = findViewById(R.id.profileMob);
        dateOfBirth = findViewById(R.id.profileBirthDate);
        gender = findViewById(R.id.txtGender);
        speciality = findViewById(R.id.txtSpeciality);
        jobTitle = findViewById(R.id.txtJobTitle);
        currentLiving = findViewById(R.id.txtCurrentLivingPlace);
    }

    private void navigateToUppdateDataScreen() {
        Intent signUpIntent = new Intent(MyProfile.this, UpdateData.class);
        signUpIntent.putExtra("userDataModel", userDataModel);
        startActivity(signUpIntent);
        finish();
    }

    private void navigateToUppdatPasswordScreen() {
        Intent updatePasswordIntent = new Intent(MyProfile.this, UpdatePassword.class);
        updatePasswordIntent.putExtra("userDataModel", userDataModel);
        startActivity(updatePasswordIntent);
        finish();
    }

    private void changePhoto(String encoded) {
        HashMap<String, String> map = new HashMap<>();
        map.put("image_profile", encoded);
        progress.setVisibility(View.VISIBLE);
        callChangeImageApi(map);
    }

    private void callChangeImageApi(HashMap<String, String> map) {
        Webservice.getInstance().getApi().changeImage(userID, map).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                if (!response.isSuccessful()) {
                    assert response.errorBody() != null;
                    Toast.makeText(MyProfile.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                } else {
                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(MyProfile.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
            }
        });
    }

    // to open Gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgprofile.setImageBitmap(BitmapHelper.decodeFile(picturePath, 300, 300, true));
            Bitmap bitmap = ((BitmapDrawable) imgprofile.getDrawable()).getBitmap();
            String encoded = ImageBase64.encodeTobase64(bitmap);
            changePhoto(encoded);
        }
    }
}
