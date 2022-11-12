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

    Button bSubmit, bPickUpDateBtn, bCancel, bPickUpEndDateBtn;

    TextInputEditText etTripName, etDestination, etDescription, etBudget;

    TextInputLayout lyTripName, lyDestination, lyDescription;

    TextView showSelectedDate,showSelectedEndDate, dateHelpText;

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
        bCancel = findViewById(R.id.cancelBtn);

        etTripName = findViewById(R.id.nameOfTripTextField);
        lyTripName = findViewById(R.id.nameOfTripContainer);

        etDestination = findViewById(R.id.destinationTextField);
        lyDestination = findViewById(R.id.destinationContainer);

        lyDescription = findViewById(R.id.descriptionContainer);
        etDescription = findViewById(R.id.descriptionTextField);

        riskRadioGroup = (RadioGroup) findViewById(R.id.riskRadioGroup);
        riskButton = (RadioButton) findViewById(R.id.riskBtn);
        noriskButton = (RadioButton) findViewById(R.id.noriskBtn);

        dateHelpText = findViewById(R.id.dateHelpText);

        etBudget = findViewById(R.id.tripBudgetTextField);


        dbHelper = new TripDBHelper(this);


        //======================= DropDown Travel Method ==============================

        travelDropDown = findViewById(R.id.travelMethodDropdown);

        String[] methods = getResources().getStringArray(R.array.travel_method);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, methods);

        travelDropDown.setAdapter(arrayAdapter);




        //======================== DATE PICKER START HERE =============================

        bPickUpDateBtn = findViewById(R.id.pickupStartDateBtn);

        showSelectedDate = findViewById(R.id.showSelectedStartDateTextView);

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        materialDateBuilder.setTitleText("SELECT A START DATE");

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
                        showSelectedDate.setText("Select Start Date of Trip : " + materialDatePicker.getHeaderText());
                    }
                }
        );
        //===================== DATE PICKER END HERE ================================


        bPickUpEndDateBtn = findViewById(R.id.pickupEndDateBtn);

        showSelectedEndDate = findViewById(R.id.showSelectedEndDateTextView);

        MaterialDatePicker.Builder materialEndDateBuilder = MaterialDatePicker.Builder.datePicker();

        materialEndDateBuilder.setTitleText("SELECT AN END START DATE");

        final MaterialDatePicker materialEndDatePicker = materialEndDateBuilder.build();

        bPickUpEndDateBtn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        materialEndDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                }
        );

        materialEndDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        showSelectedEndDate.setText("Select End Date of Trip : " + materialEndDatePicker.getHeaderText());
                    }
                }
        );

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

                String date = showSelectedDate.getText().toString();
                String trip_date = date.substring(28);
                if(trip_date.length() <= 0){
                    dateHelpText.setText("This field is required.");
                    return false;
                }
                return true;


            }
        });

        bCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }

    private void addTrip() {
        String trip_name =etTripName.getText().toString();
        String trip_destination = etDestination.getText().toString();
        String trip_description = null;
        String trip_method = null;

        String date = showSelectedDate.getText().toString();
        String trip_date = date.substring(28);

        String end_date = showSelectedEndDate.getText().toString();
        String trip_end_date = end_date.substring(26);

        String trip_budget = null;

        Boolean trip_risk;
        if(riskButton.isChecked()){
            trip_risk = true;
        } else {
            trip_risk = false;
        }

        if(etDescription.length()!=0){
            trip_description = etDescription.getText().toString();
        }

        if(travelDropDown.getText().toString().length() != 0){
            trip_method =travelDropDown.getText().toString();
        }

        if(etBudget.length() != 0){
            trip_budget = etBudget.getText().toString();
        }

        if(trip_end_date.length() <=1){
            trip_end_date = null;
        }

        Trip trip = new Trip(trip_name,trip_destination, trip_date, trip_risk, trip_method,trip_description,trip_end_date,trip_budget);

        long id = dbHelper.addTrip(trip);

        Toast.makeText(this, "Trip successfully added.", Toast.LENGTH_LONG).show();

        intent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);



    }
}