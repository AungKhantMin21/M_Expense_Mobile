package com.example.trip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTripActivity extends AppCompatActivity {

    TextView tvName,tvDate,tvDestination,tvRisk,tvDescription,tvBudget,tvBudgetLabel,tvDescriptionLabel;

    ImageView imgCar,imgTrain,imgBus,imgFlight,imgShip;

    TripDBHelper dbHelper;

    Intent intent;

    long _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);

        tvName = findViewById(R.id.detailTripName);
        tvDate = findViewById(R.id.detailTripDate);
        tvDestination = findViewById(R.id.detailTripDestination);
        tvRisk = findViewById(R.id.detailTripRisk);
        imgCar  = findViewById(R.id.detailTripCar);
        imgTrain = findViewById(R.id.detailTripTrain);
        imgBus = findViewById(R.id.detailTripBus);
        imgFlight = findViewById(R.id.detailTripFlight);
        imgShip = findViewById(R.id.detailTripShip);
        tvDescription = findViewById(R.id.detailTripDescription);
        tvBudget = findViewById(R.id.detailTripBudget);
        tvBudgetLabel = findViewById(R.id.detailTripBudgetLabel);
        tvDescriptionLabel = findViewById(R.id.detailTripDescriptionLabel);

        dbHelper = new TripDBHelper(this);

        intent = getIntent();
        String id = intent.getStringExtra("id");
//        String name =intent.getStringExtra("name");
//        String destination =intent.getStringExtra("destination");
//        String date =intent.getStringExtra("date");
//        String risk =intent.getStringExtra("risk");
//        String method =intent.getStringExtra("method");
//        String description =intent.getStringExtra("description");
//        String enddate = intent.getStringExtra("enddate");
//        String budget = intent.getStringExtra("budget");

        _id= Long.parseLong(id);

        Trip details = dbHelper.getTripDetail(_id);

        String name = details.getTrip_name();
        String destination = details.getTrip_destination();
        String date = details.getTrip_date();
        String method = details.getTrip_method();
        String description = details.getTrip_description();
        String enddate = details.getTrip_enddate();
        String budget = details.getTrip_budget();


        //Boolean _risk = Boolean.parseBoolean(trip);
        Boolean _risk = details.getTrip_risk();
        String risk = String.valueOf(_risk);

        tvName.setText(name);
        tvDestination.setText(destination);
        tvDate.setText(date);

        tvDescriptionLabel.setVisibility(View.GONE);
        tvDescription.setVisibility(View.GONE);
        tvBudget.setVisibility(View.GONE);
        tvBudgetLabel.setVisibility(View.GONE);

        if(enddate.length() > 1){
            tvDate.setText(date+" - "+enddate);
        }



        if(risk.equals("Yes")){
            tvRisk.setText("Yes");
        } else {
            tvRisk.setText("No");
        }

        if(description != null){
            tvDescriptionLabel.setVisibility(View.VISIBLE);
            tvDescription.setVisibility(View.VISIBLE);
            tvDescription.setText(description);
        }

        if(budget != null){
            tvBudgetLabel.setVisibility(View.VISIBLE);
            tvBudget.setVisibility(View.VISIBLE);
            tvBudget.setText(budget);
        }






        if(method.equals("Car")){
            imgCar.setBackgroundResource(R.drawable.activeroundedborder);
            imgCar.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Train")){
            imgTrain.setBackgroundResource(R.drawable.activeroundedborder);
            imgTrain.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Bus")){
            imgBus.setBackgroundResource(R.drawable.activeroundedborder);
            imgBus.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Flight")){
            imgFlight.setBackgroundResource(R.drawable.activeroundedborder);
            imgFlight.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Ship")) {
            imgShip.setBackgroundResource(R.drawable.activeroundedborder);
            imgShip.setColorFilter(getResources().getColor(R.color.white));
        }
    }
}