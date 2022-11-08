package com.example.trip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditTripActivity extends AppCompatActivity {

    Button bSubmit, bPickUpDateBtn, bCancel;

    TextInputEditText etTripName, etDestination, etDescription;

    TextInputLayout lyTripName, lyDestination, lyDescription;

    TextView showSelectedDate;

    AutoCompleteTextView travelDropDown;

    RadioGroup riskRadioGroup;

    RadioButton riskButton,noriskButton,radioButton;

    boolean isAllFieldsChecked = false;

    TripDBHelper dbHelper;

    Intent intent;

    long _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        bSubmit = findViewById(R.id.updateBtn);

        etTripName = findViewById(R.id.editNameOfTripTextField);
        lyTripName = findViewById(R.id.editNameOfTripContainer);

        etDestination = findViewById(R.id.editDestinationTextField);
        lyDestination = findViewById(R.id.editDestinationContainer);

        lyDescription = findViewById(R.id.editDescriptionContainer);
        etDescription = findViewById(R.id.editDescriptionTextField);

        riskRadioGroup = (RadioGroup) findViewById(R.id.editRiskRadioGroup);
        riskButton = (RadioButton) findViewById(R.id.editriskBtn);
        noriskButton = (RadioButton) findViewById(R.id.editnoriskBtn);

        bCancel = findViewById(R.id.updateCancelBtn);



        dbHelper = new TripDBHelper(this);





        //======================== DATE PICKER START HERE =============================

        bPickUpDateBtn = findViewById(R.id.editPickupDateBtn);

        showSelectedDate = findViewById(R.id.editShowSelectedDateTextView);

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


        intent = getIntent();
        String id = intent.getStringExtra("id");
        String name =intent.getStringExtra("name");
        String destination =intent.getStringExtra("destination");
        String date =intent.getStringExtra("date");
        String risk =intent.getStringExtra("risk");
        String method =intent.getStringExtra("method");
        String description =intent.getStringExtra("description");

        _id= Long.parseLong(id);

//        Boolean _risk = Boolean.parseBoolean(risk);

        etTripName.setText(name);
        etDestination.setText(destination);
        showSelectedDate.setText("Select Date of Trip : "+date);

        if(risk.equals("Yes")){
            riskRadioGroup.check(riskButton.getId());
        } else {
            riskRadioGroup.check(noriskButton.getId());
        }

        etDescription.setText(description);

        //======================= DropDown Travel Method ==============================

        travelDropDown = findViewById(R.id.editTravelMethodDropdown);

        String[] methods = getResources().getStringArray(R.array.travel_method);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EditTripActivity.this, android.R.layout.simple_spinner_item, methods);

        travelDropDown.setAdapter(arrayAdapter);

        if(method.length() != 0 && method != null){
            travelDropDown.setText(method, false);
        }



        //=====================Form Validation Start Here=============================

        bSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                isAllFieldsChecked = checkAllFields();

                if(isAllFieldsChecked){
                    updateTrip();
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

        bCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                intent = new Intent(EditTripActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateTrip() {
        String trip_name =etTripName.getText().toString();
        String trip_destination = etDestination.getText().toString();
        String trip_description = null;
        String trip_method = travelDropDown.getText().toString();

        String date = showSelectedDate.getText().toString();
        String trip_date = date.substring(22);

        boolean trip_risk;

        int selectedId = riskRadioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton.getText().equals("Yes")){
            trip_risk = true;
        } else{
            trip_risk = false;
        }

        if(etDescription.length()!=0){
            trip_description = etDescription.getText().toString();
        }

        Trip trip = new Trip(_id,trip_name,trip_destination, trip_date, trip_risk, trip_method,trip_description);

        dbHelper.updateTrip(trip);

        Toast.makeText(this, "Trip successfully updated.", Toast.LENGTH_LONG).show();

        intent = new Intent(EditTripActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}