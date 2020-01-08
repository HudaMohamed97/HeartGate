package dev.cat.mahmoudelbaz.heartgate.medicalStatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dev.cat.mahmoudelbaz.heartgate.R;

public class BMI extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private TextView result;
    private Button calculate_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
        calculate_button = findViewById(R.id.calculate_button);
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI(view);
            }
        });
    }

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);
            float bmi = weightValue / (heightValue * heightValue);
            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel;
        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = "very_severely_underweight";
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmiLabel = "severely_underweight";
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = "underweight";
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = "normal";
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = "overweight";
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = "obese_class_i";
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = "obese_class_ii";
        } else {
            bmiLabel = "obese_class_iii";
        }
        bmiLabel = bmi + " kg/m^2 " + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }
}