package com.example.trip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bSubmit, bPickUpDateBtn;

    TextInputEditText etTripName, etDestination, etDescription;

    TextInputLayout lyTripName, lyDestination, lyDescription;

    TextView showSelectedDate;

    AutoCompleteTextView travelDropDown;

    RadioGroup riskRadioGroup;

    RadioButton riskButton,noriskButton;

    boolean isAllFieldsChecked = false;

    TripDBHelper dbHelper;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSubmit = findViewById(R.id.submitBtn);

        etTripName = findViewById(R.id.nameOfTripTextField);
        lyTripName = findViewById(R.id.nameOfTripContainer);

        etDestination = findViewById(R.id.destinationTextField);
        lyDestination = findViewById(R.id.destinationContainer);

        lyDescription = findViewById(R.id.descriptionContainer);
        etDescription = findViewById(R.id.descriptionTextField);

        riskRadioGroup = (RadioGroup) findViewById(R.id.riskRadioGroup);
        riskButton = (RadioButton) findViewById(R.id.riskBtn);
        noriskButton = (RadioButton) findViewById(R.id.noriskBtn);


        dbHelper = new TripDBHelper(this);


        //======================= DropDown Travel Method ==============================

        travelDropDown = findViewById(R.id.travelMethodDropdown);

        String[] methods = getResources().getStringArray(R.array.travel_method);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, methods);

        travelDropDown.setAdapter(arrayAdapter);




        //======================== DATE PICKER START HERE =============================

        bPickUpDateBtn = findViewById(R.id.pickupDateBtn);

        showSelectedDate = findViewById(R.id.showSelectedDateTextView);

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        materialDateBuilder.setTitleText("SELECT A DATE");

        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        bPickUpDateBtn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                }
        );

        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        showSelectedDate.setText("Select Date of Trip : " + materialDatePicker.getHeaderText());
                    }
                }
        );
        //===================== DATE PICKER END HERE ================================



        //=====================Form Validation Start Here=============================

        bSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                isAllFieldsChecked = checkAllFields();

                if(isAllFieldsChecked){
                    addTrip();
                }
            }

            private boolean checkAllFields() {
                if(etTripName.length() == 0){
                    lyTripName.setError("This field is required.");
                    return false;
                }

                if(etDestination.length() == 0){
                    lyDestination.setError("This field is required.");
                    return false;
                }
                return true;
            }
        });


    }

    private void addTrip() {
        String trip_name =etTripName.getText().toString();
        String trip_destination = etDestination.getText().toString();
        String trip_description = null;
        String trip_method = travelDropDown.getText().toString();

        String date = showSelectedDate.getText().toString();
        String trip_date = date.substring(22);

        Boolean trip_risk;
        if(riskButton.isChecked()){
            trip_risk = true;
        } else {
            trip_risk = false;
        }

        if(etDescription.length()!=0){
            trip_description = etDescription.getText().toString();
        }

        Trip trip = new Trip(trip_name,trip_destination, trip_date, trip_risk, trip_method,trip_description);

        long id = dbHelper.addTrip(trip);

        Toast.makeText(this, "Trip successfully added.", Toast.LENGTH_LONG).show();

        intent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);



    }
}